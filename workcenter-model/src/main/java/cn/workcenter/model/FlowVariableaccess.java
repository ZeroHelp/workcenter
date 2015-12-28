package cn.workcenter.model;

public class FlowVariableaccess {
    private Long id;

    private Long nodeId;

    private Long variableinstanceId;

    private String variablename;
    /**
     * 流程变量访问方式：1read ,2write,3readwrite
     */
    private Integer access;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Long getVariableinstanceId() {
        return variableinstanceId;
    }

    public void setVariableinstanceId(Long variableinstanceId) {
        this.variableinstanceId = variableinstanceId;
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
}