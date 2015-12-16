package cn.workcenter.xml;

import cn.workcenter.model.FlowProcessdefinition;

public interface FlowProcessdefinitionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FlowProcessdefinition record);

    int insertSelective(FlowProcessdefinition record);

    FlowProcessdefinition selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FlowProcessdefinition record);

    int updateByPrimaryKey(FlowProcessdefinition record);
}