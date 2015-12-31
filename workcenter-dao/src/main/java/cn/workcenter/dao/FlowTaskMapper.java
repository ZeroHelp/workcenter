package cn.workcenter.dao;

import java.util.List;
import java.util.Map;

import cn.workcenter.model.FlowTask;

public interface FlowTaskMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(FlowTask record);

    int insertSelective(FlowTask record);

    FlowTask selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FlowTask record);

    int updateByPrimaryKey(FlowTask record);

	FlowTask getFlowTaskByProcessinstanceidandNodeid(Map<String, Long> parameterMap);

	List<FlowTask> getFlowTaskByprocessdefinitionid(Long processdefinition_id);
}