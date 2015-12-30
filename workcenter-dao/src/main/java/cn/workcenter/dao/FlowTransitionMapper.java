package cn.workcenter.dao;

import java.util.List;
import java.util.Map;

import cn.workcenter.model.FlowNode;
import cn.workcenter.model.FlowTransition;

public interface FlowTransitionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FlowTransition record);

    int insertSelective(FlowTransition record);

    FlowTransition selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FlowTransition record);

    int updateByPrimaryKey(FlowTransition record);

	FlowTransition findFlowTransitionByFromNodeidAndProcessdefinationid(Map<String, Object> parameterMap);

	FlowTransition findFlowTransitionByToNodeidAndProcessdefinationid(Map<String, Object> parameterMap);
}