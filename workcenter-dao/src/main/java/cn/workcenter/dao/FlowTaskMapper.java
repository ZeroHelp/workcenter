package cn.workcenter.dao;

import cn.workcenter.model.FlowTask;

public interface FlowTaskMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FlowTask record);

    int insertSelective(FlowTask record);

    FlowTask selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FlowTask record);

    int updateByPrimaryKey(FlowTask record);
}