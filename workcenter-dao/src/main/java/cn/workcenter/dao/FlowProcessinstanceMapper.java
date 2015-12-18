package cn.workcenter.dao;

import cn.workcenter.model.FlowProcessinstance;

public interface FlowProcessinstanceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FlowProcessinstance record);

    int insertSelective(FlowProcessinstance record);

    FlowProcessinstance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FlowProcessinstance record);

    int updateByPrimaryKey(FlowProcessinstance record);
}