package cn.workcenter.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.workcenter.common.WorkcenterApplication;
import cn.workcenter.common.WorkcenterCodeEnum;
import cn.workcenter.common.WorkcenterResult;
import cn.workcenter.common.cache.RedisCache;
import cn.workcenter.common.constant.CacheConstant;
import cn.workcenter.common.constant.SecurityConstant;
import cn.workcenter.common.constant.WebConstant;
import cn.workcenter.common.util.StringUtil;
import cn.workcenter.dao.FlowProcessinstanceMapper;
import cn.workcenter.dao.FlowTaskinstanceMapper;
import cn.workcenter.dao.GroupMapper;
import cn.workcenter.dao.GroupUserMapper;
import cn.workcenter.dao.UserMapper;
import cn.workcenter.dao.UserRoleMapper;
import cn.workcenter.model.FlowProcessinstance;
import cn.workcenter.model.FlowTaskinstance;
import cn.workcenter.model.Group;
import cn.workcenter.model.GroupUser;
import cn.workcenter.model.Resource;
import cn.workcenter.model.User;
import cn.workcenter.model.UserRole;
import cn.workcenter.service.ResourceService;
import cn.workcenter.service.UserService;

@Service("userService")
public class UserServiceImpl extends WorkcenterApplication implements UserService , SecurityConstant, CacheConstant, WebConstant {

	@Autowired
	private RedisCache redisCache;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private FlowProcessinstanceMapper flowProcessinstanceMapper;
	@Autowired
	private FlowTaskinstanceMapper flowTaskinstanceMapper;
	@Autowired
	private GroupMapper groupMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private GroupUserMapper GroupUserMapper;
	
	//登录 redisCache.save(USERNAME_PREFIX + sid, username).expired(7 * 24 * 60 * 60 * 1000l);
	//登录redisCache.save(RESOURCES_PREFIX + username, json(resourcelist));

	//退出 redisCache.delete(RESOURCES_PREFIX + username);
	@Override
	public boolean authAndInitSession(String requestURI) {
		
		if(isAnnonymousPath(requestURI)) return true;
		//sid: XX,modelname: XX,callmethod: XX
		Map<String, String> URIApartMap = new HashMap<String, String>();
		apartURI(URIApartMap, requestURI);
		
		//1.authentication  登录认证  init sid
		authentication(URIApartMap.get("sid"));
			
		//2.authorization   用户鉴权
		authorization(URIApartMap.get("sid"), URIApartMap.get("modelname"), URIApartMap.get("callmethod"));
		
		sidThreadLocal.set(URIApartMap.get("sid"));
		System.out.println("sidThreadLocal"+sidThreadLocal.get());
		return true;
	}
	
	// url规范 : /BASE_PATH/{8位sid}/{modelname:workcenter/kpi}/{callmethod}
	private void apartURI(Map<String, String> URIApartMap, String requestURI) {
		int sidstart = requestURI.indexOf("/", 1);
		if(sidstart <0)
			throw new cn.workcenter.common.exception.SecurityException("url is wrong! url :" + requestURI);
		int sidend = requestURI.indexOf("/", sidstart+1);
		if(sidend <0)
			throw new cn.workcenter.common.exception.SecurityException("url is wrong! url :" + requestURI);
		String sid = requestURI.substring(sidstart+1, sidend);
		URIApartMap.put("sid", sid);
		
		int modelnamestart = sidend;
		int modelnameend = requestURI.indexOf("/", modelnamestart+1);
		if(modelnameend <0)
			throw new cn.workcenter.common.exception.SecurityException("url is wrong! url :" + requestURI);
		String modelname = requestURI.substring(modelnamestart + 1, modelnameend);
		URIApartMap.put("modelname", modelname);
		
		int callmethodstart = modelnameend;
		String callmethod = requestURI.substring(callmethodstart + 1);
		URIApartMap.put("callmethod", callmethod);
	}

	private void authorization(String sid, String modelname, String callmethod) {
		String username = redisCache.get(USERNAME_PREFIX+sid);
		List<Resource> resources = resourceService.getResourcesByUserName(username);
		String url = modelname + "/" + callmethod;
		for(Resource resource: resources) {
			if(url.startsWith(resource.getResourceUrl()))
				return;
		}
		throw new cn.workcenter.common.exception.SecurityException("you have no security to visit this url! url :" + url);
	}
	
