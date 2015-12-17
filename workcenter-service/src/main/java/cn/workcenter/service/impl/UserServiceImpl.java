package cn.workcenter.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.workcenter.common.WorkcenterApplication;
import cn.workcenter.common.WorkcenterCodeEnum;
import cn.workcenter.common.WorkcenterResult;
import cn.workcenter.common.cache.RedisCache;
import cn.workcenter.common.constant.CacheConstant;
import cn.workcenter.common.constant.SecurityConstant;
import cn.workcenter.common.util.StringUtil;
import cn.workcenter.model.Resource;
import cn.workcenter.model.User;
import cn.workcenter.service.ResourceService;
import cn.workcenter.service.UserService;
import cn.workcenter.xml.UserMapper;

public class UserServiceImpl extends WorkcenterApplication implements UserService , SecurityConstant, CacheConstant {

	@Autowired
	private RedisCache redisCache;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ResourceService resourceService;
	
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
		return true;
	}
	
	// url规范 : /BASE_PATH/{8位sid}/{modelname:workcenter/kpi}/{callmethod}
	private void apartURI(Map<String, String> URIApartMap, String requestURI) {
		int sidstart = requestURI.indexOf("/", 1);
		if(sidstart <0)
			throw new cn.workcenter.common.exception.SecurityException("url is wrong! url :" + requestURI);
		int sidend = requestURI.indexOf("/", sidstart);
		if(sidend <0)
			throw new cn.workcenter.common.exception.SecurityException("url is wrong! url :" + requestURI);
		String sid = requestURI.substring(sidstart+1, sidend);
		URIApartMap.put("sid", sid);
		
		int modelnamestart = sidend;
		int modelnameend = requestURI.indexOf("/", modelnamestart);
		if(modelnameend <0)
			throw new cn.workcenter.common.exception.SecurityException("url is wrong! url :" + requestURI);
		String modelname = requestURI.substring(modelnamestart + 1, modelnameend);
		URIApartMap.put("modelname", modelname);
		
		int callmethodstart = modelnameend;
		int callmethodend = requestURI.indexOf("/", callmethodstart);
		if(callmethodend <0)
			throw new cn.workcenter.common.exception.SecurityException("url is wrong! url :" + requestURI);
		String callmethod = requestURI.substring(callmethodstart + 1, callmethodend);
		URIApartMap.put("callmethod", callmethod);
	}

	private void authorization(String sid, String modelname, String callmethod) {
		String username = redisCache.get(sid);
		List<Resource> resources = resourceService.getResourcesByUserName(username);
		String url = modelname + "/" + callmethod;
		for(Resource resource: resources) {
			if(resource.getResourceUrl().contains(url))
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
		String lastPath = requestURI.substring(requestURI.lastIndexOf("/")==-1?0:requestURI.lastIndexOf("/"));
		if(auth_escapepage.contains(lastPath)) 
			return true;
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
		return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_LOGIN)).build();
	}

	@Override
	public WorkcenterResult doLogout(String sid) {
		redisCache.delete(USERNAME_PREFIX + sid);
		return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_LOGOUT)).build();
	}

}
