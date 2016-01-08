package cn.workcenter.common;

import cn.workcenter.common.constant.WebConstant;

public enum WorkcenterCodeEnum implements WebConstant{
	//{1:error 2:success}{001:workcenter model}{0001:workcenter first-error}
	NO_RUNTIMEEXCEPTION("10010000", "RuntimeException"),
	NO_INVALID_SID("10010002","sid失效或非法"),
	NO_WRONGPASSWORD("10010003","登录失败,密码错误"),
	NO_EXISTUSER("10010004", "用户不存在"),
	
	NO_USER_ADD("10020001", "用户添加失败"),
	NO_USER_GET("10020002", "用户查询失败"),
	NO_USER_UPDATE("10020003", "用户更新失败"),
	NO_USER_FORBIDDEN("10020004", "用户禁用失败"),
	NO_USER_DELETE("10020005", "用户删除失败"),
	NO_ROLE_ADD("10020006", "角色添加失败"),
	NO_ROLE_GET("10020007", "角色获取失败"),
	NO_ROLE_UPDATE("10020008", "角色更新失败"),
	NO_ROLE_FORBIDDEN("10020009", "角色禁止失败"),
	NO_ROLE_DELETE("10020010", "角色删除失败"),
	NO_RESOURCE_ADD("10020011", "资源添加失败"),
	NO_RESOURCE_GET("10020012", "资源获取失败"),
	NO_RESOURCE_UPDATE("10020013", "资源更新失败"),
	NO_RESOURCE_FORBIDDEN("10020014", "资源禁止失败"),
	NO_RESOURCE_DELETE("10020015", "资源删除失败"),
	NO_GROUP_ADD("10020016", "用户组添加失败"),
	NO_GROUP_GET("10020017", "用户组获取失败"),
	NO_GROUP_UPDATE("10020018", "用户组更新失败"),
	NO_GROUP_FORBIDDEN("10020019", "用户组禁止失败"),
	NO_GROUP_DELETE("10020020", "用户组删除失败"),
	NO_CHANGE_PASSWORD("10020021", "更换密码失败"),
	NO_OLDPASSWORD_WRONG("10020022", "更换密码失败,旧密码校验失败"),
	NO_FVI_ADD("10020023", "流程变量添加失败"),
	NO_FVI_UPDATE("10020024", "流程变量更新失败"),
	NO_FVI_DELETE("10020025", "流程变量删除失败"),
	NO_FVI_GET("10020026", "流程变量查询失败"),
	NO_FVI_FORBIDDEN("10020027", "流程变量禁用失败"),
	NO_FVA_ADD("10020028", "流程变量权限添加失败"),
	NO_FVA_UPDATE("10020029", "流程变量权限更新失败"),
	NO_FVA_DELETE("10020030", "流程变量权限删除失败"),
	NO_FVA_GET("10020031", "流程变量权限查询失败"),
	NO_FVA_FORBIDDEN("10020032", "流程变量权限禁用失败"),
	
	OK_SUCCESS("20010000", "SUCCESS"),
	OK_LOGOUT("20010001", "退出登录成功"),
	OK_LOGIN("20010003", "登录成功"),
	
	OK_USER_ADD("20020001", "用户添加成功"),
	OK_USER_UPDATE("20020002", "用户更新成功"),
	OK_USER_DELETE("20020003", "用户删除成功"),
	OK_USER_GET("20020004", "用户查询成功"),
	OK_USER_FORBIDDEN("20020005", "用户禁用成功"),
	OK_ROLE_ADD("20020006", "角色添加成功"),
	OK_ROLE_GET("20020007", "角色获取成功"),
	OK_ROLE_UPDATE("20020008", "角色更新成功"),
	OK_ROLE_FORBIDDEN("20020009", "角色禁止成功"),
	OK_ROLE_DELETE("20020010", "角色删除成功"),
	OK_RESOURCE_ADD("20020011", "资源添加成功"),
	OK_RESOURCE_GET("20020012", "资源获取成功"),
	OK_RESOURCE_UPDATE("20020013", "资源更新成功"),
	OK_RESOURCE_FORBIDDEN("20020014", "资源禁止成功"),
	OK_RESOURCE_DELETE("20020015", "资源删除成功"),
	OK_GROUP_ADD("20020016", "用户组添加成功"),
	OK_GROUP_GET("20020017", "用户组获取成功"),
	OK_GROUP_UPDATE("20020018", "用户组更新成功"),
	OK_GROUP_FORBIDDEN("20020019", "用户组禁止成功"),
	OK_GROUP_DELETE("20020020", "用户组删除成功"),
	OK_CHANGE_PASSWORD("20020021", "更换密码成功"),
	OK_FVI_ADD("20020023", "流程变量添加成功"),
	OK_FVI_UPDATE("20020024", "流程变量更新成功"),
	OK_FVI_DELETE("20020025", "流程变量删除成功"),
	OK_FVI_GET("20020026", "流程变量查询成功"),
	OK_FVI_FORBIDDEN("20020027", "流程变量禁用成功"),
	OK_FVA_ADD("20020028", "流程变量权限添加成功"),
	OK_FVA_UPDATE("20020029", "流程变量权限更新成功"),
	OK_FVA_DELETE("20020030", "流程变量权限删除成功"),
	OK_FVA_GET("20020031", "流程变量权限查询成功"),
	OK_FVA_FORBIDDEN("20020032", "流程变量权限禁用成功"),
	OK_USERROLE_UPDATE("20020033", "用户角色更新成功"),
	OK_USERGROUP_UPDATE("20020034", "用户用户组更新成功"),
	OK_ROLERESOURCE_UPDATE("20020035", "角色资源更新成功"),
	;
	
	private String code;
	private String msg;
	WorkcenterCodeEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public String getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	
	public String getStatus() {
		if(this.getCode().indexOf("2")==0) {
			return SUCCESS;
		} 
		return FAILURE;
	}
}
