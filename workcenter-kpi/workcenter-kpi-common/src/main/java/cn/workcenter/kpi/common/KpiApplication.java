package cn.workcenter.kpi.common;

import cn.workcenter.kpi.common.threadLocal.RequestThreadLocal;

public abstract class KpiApplication implements KpiConstant {
	public static RequestThreadLocal requestThreadLocal = new RequestThreadLocal();
}
