package cn.workcenter.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.workcenter.common.constant.FlowConstant;
import cn.workcenter.dao.FlowVariableaccessMapper;
import cn.workcenter.dao.FlowVariableinstanceMapper;
import cn.workcenter.model.FlowVariableaccess;
import cn.workcenter.service.FlowService;

@Service("flowService")
public class FlowServiceImpl implements FlowService, FlowConstant {

	@Autowired
	FlowVariableinstanceMapper flowVariableinstanceMapper;
	@Autowired
	FlowVariableaccessMapper flowVariableaccessMapper;
	
	@Override
	public Map<String, Object> getVariableaccess(Long node_id) {
		List<FlowVariableaccess> accesses = flowVariableaccessMapper.getVariableaccess(node_id);
		Map<String, Object> accessesMap = new HashMap<String, Object>();
		for(FlowVariableaccess fva: accesses) {
			accessesMap.put(fva.getVariablename() + VARIABLEACCESS_SUFFIX, fva.getAccess());
		}
		return accessesMap;
	}

}
