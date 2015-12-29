package cn.workcenter.kpi.model;

import java.math.BigDecimal;

public class EnactmentSelf {
    private Long id;

    private Long kpiMainId;

    private Integer indexNum;

    private String selfDirection;

    private BigDecimal selfWeight;

    private BigDecimal selfScore;

    private BigDecimal leaderScore;

    private Integer status;

    
    public Long getId() {
        return id;
    }
    
    public void setSelfId(Long id) {
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

    public String getSelfDirection() {
        return selfDirection;
    }

    public void setSelfDirection(String selfDirection) {
        this.selfDirection = selfDirection;
    }

    public BigDecimal getSelfWeight() {
        return selfWeight;
    }

    public void setSelfWeight(BigDecimal selfWeight) {
        this.selfWeight = selfWeight;
    }

    public BigDecimal getSelfScore() {
        return selfScore;
    }

    public void setSelfScore(BigDecimal selfScore) {
        this.selfScore = selfScore;
    }

    public BigDecimal getLeaderScore() {
        return leaderScore;
    }

    public void setLeaderScore(BigDecimal leaderScore) {
        this.leaderScore = leaderScore;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}