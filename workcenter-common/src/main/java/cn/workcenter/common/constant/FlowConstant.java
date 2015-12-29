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
}