	private boolean authentication(String sid) {
		//1.{"USERNAME_PREFIX + sid" ,"username"}
		String username = redisCache.get(USERNAME_PREFIX+sid);
		if(StringUtil.isEmpty(username)) 
			throw new cn.workcenter.common.exception.SecurityException("sid is wrong! sid :" + sid);
		
		return true;
	}
	
	private boolean isAnnonymousPath(String requestURI) {
		String afterFirstPath = requestURI.substring(requestURI.indexOf("/", 1)==-1?0:requestURI.indexOf("/", 1)+1);
		if(auth_escapepage.contains(afterFirstPath)) 
			return true;
		for(String staticPath: static_page) {
			if(afterFirstPath.startsWith(staticPath))
				return true;
		}
		return false;
	}

	@Override
	public void destroyThreadLocal() {
		sidThreadLocal.set("");
	}

	@Override
	public WorkcenterResult doLogin(String username, String password) {
		User user = userMapper.getUserByUsername(username);
		
		if(user ==null) {
			return WorkcenterResult.custom().setNO(WorkcenterCodeEnum.valueOf(NO_EXISTUSER)).build();
		}
		
		if(!password.equals(user.getPassword())) {
			return WorkcenterResult.custom().setNO(WorkcenterCodeEnum.valueOf(NO_WRONGPASSWORD)).build();
		}
		
		//initial sid
		String newsid = StringUtil.getRandom(8);
		initialCache(newsid, username);
		
		return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_LOGIN), getSid()).build();
	}

	private void initialCache(String newsid, String username) {
		//init sercurity
		resourceService.initRedisResource(username);
		
		initialSid(newsid, username);
	}

	private void initialSid(String newsid, String username) {
		sidThreadLocal.set(newsid);
		redisCache.set(USERNAME_PREFIX+newsid, username);
		
		
	}

	public String getSid() {
		return sidThreadLocal.get();
	}

	@Override
	public WorkcenterResult doLogout(String sid) {
		redisCache.delete(USERNAME_PREFIX + sid);
		return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_USER_ADD)).build();
	}

	@Override
	public String getUsername() {
		String sid = getSid();
		String username = redisCache.get(USERNAME_PREFIX+sid);
		return username;
	}

	@Override
	public List<User> getFlowRelatedUsers(Long processinstance_id) {
		FlowProcessinstance flowProcessinstance = flowProcessinstanceMapper.selectByPrimaryKey(processinstance_id);
		List<User> userlist = userMapper.getFlowSwimlaneUsers(flowProcessinstance.getSwimlaneId());
		return userlist;
	}

	@Override
	public List<User> getNodeRelatedUsers(Long processinstance_id, Long node_id) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("processinstance_id", processinstance_id);
		parameterMap.put("node_id", node_id);
		FlowTaskinstance FlowTaskinstance = flowTaskinstanceMapper.getFlowTaskinstanceByProcessinstanceidandNodeid(parameterMap);
		List<User> userlist = userMapper.getFlowSwimlaneUsers(FlowTaskinstance.getSwimlaneId());
		return userlist;
	}

	@Override
	public String getUserRealnameByUserid(Long waitAssessmentPersonId) {
		User user = userMapper.selectByPrimaryKey(waitAssessmentPersonId);
		
		return user.getRealName();
	}
	
	@Override
	public User getUser() {
		String username = getUsername();
		User user = userMapper.getUserByUsername(username);
		return user;
	}

	@Override
	public Long getUserId() {
		return getUser().getId();
	}

	@Override
	public User getUserTeamLeader(Long userid) {
		
		Group userGroup = groupMapper.getGroupByUserid(userid);
		Group parentGroup = groupMapper.selectByPrimaryKey(userGroup.getParentId());
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("group_id", parentGroup.getId());
		List<User> groupUsers = userMapper.getUsersByGroupid(parameterMap);
		User tl = groupUsers.get(0);
		return tl;
	}

	@Override
	public List<User> getAllUsers() {
		
		List<User> users = userMapper.queryAllUsers();
		return users;
	}

	@Override
	public WorkcenterResult editUser(User user) {
		int re = userMapper.updateByPrimaryKeySelective(user);
		if(re>0){
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_USER_UPDATE)).build();
		} else {
			return WorkcenterResult.custom().setNO(WorkcenterCodeEnum.valueOf(NO_USER_UPDATE)).build();
		}
	}

	@Override
	public Object deleteUsers(String userids) {
		String[] arrayId = userids.split(",");
		for(int i=0;i<arrayId.length; i++) {
			deleteUser(Long.parseLong(arrayId[i]));
		}
		return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_USER_DELETE)).build();
	}
	
	private WorkcenterResult deleteUser(Long id) {
		int re = userMapper.deleteByPrimaryKey(id);
		if(re>0){
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_USER_DELETE)).build();
		} else {
			return WorkcenterResult.custom().setNO(WorkcenterCodeEnum.valueOf(NO_USER_DELETE)).build();
		}
	}

	@Override
	public Object getUserByUserid(Long userid) {
		User user = userMapper.selectByPrimaryKey(userid);
		if(user!=null && user.getId()>0){
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_USER_GET), user).build();
		} else {
			return WorkcenterResult.custom().setNO(WorkcenterCodeEnum.valueOf(NO_USER_GET)).build();
		}
	}

	@Override
	public Object forbiddenUsers(String userids) {
		String[] arrayId = userids.split(",");
		for(int i=0;i<arrayId.length; i++) {
			forbiddenUser(Long.parseLong(arrayId[i]));
		}
		return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_USER_FORBIDDEN)).build();
	}

	private WorkcenterResult forbiddenUser(Long id) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("id", id);
		parameterMap.put("status", 0);
		int re = userMapper.updateUserStatusById(parameterMap);
		if(re>0){
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_USER_FORBIDDEN)).build();
		} else {
			return WorkcenterResult.custom().setNO(WorkcenterCodeEnum.valueOf(NO_USER_FORBIDDEN)).build();
		}
	}

	@Override
	public List<User> queryUsers(User user) {
		List<User> users = userMapper.queryUsersBySelective(user);
		return users;
	}

	@Override
	public List<User> getUsersByGroupid(Long id) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("group_id", id);
		List<User> users = userMapper.getUsersByGroupid(parameterMap);
		return users;
	}

	@Override
	public WorkcenterResult changePassword(String oldPassword, String newPassword) {
		User currentUser = getUser();
		if(!currentUser.getPassword().equals(oldPassword)) {
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(NO_OLDPASSWORD_WRONG)).build();
		}
		User newUser = currentUser.clone();
		newUser.setPassword(newPassword);
		int re = userMapper.updateByPrimaryKeySelective(newUser);
		if(re>0){
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_CHANGE_PASSWORD)).build();
		} else {
			return WorkcenterResult.custom().setNO(WorkcenterCodeEnum.valueOf(NO_CHANGE_PASSWORD)).build();
		}
	}
	
	private Long addUser(User user) {
		if(StringUtils.isEmpty(user.getPassword())) {
			user.setPassword(DEFAULT_PASSWORD);
		}
		
		int re = userMapper.insertSelective(user);
		return user.getId();
	}

	@Override
	public Object addUser(User user, Long[] roleId, Long[] groupId) {
		Long userId = addUser(user);
		for(int i=0;i<roleId.length;i++) {
			UserRole userRole = new UserRole();
			userRole.setUserId(userId);
			userRole.setRoleId(roleId[i]);
			userRole.setStatus(OK_STATUS);
			userRole.setCreateTime(new Date());
			userRole.setCreateUserId(getUserId());
			userRoleMapper.insertSelective(userRole);
		}
		
		for(int i=0;i<groupId.length;i++) {
			GroupUser groupUser = new GroupUser();
			groupUser.setUserId(userId);
			groupUser.setGroupId(groupId[i]);
			groupUser.setStatus(OK_STATUS);
			GroupUserMapper.insertSelective(groupUser);
		}
		return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_USER_ADD)).build();
	}

	@Override
	public User getRootGroupManager(Long group_id) {
		Map<String , Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("group_id", group_id);
		List<User> groupUsers = userMapper.getUsersByGroupid(parameterMap);
		User manager = groupUsers.get(0);
		return manager;
	}

}
