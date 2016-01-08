package cn.workcenter.dao;

import java.util.List;
import java.util.Map;

import cn.workcenter.model.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

	List<Role> queryRolesBySelective(Role role);

	int updateRoleStatusById(Map<String, Object> parameterMap);
	
	List<Role> queryRolesByUserId(Long id);

	List<Role> queryAllRoles();
	/**
	 * 属于用户checked 1 
	 * @param userId
	 * @return
	 */
	List<Role> queryRolesByUserid(Long userId);
}