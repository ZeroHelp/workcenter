package cn.workcenter.kpi.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectUtil {

	public static void setValue(Object targetObj, String attributeName, Object value) {
		try {
			Method setMethod = targetObj.getClass().getMethod(getMethodnameByAttributename(attributeName), value.getClass());
			setMethod.invoke(targetObj, value);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}

	private static String getMethodnameByAttributename(String attributeName) {
		String prefix = "set";
		String suffix = getFirstUpperString(attributeName);
		return prefix + suffix;
	}

	private static String getFirstUpperString(String attributeName) {
		String firstUpperString = attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
		return firstUpperString;
	}

}
