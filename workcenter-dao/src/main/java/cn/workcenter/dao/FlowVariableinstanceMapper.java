package cn.workcenter.dao;

import java.util.List;
import java.util.Map;

import cn.workcenter.model.FlowVariableinstance;

public interface FlowVariableinstanceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FlowVariableinstance record);

    int insertSelective(FlowVariableinstance record);

    FlowVariableinstance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FlowVariableinstance record);

    int updateByPrimaryKeyWithBLOBs(FlowVariableinstance record);

    int updateByPrimaryKey(FlowVariableinstance record);

	List<FlowVariableinstance> getVariable(Long processinstance_id);

	int updateFlowVariableinstanceStatusById(Map<String, Object> parameterMap);

	List<FlowVariableinstance> getFlowVariableinstance(FlowVariableinstance flowVariableinstance);
	

}