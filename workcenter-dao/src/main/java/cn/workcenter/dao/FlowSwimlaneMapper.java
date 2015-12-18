package cn.workcenter.dao;

import cn.workcenter.model.FlowSwimlane;

public interface FlowSwimlaneMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FlowSwimlane record);

    int insertSelective(FlowSwimlane record);

    FlowSwimlane selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FlowSwimlane record);

    int updateByPrimaryKey(FlowSwimlane record);
}