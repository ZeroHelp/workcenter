package cn.workcenter.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.workcenter.common.WorkcenterApplication;
import cn.workcenter.common.WorkcenterCodeEnum;
import cn.workcenter.common.WorkcenterResult;
import cn.workcenter.common.constant.WebConstant;
import cn.workcenter.dao.RoleMapper;
import cn.workcenter.dao.UserRoleMapper;
import cn.workcenter.model.Role;
import cn.workcenter.model.UserRole;
import cn.workcenter.service.RoleService;
import cn.workcenter.service.UserService;

@Service("roleService")
public class RoleServiceImpl extends WorkcenterApplication implements RoleService , WebConstant{

	@Autowired
	private UserService userService;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Override
	public List<Role> queryRoles(Role role) {
		List<Role> roles = roleMapper.queryRolesBySelective(role);
		return roles;
	}
	@Override
	public Object addRole(Role role) {
		role.setCreateTime(new Date());
		role.setCreateUserid(userService.getUserId());
		int re = roleMapper.insert(role);
		if(re>0){
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_ROLE_ADD)).build();
		} else {
			return WorkcenterResult.custom().setNO(WorkcenterCodeEnum.valueOf(NO_ROLE_ADD)).build();
		}
	}
	@Override
	public Object getRoleByRoleid(Long roleid) {
		Role role = roleMapper.selectByPrimaryKey(roleid);
		if(role!=null && role.getId() >0) {
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_ROLE_GET), role).build();
		} else {
			return WorkcenterResult.custom().setNO(WorkcenterCodeEnum.valueOf(NO_ROLE_GET)).build();
		}
	}
	@Override
	public Object editRole(Role role) {
		int re = roleMapper.updateByPrimaryKeySelective(role);
		if(re>0){
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_ROLE_UPDATE)).build();
		} else {
			return WorkcenterResult.custom().setNO(WorkcenterCodeEnum.valueOf(NO_ROLE_UPDATE)).build();
		}
	}
	@Override
	public Object forbiddenRoles(String roleids) {
		String[] arrayId = roleids.split(",");
		for(int i=0;i<arrayId.length;i++) {
			forbiddenRole(Long.parseLong(arrayId[i]));
		}
		return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_ROLE_FORBIDDEN)).build();
	}
	
	private WorkcenterResult forbiddenRole(Long id) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("id", id);
		parameterMap.put("status", 0);
		int re = roleMapper.updateRoleStatusById(parameterMap);
		if(re>0){
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_ROLE_FORBIDDEN)).build();
		} else {
			return WorkcenterResult.custom().setNO(WorkcenterCodeEnum.valueOf(NO_ROLE_FORBIDDEN)).build();
		}
	}
	
	@Override
	public Object deleteRoles(String roleids) {
		String[] arrayId = roleids.split(",");
		for(int i=0;i<arrayId.length;i++) {
			deleteRole(Long.parseLong(arrayId[i]));
		}
		return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_ROLE_DELETE)).build();
	}

	private WorkcenterResult deleteRole(Long id) {
		int re = roleMapper.deleteByPrimaryKey(id);
		if(re>0){
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_ROLE_DELETE)).build();
		} else {
			return WorkcenterResult.custom().setNO(WorkcenterCodeEnum.valueOf(NO_ROLE_DELETE)).build();
		}
	}
	
	@Override
	public List<Role> queryRolesByUserId(Long id) {
		// TODO Auto-generated method stub
		return roleMapper.queryRolesByUserId(id);
	}

	@Override
	public List<Role> queryKpiDefaultRoles() {
		Role workcenterRole = roleMapper.selectByPrimaryKey(WORKCENTER_ROLE_ID);
		Role kpiRole = roleMapper.selectByPrimaryKey(KPI_ROLE_ID);
		List<Role> roles = new ArrayList<Role>();
		roles.add(workcenterRole);
		roles.add(kpiRole);
		return roles;
	}
	
	@Override
	public List<Role> getRolesByUserid(Long userId) {
		List<Role> rolesList = new ArrayList<Role>();
		
		Set<Role> rolesSet = new HashSet<Role>();
		List<Role> userRoles = roleMapper.queryRolesByUserid(userId);
		List<Role> allRoles = roleMapper.queryAllRoles();
		rolesSet.addAll(userRoles);
		rolesSet.addAll(allRoles);
		
		rolesList.addAll(rolesSet);
		Collections.sort(rolesList);
		return rolesList;
	}
	
	@Override
	public Object updateUserRoles(Long userId, Long[] roleId) {
		
		userRoleMapper.deleteUserRoleByUserId(userId);
		
		for(int i=0;i<roleId.length;i++) {
			Long roleid = roleId[i];
			UserRole userRole = new UserRole();
			userRole.setUserId(userId);
			userRole.setRoleId(roleid);
			userRole.setCreateTime(new Date());
			userRole.setCreateUserId(userService.getUserId());
			userRole.setStatus(OK_STATUS);
			userRoleMapper.insertSelective(userRole);
		}
		
		return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_USERROLE_UPDATE)).build();
	}
}
