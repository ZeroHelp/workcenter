package cn.workcenter.common.constant;

public interface FlowConstant extends Constant {
	
	String VARIABLEACCESS_SUFFIX = "_access";
	
	/**
     * 节点类型 
     * D:  	Decision 
     * E:  	EndState
     * F:  	Fork  
     * J:	Join
     * K:	TaskNode
     * N:	Node
     * R:	StartState
     */
	String START_NODE = "S";
	String TASK_NODE = "T";
	String END_NODE = "E";
	
	int NOT_FILED = 0; 		//没有归档，流程未结束
	int FILED = 1;     		//归档，流程结束
	int NOT_OPENED = 0; 	//任务没有开启
	int OPENED = 1;			//待执行任务
	long VIEW_NODE_ID = 6;
}
