package cn.workcenter.common.response;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.alibaba.fastjson.JSON;

import cn.workcenter.common.Loader;
import cn.workcenter.common.WorkcenterResult;
import cn.workcenter.common.constant.WebConstant;
import cn.workcenter.common.exception.ParameterEmptyException;


public class WorkcenterResponseBodyJson implements ResponseBody , WebConstant{
	
	public final String returncode; //200 成功    500服务器错误 600执行业务异常
	public final String returnmsg; //请求成功        服务器错误     执行业务异常
	public final String returnmemo;//{SUCCESS/FAILURE}:{errorcode}:{errormsg}
	public final Object data;//附加传送到web端值
	public final String operator;
	
	private WorkcenterResponseBodyJson() {
		returncode = "";
		returnmsg = "";
		returnmemo = "";
		operator = "";
		data = null;
	}
	
	private WorkcenterResponseBodyJson(String returncode, String returnmsg, String returnmemo, Object data, String operator) {
		this.returncode = returncode;
		this.returnmsg = returnmsg;
		this.returnmemo = returnmemo;
		this.operator = operator;
		this.data = data;
	}
	
	public static WorkcenterResponseBodyJson.Builder custom() {
		return new Builder();
	}
	
	public static class Builder {
		private String returncode;
		private String returnmsg;
		private String returnmemo;
		private String operator;
		private Object data;
		
		Builder() {
			super();
			this.returncode = "200";
			this.returnmsg = "请求成功";
			this.returnmemo = "SUCCESS:200000:请求成功";
			this.operator = "nothing";
			this.data = "附加信息";
		}
		
		public WorkcenterResponseBodyJson.Builder setReturncode(String returncode) {
			this.returncode = returncode;
			return this;
		}

		public WorkcenterResponseBodyJson.Builder setReturnmsg(String returnmsg) {
			this.returnmsg = returnmsg;
			return this;
		}

		public WorkcenterResponseBodyJson.Builder setReturnmemo(String returnmemo) {
			this.returnmemo = returnmemo;
			return this;
		}
		
		public WorkcenterResponseBodyJson.Builder setOperator(String operator) {
			this.operator = operator;
			return this;
		}
		
		public Builder setAll(ParameterEmptyException e) {
			this.returncode = "500";
			this.returnmsg = "服务器错误";
			this.returnmemo = "FAILURE:10010000:ParameterEmptyException" + COLON + e.getMessage();
			this.operator = null;
			this.data = null;
			return this;
		}
		public WorkcenterResponseBodyJson.Builder setAll(RuntimeException e, String operator) {
			this.returncode = "500";
			this.returnmsg = "服务器错误";
			this.returnmemo = "FAILURE:10010000:RuntimeException" + COLON + e.getMessage();
			this.operator = operator;
			this.data = null;
			return this;
		}
		
		public WorkcenterResponseBodyJson.Builder setAll(WorkcenterResult result, String operator) {
			this.returncode = result.getResultCode();
			this.returnmsg = result.getResultMsg();
			this.returnmemo = result.getCodeEnum().getStatus() + COLON+result.getCodeEnum().getCode() + COLON+result.getCodeEnum().getMsg();
			this.operator = operator;
			this.data = result.getData();
			return this;
		}
		
		public WorkcenterResponseBodyJson.Builder setAll(WorkcenterResult result,  String operator, Object data) {
			this.returncode = result.getResultCode();
			this.returnmsg = result.getResultMsg();
			this.returnmemo = result.getCodeEnum().getStatus() + COLON+result.getCodeEnum().getCode() + COLON+result.getCodeEnum().getMsg();
			this.operator = operator;
			this.data = data;
			return this;
		}
		
		public WorkcenterResponseBodyJson.Builder setAll(String returncode, String returnmsg, String returnmemo, String operator , Object data){
			this.returncode = returncode;
			this.returnmsg = returnmsg;
			this.returnmemo = returnmemo;
			this.operator = operator;
			this.data = data;
			return this;
		}

		public WorkcenterResponseBodyJson build() {
			return new WorkcenterResponseBodyJson(returncode, returnmsg, returnmemo, data, operator);
		}

	}

	public String getReturncode() {
		return returncode;
	}

	public String getReturnmsg() {
		return returnmsg;
	}

	public String getReturnmemo() {
		return returnmemo;
	}
	
	public String getOperator() {
		return operator;
	}
	
	public Object getData() {
		return data;
	}
	
	@Override
	public String toString() {
		String json = JSON.toJSONString(this);
		return json;
	}
	
	public WorkcenterResponseBodyJson log() {
		Class logClass = null;
		try {
			logClass = Loader.getClass("cn.godzilla.util.GodzillaWebApplication");
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			System.out.println("GodzillaWebApplication.logThenReturn 更新日志失败 类错误");
			return this;
		}
		Method dologmethod;
		try {
			dologmethod = logClass.getMethod("logThenReturn", WorkcenterResponseBodyJson.class);
			dologmethod.invoke(logClass, this);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException |NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			System.out.println("GodzillaWebApplication.logThenReturn 更新日志失败　方法错误");
			return this;
		}
		return this;
	}

	public Object updateLog() {
		Class logClass = null;
		try {
			logClass = Loader.getClass("cn.godzilla.util.GodzillaWebApplication");
		} catch(ClassNotFoundException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			System.out.println("GodzillaWebApplication.updateLogThenReturn 更新日志失败 类错误");
			return this;
		}
		Method dologmethod;
		try{
			dologmethod = logClass.getMethod("updateLogThenReturn", WorkcenterResponseBodyJson.class);
			dologmethod.invoke(logClass, this);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException |NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			System.out.println("GodzillaWebApplication.updateLogThenReturn 更新日志失败　方法错误");
			return this;
		}
		return this;
	}
	
}
