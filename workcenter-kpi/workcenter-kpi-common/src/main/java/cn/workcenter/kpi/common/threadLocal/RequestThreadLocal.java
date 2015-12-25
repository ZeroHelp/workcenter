package cn.workcenter.kpi.common.threadLocal;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class RequestThreadLocal extends ThreadLocal<HttpServletRequest> {
	
	public void setALLAccesses(Map<String, Object> map) {
		Set<String> keyset = map.keySet();
		HttpServletRequest request = get();
		for(String key : keyset) {
			request.setAttribute(key, map.get(key));
		}
	}
}
