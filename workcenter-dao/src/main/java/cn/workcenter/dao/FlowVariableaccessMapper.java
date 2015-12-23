package cn.workcenter.dao;

import java.util.List;

import cn.workcenter.model.FlowVariableaccess;

public interface FlowVariableaccessMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FlowVariableaccess record);

    int insertSelective(FlowVariableaccess record);

    FlowVariableaccess selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FlowVariableaccess record);

    int updateByPrimaryKey(FlowVariableaccess record);
    
	List<FlowVariableaccess> getVariableaccess(Long node_id);
}