package cn.workcenter.service;

import java.util.List;

import cn.workcenter.model.Role;

public interface RoleService {

	List<Role> queryRoles(Role role);

	Object addRole(Role role);

	Object getRoleByRoleid(Long roleid);

	Object editRole(Role role);

	Object forbiddenRoles(String roleids);

	Object deleteRoles(String roleids);

	List<Role> queryKpiDefaultRoles();

	List<Role> getRolesByUserid(Long userId);

	Object updateUserRoles(Long userId, Long[] roleId);
	
}
