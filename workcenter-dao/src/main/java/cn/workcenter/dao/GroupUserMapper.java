package cn.workcenter.dao;

import cn.workcenter.model.GroupUser;

public interface GroupUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GroupUser record);

    int insertSelective(GroupUser record);

    GroupUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GroupUser record);

    int updateByPrimaryKey(GroupUser record);

	void deleteGroupUserByUserId(Long userId);
}