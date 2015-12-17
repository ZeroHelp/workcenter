package cn.workcenter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.workcenter.common.constant.SecurityConstant;
import cn.workcenter.model.Resource;
import cn.workcenter.service.SecurityService;
import cn.workcenter.xml.ResourceMapper;

@Service("securityService")
public class SecurityServiceImpl implements SecurityService, SecurityConstant {
	
	ResourceMapper resourceMapper;
	
	@Override
	public List<String> getAuthenEscapePage() {
		List<Resource> anonymousResources = resourceMapper.getResourcesByRoleName(ANONYMOUS_ROLE);
		List<String> escapePageList = new ArrayList<String>();
		for(Resource resource: anonymousResources) {
			escapePageList.add(resource.getResourceUrl());
		}
		return escapePageList;
	}

	
	
	
	
	
	
	
	
	//---------private 

}
