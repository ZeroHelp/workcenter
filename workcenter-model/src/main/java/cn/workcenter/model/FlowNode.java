package cn.workcenter.model;

public class FlowNode implements Cloneable{
    private Long id;

    private Long processdefinitionId;

    private Integer indexNum;

    private String name;

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
    private String type;

    private String enterClassName;

    private String leaveClassName;

    private Long taskId;

    private Long decisionDelegationId;

    private String decisionExpression;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProcessdefinitionId() {
        return processdefinitionId;
    }

    public void setProcessdefinitionId(Long processdefinitionId) {
        this.processdefinitionId = processdefinitionId;
    }

    public Integer getIndexNum() {
        return indexNum;
    }

    public void setIndexNum(Integer indexNum) {
        this.indexNum = indexNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEnterClassName() {
        return enterClassName;
    }

    public void setEnterClassName(String enterClassName) {
        this.enterClassName = enterClassName;
    }

    public String getLeaveClassName() {
        return leaveClassName;
    }

    public void setLeaveClassName(String leaveClassName) {
        this.leaveClassName = leaveClassName;
    }

    public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getDecisionDelegationId() {
        return decisionDelegationId;
    }

    public void setDecisionDelegationId(Long decisionDelegationId) {
        this.decisionDelegationId = decisionDelegationId;
    }

    public String getDecisionExpression() {
        return decisionExpression;
    }

    public void setDecisionExpression(String decisionExpression) {
        this.decisionExpression = decisionExpression;
    }
    
    public FlowNode clone(){
    	try {
			super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
    }
}