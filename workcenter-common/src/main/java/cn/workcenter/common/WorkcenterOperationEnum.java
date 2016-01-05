package cn.workcenter.common;

import cn.workcenter.common.constant.WebConstant;

public enum WorkcenterOperationEnum implements WebConstant {
	
	LOGIN("0010001", WebConstant.LOGIN, "用户登录"),
	LOGOUT("0010002", WebConstant.LOGOUT, "用户退出登录"),
			
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
