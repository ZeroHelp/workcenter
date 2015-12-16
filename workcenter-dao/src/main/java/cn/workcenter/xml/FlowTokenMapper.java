package cn.workcenter.xml;

import cn.workcenter.model.FlowToken;

public interface FlowTokenMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FlowToken record);

    int insertSelective(FlowToken record);

    FlowToken selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FlowToken record);

    int updateByPrimaryKey(FlowToken record);
}