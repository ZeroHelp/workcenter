package cn.workcenter.dao;

import java.util.List;
import java.util.Map;

import cn.workcenter.model.Group;

public interface GroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);

	Group getGroupByUserid(Long userid);

	Group getParentGroupByGroupid(Long id);

	List<Group> queryGroupsBySelective(Group group);

	int updateGroupStatusById(Map<String, Object> parameterMap);

	List<Group> queryAllGroup();

}