package cn.workcenter.kpi.common;

import cn.workcenter.kpi.common.threadLocal.RequestThreadLocal;

public abstract class KpiApplication implements Constant {
	public static RequestThreadLocal requestThreadLocal = new RequestThreadLocal();
}
