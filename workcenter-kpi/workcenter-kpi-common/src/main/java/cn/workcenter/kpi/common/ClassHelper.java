package cn.workcenter.kpi.common;

import java.util.Map;
import java.util.Set;

public class ClassHelper {

	public static void setAttributes(Object obj, Map<String, Object> parameterSelfMap) {
		Set<String> keySet = parameterSelfMap.keySet();
		for (String key : keySet) {
			setValueByAttributename(obj, key, parameterSelfMap.get(key));
		}
	}

	private static void setValueByAttributename(Object obj, String attributeName, Object value) {
		ReflectUtil.setValue(obj, attributeName, value);
	}
}
