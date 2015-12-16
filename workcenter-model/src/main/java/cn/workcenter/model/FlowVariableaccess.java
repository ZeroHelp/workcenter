package cn.workcenter.model;

public class FlowVariableaccess {
    private Long id;

    private String variablename;

    private Integer access;

    private Long nodeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVariablename() {
        return variablename;
    }

    public void setVariablename(String variablename) {
        this.variablename = variablename;
    }

    public Integer getAccess() {
        return access;
    }

    public void setAccess(Integer access) {
        this.access = access;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }
}