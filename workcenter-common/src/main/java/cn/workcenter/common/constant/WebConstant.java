package cn.workcenter.common.constant;

import cn.workcenter.common.util.StringUtil;

public interface WebConstant extends Constant{
	
	String COLON = ":";
	String SUCCESS = "SUCCESS";
	String FAILURE = "FAILURE";
	
	String DEFAULT_PASSWORD = StringUtil.MD5("111111");
	
	//----WorkcenterCodeEnum 
	
	String NO_INVALID_SID = "NO_INVALID_SID";
	String NO_RUNTIMEEXCEPTION = "NO_RUNTIMEEXCEPTION";
	String NO_WRONGPASSWORD = "NO_WRONGPASSWORD";
	String NO_EXISTUSER = "NO_EXISTUSER";
	String NO_USER_ADD = "NO_USER_ADD";
	String NO_USER_UPDATE = "NO_USER_UPDATE";
	String NO_USER_DELETE = "NO_USER_DELETE";
	String NO_USER_GET = "NO_USER_GET";
	String NO_USER_FORBIDDEN = "NO_USER_FORBIDDEN";
	
	String OK_SUCCESS = "OK_SUCCESS";
	String OK_LOGOUT = "OK_LOGOUT";
	String OK_LOGIN = "OK_LOGIN";
	String OK_USER_ADD = "OK_USER_ADD";
	String OK_USER_UPDATE = "OK_USER_UPDATE";
	String OK_USER_DELETE = "OK_USER_DELETE";
	String OK_USER_GET = "OK_USER_GET";
	String OK_USER_FORBIDDEN = "OK_USER_FORBIDDEN";
	//----WorkcenterOperationEnum englishname
	String LOGIN = "LOGIN";
	String LOGOUT = "LOGOUT";
	
	
}
