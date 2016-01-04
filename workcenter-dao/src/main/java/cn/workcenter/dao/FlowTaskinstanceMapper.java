package cn.workcenter.dao;

import java.util.List;
import java.util.Map;

import cn.workcenter.model.FlowTaskinstance;

public interface FlowTaskinstanceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FlowTaskinstance record);

    int insertSelective(FlowTaskinstance record);

    FlowTaskinstance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FlowTaskinstance record);

    int updateByPrimaryKey(FlowTaskinstance record);

	FlowTaskinstance findTaskinstanceByProcessinstanceIdAndUsername(Map<String, Object> parameterMap);

	FlowTaskinstance getFlowTaskinstanceByProcessinstanceidandNodeid(Map<String, Object> parameterMap);

	List<FlowTaskinstance> getTaskinstanceByProcessinstanceid(Long flowProcessinstance_id);

	FlowTaskinstance getCurrentOpenTaskinstance(Long processinstanceId);
}