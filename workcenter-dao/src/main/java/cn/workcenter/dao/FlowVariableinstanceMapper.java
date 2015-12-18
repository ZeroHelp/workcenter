package cn.workcenter.dao;

import cn.workcenter.model.FlowVariableinstance;

public interface FlowVariableinstanceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FlowVariableinstance record);

    int insertSelective(FlowVariableinstance record);

    FlowVariableinstance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FlowVariableinstance record);

    int updateByPrimaryKeyWithBLOBs(FlowVariableinstance record);

    int updateByPrimaryKey(FlowVariableinstance record);
}