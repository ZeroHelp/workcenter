package cn.workcenter.kpi.model;

import java.math.BigDecimal;

public class EnactmentCultural {
    private Long id;

    private Long kpiMainId;

    private Integer indexNum;

    private String taskDirection;

    private Integer taskWeight;

    private BigDecimal taskScore;

    private Integer status;

    private String taskContent;

    public Long getId() {
        return id;
    }

    public void setCulturalId(Long id) {
    	this.id = id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getKpiMainId() {
        return kpiMainId;
    }

    public void setKpiMainId(Long kpiMainId) {
        this.kpiMainId = kpiMainId;
    }

    public Integer getIndexNum() {
        return indexNum;
    }

    public void setIndexNum(Integer indexNum) {
        this.indexNum = indexNum;
    }

    public String getTaskDirection() {
        return taskDirection;
    }

    public void setTaskDirection(String taskDirection) {
        this.taskDirection = taskDirection;
    }

    public Integer getTaskWeight() {
		return taskWeight;
	}

	public void setTaskWeight(Integer taskWeight) {
		this.taskWeight = taskWeight;
	}

	public BigDecimal getTaskScore() {
        return taskScore;
    }

    public void setTaskScore(BigDecimal taskScore) {
        this.taskScore = taskScore;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }
}