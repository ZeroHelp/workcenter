package cn.workcenter.xml;

import cn.workcenter.model.FlowNode;

public interface FlowNodeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FlowNode record);

    int insertSelective(FlowNode record);

    FlowNode selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FlowNode record);

    int updateByPrimaryKey(FlowNode record);
}