package cn.workcenter.service;

import java.util.Map;

import cn.workcenter.model.FlowNode;
import cn.workcenter.model.FlowTask;
import cn.workcenter.model.FlowTaskinstance;
import cn.workcenter.model.FlowTransition;
import cn.workcenter.model.FlowVariableinstance;


public interface FlowService {

	Map<String, Object> getVariableaccess(Long node_id);

	Map<String, FlowVariableinstance> getVariable(Long processinstance_id);

	FlowTaskinstance getFlowTaskinstanceById(Long taskinstance_id);

	FlowTask getFlowTaskById(Long taskId);

	FlowNode getFlowNodeById(Long nodeId);

	FlowTransition getFlowTransitionByFromNodeidAndProcessdefinationid(Long processdefinitionId, Long id);
	
	FlowTransition getFlowTransitionByToNodeidAndProcessdefinationid(Long processdefinitionId, Long id);

	void doEndNodeLogic(Long processinstance_id);

}
