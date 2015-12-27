package cn.workcenter.kpi.common;

public interface KpiConstant {
	
	String BASE_PATH = "workcenter-web";
	
	int NOT_FILED = 0; 		//没有归档，流程未结束
	int FILED = 1;     		//归档，流程结束
	int NOT_OPENED = 0; 	//任务没有开启
	int OPENED = 1;			//待执行任务
	
}
