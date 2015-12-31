package cn.workcenter.dao;

import java.util.List;

import cn.workcenter.model.Group;
import cn.workcenter.model.User;

public interface GroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);

	Group getGroupByUserid(Long userid);

	Group getParentGroupByGroupid(Long id);

}