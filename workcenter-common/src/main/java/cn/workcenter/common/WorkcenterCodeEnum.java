package cn.workcenter.common;

import cn.workcenter.common.constant.WebConstant;

public enum WorkcenterCodeEnum implements WebConstant{
	//{1:error 2:success}{001:workcenter model}{0001:workcenter first-error}
	NO_RUNTIMEEXCEPTION("10010000", "RuntimeException"),
	NO_INVALID_SID("10010002","sid失效或非法"),
	NO_WRONGPASSWORD("10010003","登录失败,密码错误"),
	NO_EXISTUSER("10010004", "用户不存在"),
	
	
	OK_SUCCESS("20010000", "SUCCESS"),
	OK_LOGOUT("20010001", "退出登录成功"),
	OK_LOGIN("20010003", "登录成功"),
	
	
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
