package cn.workcenter.kpi.dao;

import java.util.List;

import cn.workcenter.kpi.model.EnactmentCultural;
import cn.workcenter.kpi.model.EnactmentSelf;
import cn.workcenter.kpi.model.EnactmentSelfWithBLOBs;

public interface EnactmentSelfMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EnactmentSelfWithBLOBs record);

    int insertSelective(EnactmentSelfWithBLOBs record);

    EnactmentSelfWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EnactmentSelfWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(EnactmentSelfWithBLOBs record);

    int updateByPrimaryKey(EnactmentSelf record);

	List<EnactmentSelfWithBLOBs> getEnactmentSelfsByProcessinstanceid(Long processinstance_id);
}