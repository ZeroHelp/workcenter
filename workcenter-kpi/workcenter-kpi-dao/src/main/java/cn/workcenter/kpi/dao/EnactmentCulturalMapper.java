package cn.workcenter.kpi.dao;

import java.util.List;

import cn.workcenter.kpi.model.EnactmentCultural;

public interface EnactmentCulturalMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EnactmentCultural record);

    int insertSelective(EnactmentCultural record);

    EnactmentCultural selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EnactmentCultural record);

    int updateByPrimaryKeyWithBLOBs(EnactmentCultural record);

    int updateByPrimaryKey(EnactmentCultural record);

	List<EnactmentCultural> getEnactmentCulturalsByProcessinstanceid(Long processinstance_id);
}