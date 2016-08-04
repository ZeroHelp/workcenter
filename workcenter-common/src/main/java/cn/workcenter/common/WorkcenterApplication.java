package cn.workcenter.common;

import java.util.ArrayList;
import java.util.List;

import cn.workcenter.common.constant.WebConstant;

public abstract class WorkcenterApplication implements WebConstant {
	
	protected static ThreadLocal<String> sidThreadLocal = new ThreadLocal<String>(){
		protected String initialValue() {
			return "";
		}
	};
	
	public static List<String> iconsList = new ArrayList<String>();
	
	static {
		iconsList.add("Compas.png");
		iconsList.add("Map.png");
		iconsList.add("Clipboard.png");
	}
}
