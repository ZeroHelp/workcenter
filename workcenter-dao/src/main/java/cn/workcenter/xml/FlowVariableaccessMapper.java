package cn.workcenter.xml;

import cn.workcenter.model.FlowVariableaccess;

public interface FlowVariableaccessMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FlowVariableaccess record);

    int insertSelective(FlowVariableaccess record);

    FlowVariableaccess selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FlowVariableaccess record);

    int updateByPrimaryKey(FlowVariableaccess record);
}