package cn.workcenter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.workcenter.common.constant.SecurityConstant;
import cn.workcenter.dao.ResourceMapper;
import cn.workcenter.model.Resource;
import cn.workcenter.service.SecurityService;

@Service("securityService")
public class SecurityServiceImpl implements SecurityService, SecurityConstant {
	
	@Autowired
	ResourceMapper resourceMapper;
	
	@Override
	public List<String> getAuthenEscapePage() {
		List<Resource> anonymousResources = resourceMapper.getResourcesByRolename(ANONYMOUS_ROLE);
		List<String> escapePageList = new ArrayList<String>();
		for(Resource resource: anonymousResources) {
			escapePageList.add(resource.getResourceUrl());
		}
		return escapePageList;
	}

	
	
	
	
	
	
	
	
	//---------private 

}
