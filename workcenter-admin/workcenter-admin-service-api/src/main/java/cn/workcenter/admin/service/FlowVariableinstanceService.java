package cn.workcenter.admin.service;

import java.util.List;

import cn.workcenter.model.FlowVariableinstance;

public interface FlowVariableinstanceService {
	Object addFlowVariableinstance(FlowVariableinstance flowVariableinstance);

	Object editFlowVariableinstance(FlowVariableinstance flowVariableinstance);

	Object getFlowVariableinstanceByFlowVariableinstanceid(Long flowVariableinstanceid);

	Object deleteFlowVariableinstances(String flowVariableinstanceids);

	Object forbiddenFlowVariableinstances(String flowVariableinstanceids);
	List<FlowVariableinstance> queryFlowVariableinstance(FlowVariableinstance flowVariableinstance);
}
