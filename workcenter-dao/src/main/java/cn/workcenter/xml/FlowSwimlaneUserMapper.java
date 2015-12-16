package cn.workcenter.xml;

import cn.workcenter.model.FlowSwimlaneUser;

public interface FlowSwimlaneUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FlowSwimlaneUser record);

    int insertSelective(FlowSwimlaneUser record);

    FlowSwimlaneUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FlowSwimlaneUser record);

    int updateByPrimaryKey(FlowSwimlaneUser record);
}