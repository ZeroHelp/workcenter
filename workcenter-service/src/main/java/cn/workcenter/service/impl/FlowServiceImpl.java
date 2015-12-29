package cn.workcenter.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.workcenter.common.constant.FlowConstant;
import cn.workcenter.dao.FlowNodeMapper;
import cn.workcenter.dao.FlowTaskMapper;
import cn.workcenter.dao.FlowTaskinstanceMapper;
import cn.workcenter.dao.FlowTransitionMapper;
import cn.workcenter.dao.FlowVariableaccessMapper;
import cn.workcenter.dao.FlowVariableinstanceMapper;
import cn.workcenter.model.FlowNode;
import cn.workcenter.model.FlowTask;
import cn.workcenter.model.FlowTaskinstance;
import cn.workcenter.model.FlowTransition;
import cn.workcenter.model.FlowVariableaccess;
import cn.workcenter.model.FlowVariableinstance;
import cn.workcenter.service.FlowService;

@Service("flowService")
public class FlowServiceImpl implements FlowService, FlowConstant {

	@Autowired
	FlowVariableinstanceMapper flowVariableinstanceMapper;
	@Autowired
	FlowVariableaccessMapper flowVariableaccessMapper;
	@Autowired
	FlowTaskinstanceMapper flowTaskinstanceMapper;
	@Autowired
	FlowTaskMapper flowTaskMapper;
	@Autowired
	FlowNodeMapper flowNodeMapper;
	@Autowired
	FlowTransitionMapper flowTransitionMapper;
	
	@Override
	public Map<String, Object> getVariableaccess(Long node_id) {
		List<FlowVariableaccess> accesses = flowVariableaccessMapper.getVariableaccess(node_id);
		Map<String, Object> accessesMap = new HashMap<String, Object>();
		for(FlowVariableaccess fva: accesses) {
			accessesMap.put(fva.getVariablename() + VARIABLEACCESS_SUFFIX, fva.getAccess());
		}
		return accessesMap;
	}

	@Override
	public Map<String, FlowVariableinstance> getVariable(Long processinstance_id) {
		List<FlowVariableinstance> flowvariableList = flowVariableinstanceMapper.getVariable(processinstance_id);
		Map<String, FlowVariableinstance> flowvariableMap = new HashMap<String, FlowVariableinstance>();
		for(FlowVariableinstance flowvariable: flowvariableList) {
			flowvariableMap.put(flowvariable.getVarName(), flowvariable);
		}
		return flowvariableMap;
	}

	@Override
	public FlowTaskinstance getFlowTaskinstanceById(Long taskinstance_id) {
		FlowTaskinstance flowTaskinstance = flowTaskinstanceMapper.selectByPrimaryKey(taskinstance_id);
		return flowTaskinstance;
	}

	@Override
	public FlowTask getFlowTaskById(Long taskId) {
		FlowTask flowTask = flowTaskMapper.selectByPrimaryKey(taskId);
		return flowTask;
	}

	@Override
	public FlowNode getFlowNodeById(Long nodeId) {
		FlowNode flowNode = flowNodeMapper.selectByPrimaryKey(nodeId);
		return flowNode;
	}

	@Override
	public FlowTransition getFlowTransitionByFromNodeidAndProcessdefinationid(Long processdefinitionId, Long nodeid) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("processdefinition_id", processdefinitionId);
		parameterMap.put("from_node_id", nodeid);
		FlowTransition flowTransition = flowTransitionMapper.findFlowTransitionByFromNodeidAndProcessdefinationid(parameterMap);
		return flowTransition;
	}

}
