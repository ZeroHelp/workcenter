package cn.workcenter.admin.service;

import java.util.List;

import cn.workcenter.model.FlowVariableaccess;

public interface FlowVariableaccessService {
	
	Object addFlowVariableaccess(FlowVariableaccess flowVariableaccess);

	Object editFlowVariableaccess(FlowVariableaccess flowVariableaccess);

	Object getFlowVariableaccessByFlowVariableaccessid(Long flowVariableaccessid);

	Object deleteFlowVariableaccesss(String flowVariableaccessids);
	
	List<FlowVariableaccess> queryFlowVariableaccess(FlowVariableaccess flowVariableaccess);
}
