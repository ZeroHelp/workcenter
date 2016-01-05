package cn.workcenter.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.workcenter.common.WorkcenterApplication;
import cn.workcenter.common.WorkcenterCodeEnum;
import cn.workcenter.common.WorkcenterResult;
import cn.workcenter.common.cache.RedisCache;
import cn.workcenter.common.constant.CacheConstant;
import cn.workcenter.common.constant.SecurityConstant;
import cn.workcenter.common.util.StringUtil;
import cn.workcenter.dao.FlowProcessinstanceMapper;
import cn.workcenter.dao.FlowTaskMapper;
import cn.workcenter.dao.FlowTaskinstanceMapper;
import cn.workcenter.dao.UserMapper;
import cn.workcenter.model.FlowProcessinstance;
import cn.workcenter.model.FlowTask;
import cn.workcenter.model.FlowTaskinstance;
import cn.workcenter.model.Resource;
import cn.workcenter.model.User;
import cn.workcenter.service.ResourceService;
import cn.workcenter.service.UserService;

@Service("userService")
public class UserServiceImpl extends WorkcenterApplication implements UserService , SecurityConstant, CacheConstant {

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
		return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_LOGOUT)).build();
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
		Map<String, Long> parameterMap = new HashMap<String, Long>();
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


}
