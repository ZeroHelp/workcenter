package cn.workcenter.model;

public class FlowNode {
    private Long id;

    private Long processdefinitionId;

    private Integer indexNum;

    private String name;

    private String type;

    private String enterClassName;

    private String leaveClassName;

    private Long taskid;

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

    public Long getTaskid() {
        return taskid;
    }

    public void setTaskid(Long taskid) {
        this.taskid = taskid;
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
}