package cn.workcenter.model;

public class FlowVariableinstance {
    private Long id;

    private Long processdefinitionId;

    /**
     * S:string
     * L:Long
     * D:Double
     * B:Boolen
     * I:Integer
     */
    private String varType;

    private String varValue;

    private Integer indexNum;

    private Integer status;

    private String varName;

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

	public String getVarType() {
        return varType;
    }

    public void setVarType(String varType) {
        this.varType = varType;
    }

    public String getVarValue() {
        return varValue;
    }

    public void setVarValue(String varValue) {
        this.varValue = varValue;
    }

    public Integer getIndexNum() {
        return indexNum;
    }

    public void setIndexNum(Integer indexNum) {
        this.indexNum = indexNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }
}