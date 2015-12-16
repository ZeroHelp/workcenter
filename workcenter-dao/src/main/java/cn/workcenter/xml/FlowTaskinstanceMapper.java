package cn.workcenter.xml;

import cn.workcenter.model.FlowTaskinstance;

public interface FlowTaskinstanceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FlowTaskinstance record);

    int insertSelective(FlowTaskinstance record);

    FlowTaskinstance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FlowTaskinstance record);

    int updateByPrimaryKey(FlowTaskinstance record);
}