package cn.workcenter.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.workcenter.common.constant.FlowConstant;
import cn.workcenter.dao.FlowVariableaccessMapper;
import cn.workcenter.dao.FlowVariableinstanceMapper;
import cn.workcenter.model.FlowVariableaccess;
import cn.workcenter.service.FlowService;

public class FlowServiceImpl implements FlowService, FlowConstant {

	@Autowired
	FlowVariableinstanceMapper flowVariableinstanceMapper;
	@Autowired
	FlowVariableaccessMapper flowVariableaccessMapper;
	
	@Override
	public Map<String, Integer> getVariableaccess(Long node_id) {
		
		List<FlowVariableaccess> accesses = flowVariableaccessMapper.getVariableaccess(node_id);
		Map<String, Integer> accessesMap = new HashMap<String, Integer>();
		for(FlowVariableaccess fva: accesses) {
			accessesMap.put(fva.getVariablename() + VARIABLEACCESS_SUFFIX, fva.getAccess());
		}
		return accessesMap;
	}

}
