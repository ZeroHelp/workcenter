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
	//user model
	String USER_ADD = "USER_ADD";
	String USER_GET = "USER_GET";
	String USER_EDIT = "USER_EDIT";
	String USER_FORBIDDEN = "USER_FORBIDDEN";
	String USER_DELETE = "USER_DELETE";
	//FlowVariableinstance
	String FVI_ADD = "FVI_ADD";
	String FVI_GET = "FVI_GET";
	String FVI_EDIT = "FVI_EDIT";
	String FVI_FORBIDDEN = "FVI_FORBIDDEN";
	String FVI_DELETE = "FVI_DELETE";
	
	String NO_FVI_ADD = "NO_FVI_ADD";
	String NO_FVI_UPDATE = "NO_FVI_UPDATE";
	String NO_FVI_DELETE = "NO_FVI_DELETE";
	String NO_FVI_GET = "NO_FVI_GET";
	String NO_FVI_FORBIDDEN = "NO_FVI_FORBIDDEN";
	
	String OK_FVI_ADD = "OK_FVI_ADD";
	String OK_FVI_UPDATE = "OK_FVI_UPDATE";
	String OK_FVI_DELETE = "OK_FVI_DELETE";
	String OK_FVI_GET = "OK_FVI_GET";
	String OK_FVI_FORBIDDEN = "OK_FVI_FORBIDDEN";
	
	//FlowVariableAccess
	String FVA_ADD = "FVA_ADD";
	String FVA_GET = "FVA_GET";
	String FVA_EDIT = "FVA_EDIT";
	String FVA_FORBIDDEN = "FVA_FORBIDDEN";
	String FVA_DELETE = "FVA_DELETE";
	
	String NO_FVA_ADD = "NO_FVA_ADD";
	String NO_FVA_UPDATE = "NO_FVA_UPDATE";
	String NO_FVA_DELETE = "NO_FVA_DELETE";
	String NO_FVA_GET = "NO_FVA_GET";
	String NO_FVA_FORBIDDEN = "NO_FVA_FORBIDDEN";
	
	String OK_FVA_ADD = "OK_FVA_ADD";
	String OK_FVA_UPDATE = "OK_FVA_UPDATE";
	String OK_FVA_DELETE = "OK_FVA_DELETE";
	String OK_FVA_GET = "OK_FVA_GET";
	String OK_FVA_FORBIDDEN = "OK_FVA_FORBIDDEN";
	
}
