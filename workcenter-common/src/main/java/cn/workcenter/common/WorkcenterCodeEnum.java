package cn.workcenter.common;

import cn.workcenter.common.constant.WebConstant;

public enum WorkcenterCodeEnum implements WebConstant{
	//{1:error 2:success}{001:workcenter model}{0001:workcenter first-error}
	NO_RUNTIMEEXCEPTION("10010000", "RuntimeException"),
	NO_INVALID_SID("10010002","sid失效或非法"),
	NO_WRONGPASSWORD("10010003","登录失败,密码错误"),
	NO_EXISTUSER("10010004", "用户不存在"),
	
	NO_USER_ADD("10020001", "用户添加失败"),
	NO_USER_UPDATE("10020002", "用户更新失败"),
	NO_USER_DELETE("10020003", "用户删除失败"),
	NO_USER_GET("10020004", "用户查询失败"),
	NO_USER_FORBIDDEN("10020005", "用户禁用失败"),
	
	OK_SUCCESS("20010000", "SUCCESS"),
	OK_LOGOUT("20010001", "退出登录成功"),
	OK_LOGIN("20010003", "登录成功"),
	
	OK_USER_ADD("20020001", "用户添加成功"),
	OK_USER_UPDATE("20020002", "用户更新成功"),
	OK_USER_DELETE("20020003", "用户删除成功"),
	OK_USER_GET("20020004", "用户查询成功"),
	OK_USER_FORBIDDEN("20020005", "用户禁用成功"),
	
	NO_FVI_ADD("10020001", "流程变量添加失败"),
	NO_FVI_UPDATE("10020002", "流程变量更新失败"),
	NO_FVI_DELETE("10020003", "流程变量删除失败"),
	NO_FVI_GET("10020004", "流程变量查询失败"),
	NO_FVI_FORBIDDEN("10020005", "流程变量禁用失败"),

	
	OK_FVI_ADD("20020001", "流程变量添加成功"),
	OK_FVI_UPDATE("20020002", "流程变量更新成功"),
	OK_FVI_DELETE("20020003", "流程变量删除成功"),
	OK_FVI_GET("20020004", "流程变量查询成功"),
	OK_FVI_FORBIDDEN("20020005", "流程变量禁用成功"),
	
	NO_FVA_ADD("10020001", "流程变量权限添加失败"),
	NO_FVA_UPDATE("10020002", "流程变量权限更新失败"),
	NO_FVA_DELETE("10020003", "流程变量权限删除失败"),
	NO_FVA_GET("10020004", "流程变量权限查询失败"),
	NO_FVA_FORBIDDEN("10020005", "流程变量权限禁用失败"),

	
	OK_FVA_ADD("20020001", "流程变量权限添加成功"),
	OK_FVA_UPDATE("20020002", "流程变量权限更新成功"),
	OK_FVA_DELETE("20020003", "流程变量权限删除成功"),
	OK_FVA_GET("20020004", "流程变量权限查询成功"),
	OK_FVA_FORBIDDEN("20020005", "流程变量权限禁用成功"),
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
