package cn.workcenter.model;

public class FlowTransition {
    private Long id;

    private String name;

    private String type;

    private Long processdefinitionId;

    private Long fromNodeId;

    private Long toNodeId;

    private String conditionExpression;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getProcessdefinitionId() {
        return processdefinitionId;
    }

    public void setProcessdefinitionId(Long processdefinitionId) {
        this.processdefinitionId = processdefinitionId;
    }

    public Long getFromNodeId() {
        return fromNodeId;
    }

    public void setFromNodeId(Long fromNodeId) {
        this.fromNodeId = fromNodeId;
    }

    public Long getToNodeId() {
        return toNodeId;
    }

    public void setToNodeId(Long toNodeId) {
        this.toNodeId = toNodeId;
    }

    public String getConditionExpression() {
        return conditionExpression;
    }

    public void setConditionExpression(String conditionExpression) {
        this.conditionExpression = conditionExpression;
    }
}