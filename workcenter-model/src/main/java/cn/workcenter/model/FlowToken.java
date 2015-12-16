package cn.workcenter.model;

import java.util.Date;

public class FlowToken {
    private Long id;

    private Date startTime;

    private Date endTime;

    private Date nodeEnterTime;

    private Integer isSuspended;

    private Long nodeId;

    private Long processinstanceId;

    private Long siblingTokenId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getNodeEnterTime() {
        return nodeEnterTime;
    }

    public void setNodeEnterTime(Date nodeEnterTime) {
        this.nodeEnterTime = nodeEnterTime;
    }

    public Integer getIsSuspended() {
        return isSuspended;
    }

    public void setIsSuspended(Integer isSuspended) {
        this.isSuspended = isSuspended;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Long getProcessinstanceId() {
        return processinstanceId;
    }

    public void setProcessinstanceId(Long processinstanceId) {
        this.processinstanceId = processinstanceId;
    }

    public Long getSiblingTokenId() {
        return siblingTokenId;
    }

    public void setSiblingTokenId(Long siblingTokenId) {
        this.siblingTokenId = siblingTokenId;
    }
}