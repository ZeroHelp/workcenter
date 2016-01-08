package cn.workcenter.dao;

import java.util.List;

import cn.workcenter.model.RoleResource;

public interface RoleResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoleResource record);

    int insertSelective(RoleResource record);

    RoleResource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleResource record);

    int updateByPrimaryKey(RoleResource record);
    
    List<RoleResource> getResourceByRoleid(Long id);

	void deleteRoleResourceByRoleId(Long roleId);
}