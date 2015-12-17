package cn.workcenter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.workcenter.common.cache.RedisCache;
import cn.workcenter.common.constant.CacheConstant;
import cn.workcenter.model.Resource;
import cn.workcenter.service.ResourceService;
import cn.workcenter.xml.ResourceMapper;

import com.alibaba.fastjson.JSON;

@Service("resourceService")
public class ResourceServiceImpl implements ResourceService , CacheConstant{

	@Autowired
	private ResourceMapper resourceMapper;
	
	@Autowired
	private RedisCache redisCache;
	
	@Override
	public List<Resource> getResourcesByUserName(String username) {
		String resourcesJson = redisCache.get(RESOURCES_PREFIX + username);
		List<Resource> resources = JSON.parseArray(resourcesJson, Resource.class);
		if(resources==null ||resources.size()==0) {
			resources = resourceMapper.getResourcesByUserName(username);
		}
		return resources;
	}

}
