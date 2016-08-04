package cn.workcenter.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.workcenter.common.WorkcenterApplication;
import cn.workcenter.common.WorkcenterCodeEnum;
import cn.workcenter.common.WorkcenterResult;
import cn.workcenter.common.cache.RedisCache;
import cn.workcenter.common.constant.CacheConstant;
import cn.workcenter.common.constant.WebConstant;
import cn.workcenter.dao.ResourceMapper;
import cn.workcenter.dao.RoleResourceMapper;
import cn.workcenter.model.Resource;
import cn.workcenter.model.RoleResource;
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
	@Autowired
	private RoleResourceMapper roleResourceMapper;
	
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
		for(int i=0;i<modules.size();i++) {
			modules.get(i).setIcons(WorkcenterApplication.iconsList.get(i%WorkcenterApplication.iconsList.size()));
		}
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

	@Override
	public List<Resource> getResourcesByUserid(Long roleId) {
		List<Resource> resourcesList = new ArrayList<Resource>();
		
		Set<Resource> resourcesSet = new HashSet<Resource>();
		List<Resource> userResources = resourceMapper.getResourcesByRoleid(roleId);
		List<Resource> allResources = resourceMapper.queryAllResource();
		resourcesSet.addAll(userResources);
		resourcesSet.addAll(allResources);
		
		resourcesList.addAll(resourcesSet);
		Collections.sort(resourcesList);
		return resourcesList;
	}

	@Override
	public Object updateUserResources(Long roleId, Long[] resourceId) {
		
		roleResourceMapper.deleteRoleResourceByRoleId(roleId);
		
		for(int i=0;i<resourceId.length;i++) {
			Long resourceid = resourceId[i];
			RoleResource roleResource = new RoleResource();
			roleResource.setRoleId(roleId);
			roleResource.setResourceId(resourceid);
			roleResource.setCreateTime(new Date());
			roleResource.setCreateUserId(userService.getUserId());
			roleResource.setStatus(OK_STATUS);
			roleResourceMapper.insertSelective(roleResource);
		}
		
		return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_ROLERESOURCE_UPDATE)).build();
	}

}
