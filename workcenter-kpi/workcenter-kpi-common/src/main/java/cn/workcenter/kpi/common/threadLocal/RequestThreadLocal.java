package cn.workcenter.kpi.common.threadLocal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import cn.workcenter.model.FlowVariableinstance;

public class RequestThreadLocal extends ThreadLocal<HttpServletRequest> {
	
	public void setALLAccesses(Map<String, Object> map) {
		Set<String> keyset = map.keySet();
		HttpServletRequest request = get();
		for(String key : keyset) {
			request.setAttribute(key, map.get(key));
		}
	}
	
	private static List<String> selfParams = new CopyOnWriteArrayList<String>() ;
	static {
		selfParams.add("selfId");
		selfParams.add("selfDirection");
		selfParams.add("selfGoal");
		selfParams.add("selfWeight");
		selfParams.add("selfEvaluate");
		selfParams.add("selfScore");
		selfParams.add("leaderEvaluation");
		selfParams.add("leaderScore");
	}
	
	private static List<String> culturalParams = new CopyOnWriteArrayList<String>() ;
	static {
		culturalParams.add("culturalId");
		culturalParams.add("taskDirection");
		culturalParams.add("taskContent");
		culturalParams.add("taskWeight");
		culturalParams.add("taskScore");
	}
	
	public String getSelfIdKey() {
		return selfParams.get(0);
	}
	
	public String getCulturalIdKey() {
		return culturalParams.get(0);
	}

	public List<Map<String, Object>> getSelfParameters(Map<String, FlowVariableinstance> flowvariableMap) {
		return getParametersByParamkeys(flowvariableMap, selfParams);
	}
	
	public List<Map<String, Object>> getCulturalParameters(Map<String, FlowVariableinstance> flowvariableMap) {
		return getParametersByParamkeys(flowvariableMap, culturalParams);
	}
	
	private List<Map<String, Object>> getParametersByParamkeys(Map<String, FlowVariableinstance> flowvariableMap, List<String> paramkeys) {
		HttpServletRequest request = get();
		List<Map<String, Object>> parameterMapList = new ArrayList<Map<String, Object>>();
		
		for(String parameterKey: paramkeys) {
			String[] paramArray = request.getParameterValues(parameterKey)==null?new String[0]:request.getParameterValues(parameterKey);
			for(int i=0;i<paramArray.length;i++) {
				String param = paramArray[i];
				Object paramObj = convert(param, flowvariableMap.get(parameterKey)==null?"":flowvariableMap.get(parameterKey).getVarType());
				Map<String, Object> parameterMap = getParameterMap(parameterMapList, i);
				parameterMap.put(parameterKey, paramObj);
			}
		}
		return parameterMapList;
	}

	private Map<String, Object> getParameterMap(List<Map<String, Object>> parameterMapList, int i) {
		Map<String, Object> parameterMap = null;
		try {
			parameterMap = parameterMapList.get(i);
		} catch(IndexOutOfBoundsException e) {
			parameterMap = new HashMap<String, Object>();
			parameterMapList.add(parameterMap);
		}
		return parameterMap;
	}

	private Object convert(String param, String varType) {
		Object obj;
		if(StringUtils.isEmpty(param)) {
			return null;
		}
		switch(varType) {
		case "S": 
			obj = param;
			break;
		case "L":
			obj = Long.parseLong(param);
			break;
		case "D":
			obj = Double.parseDouble(param);
			break;
		case "B":
			obj = new BigDecimal(param);
			break;
		case "I":
			obj = Integer.parseInt(param);
			break;
		default :
			obj = param;
			break;
		}
		return obj;
	}

}
