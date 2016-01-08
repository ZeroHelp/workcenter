package cn.workcenter.common;

import cn.workcenter.common.constant.WebConstant;

public enum WorkcenterOperationEnum implements WebConstant {
	//{001,workcenter 002,admin}
	LOGIN("0010001", WebConstant.LOGIN, "用户登录"),
	LOGOUT("0010002", WebConstant.LOGOUT, "用户退出登录"),
	
	USER_ADD("0020001", WebConstant.USER_ADD, "用户添加"),
	USER_GET("0020002", WebConstant.USER_GET, "用户获取"),
	USER_EDIT("0020003", WebConstant.USER_EDIT, "用户更新"),
	USER_FORBIDDEN("0020004", WebConstant.USER_FORBIDDEN, "用户禁用"),
	USER_DELETE("0020005", WebConstant.USER_DELETE, "用户删除"),
	
	ROLE_ADD("0020006", WebConstant.ROLE_ADD, "角色添加"),
	ROLE_GET("0020007", WebConstant.ROLE_GET, "角色获取"),
	ROLE_EDIT("0020008", WebConstant.ROLE_EDIT, "角色获取"),
	ROLE_FORBIDDEN("0020009", WebConstant.ROLE_FORBIDDEN, "角色获取"),
	ROLE_DELETE("0020010", WebConstant.ROLE_DELETE, "角色获取"),
	
	RESOURCE_ADD("0020011", WebConstant.RESOURCE_ADD, "资源添加"),
	RESOURCE_GET("0020012", WebConstant.RESOURCE_GET, "资源获取"),
	RESOURCE_EDIT("0020013", WebConstant.RESOURCE_EDIT, "资源获取"),
	RESOURCE_FORBIDDEN("0020014", WebConstant.RESOURCE_FORBIDDEN, "资源获取"),
	RESOURCE_DELETE("0020015", WebConstant.RESOURCE_DELETE, "资源获取"),
	
	GROUP_ADD("0020016", WebConstant.GROUP_ADD, "用户组添加"),
	GROUP_GET("0020017", WebConstant.GROUP_GET, "用户组获取"),
	GROUP_EDIT("0020018", WebConstant.GROUP_EDIT, "用户组获取"),
	GROUP_FORBIDDEN("0020019", WebConstant.GROUP_FORBIDDEN, "用户组获取"),
	GROUP_DELETE("0020020", WebConstant.GROUP_DELETE, "用户组获取"),
	
	USER_UPDATEROLE("0020021", WebConstant.USER_UPDATEROLE, "用户角色更改"),
	USER_UPDATEGROUP("0020022", WebConstant.USER_UPDATEGROUP, "用户用户组更改"),
	ROLE_UPDATERESOURCE("0020023", WebConstant.ROLE_UPDATERESOURCE, "角色资源更改"),
	;
	
	String operationCode;
	String operationEn;
	String operationCh;
	
	WorkcenterOperationEnum(String code, String en, String ch) {
		operationCode = code;
		operationEn = en;
		operationCh = ch;
	}
	
	public String getOperationCode() {
		return operationCode;
	}
	public String getOperationEn() {
		return operationEn;
	}

	public String getOperationCh() {
		return operationCh;
	}
	
}
