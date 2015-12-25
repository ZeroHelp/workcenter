package cn.workcenter.kpi.dao;

import java.util.List;

import cn.workcenter.kpi.model.Main;

public interface MainMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Main record);

    int insertSelective(Main record);

    Main selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Main record);

    int updateByPrimaryKey(Main record);

	Main getMainByProcessinstanceid(Long processinstance_id);

	List<Main> findAssosiateKpisByUsername(String username);
}