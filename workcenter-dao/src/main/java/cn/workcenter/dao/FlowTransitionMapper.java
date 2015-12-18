package cn.workcenter.dao;

import cn.workcenter.model.FlowTransition;

public interface FlowTransitionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FlowTransition record);

    int insertSelective(FlowTransition record);

    FlowTransition selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FlowTransition record);

    int updateByPrimaryKey(FlowTransition record);
}