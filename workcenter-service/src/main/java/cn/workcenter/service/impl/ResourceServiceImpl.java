package cn.workcenter.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.workcenter.common.WorkcenterCodeEnum;
import cn.workcenter.common.WorkcenterResult;
import cn.workcenter.common.cache.RedisCache;
import cn.workcenter.common.constant.CacheConstant;
import cn.workcenter.common.constant.WebConstant;
import cn.workcenter.dao.ResourceMapper;
import cn.workcenter.model.Resource;
import cn.workcenter.service.ResourceService;
import cn.workcenter.service.UserService;

import com.alibaba.fastjson.JSON;

@Service("resourceService")
public class ResourceServiceImpl implements ResourceService , CacheConstant, WebConstant{

	@Autowired
	private ResourceMapper resourceMapper;
	
	@Autowired
	private RedisCache redisCache;
	
	@Autowired
	private UserService userService;
	
	@Override
	public List<Resource> getResourcesByUserName(String username) {
		String resourcesJson = redisCache.get(RESOURCES_PREFIX + username);
		List<Resource> resources = JSON.parseArray(resourcesJson, Resource.class);
		if(resources==null ||resources.size()==0) {
			resources =  initRedisResource(username);
		}
		return resources;
	}

	public List<Resource> initRedisResource(String username) {
		List<Resource> resources = resourceMapper.getResourcesByUsername(username);
		redisCache.set(RESOURCES_PREFIX + username,JSON.toJSONString(resources));
		return resources;
	}
	
	@Override
	public List<Resource> getUserModule() {
		String username = userService.getUsername();
		List<Resource> modules = resourceMapper.getRootResourcesByUsername(username);
		
		return modules;
	}

	@Override
	public List<Resource> getResoucesByParentid(String parentId) {
		List<Resource> resources = resourceMapper.getResoucesByParentid(parentId);
		return resources;
	}
	
	@Override
	public List<Resource> queryResources(Resource resource) {
		List<Resource> resources = resourceMapper.queryResourcesBySelective(resource);
		return resources;
	}
	@Override
	public Object addResource(Resource resource) {
		resource.setCreateTime(new Date());
		resource.setCreateUserid(userService.getUserId());
		int re = resourceMapper.insert(resource);
		if(re>0){
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_RESOURCE_ADD)).build();
		} else {
			return WorkcenterResult.custom().setNO(WorkcenterCodeEnum.valueOf(NO_RESOURCE_ADD)).build();
		}
	}
	@Override
	public Object getResourceByResourceid(Long resourceid) {
		Resource resource = resourceMapper.selectByPrimaryKey(resourceid);
		if(resource!=null && resource.getId() >0) {
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_RESOURCE_GET), resource).build();
		} else {
			return WorkcenterResult.custom().setNO(WorkcenterCodeEnum.valueOf(NO_RESOURCE_GET)).build();
		}
	}
	@Override
	public Object editResource(Resource resource) {
		int re = resourceMapper.updateByPrimaryKeySelective(resource);
		if(re>0){
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_RESOURCE_UPDATE)).build();
		} else {
			return WorkcenterResult.custom().setNO(WorkcenterCodeEnum.valueOf(NO_RESOURCE_UPDATE)).build();
		}
	}
	@Override
	public Object forbiddenResources(String resourceids) {
		String[] arrayId = resourceids.split(",");
		for(int i=0;i<arrayId.length;i++) {
			forbiddenResource(Long.parseLong(arrayId[i]));
		}
		return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_RESOURCE_FORBIDDEN)).build();
	}
	
	private WorkcenterResult forbiddenResource(Long id) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("id", id);
		parameterMap.put("status", 0);
		int re = resourceMapper.updateResourceStatusById(parameterMap);
		if(re>0){
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_RESOURCE_FORBIDDEN)).build();
		} else {
			return WorkcenterResult.custom().setNO(WorkcenterCodeEnum.valueOf(NO_RESOURCE_FORBIDDEN)).build();
		}
	}
	
	@Override
	public Object deleteResources(String resourceids) {
		String[] arrayId = resourceids.split(",");
		for(int i=0;i<arrayId.length;i++) {
			deleteResource(Long.parseLong(arrayId[i]));
		}
		return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_RESOURCE_DELETE)).build();
	}

	private WorkcenterResult deleteResource(Long id) {
		int re = resourceMapper.deleteByPrimaryKey(id);
		if(re>0){
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_RESOURCE_DELETE)).build();
		} else {
			return WorkcenterResult.custom().setNO(WorkcenterCodeEnum.valueOf(NO_RESOURCE_DELETE)).build();
		}
	}

}
