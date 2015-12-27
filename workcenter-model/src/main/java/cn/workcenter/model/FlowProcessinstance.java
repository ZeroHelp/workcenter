package cn.workcenter.model;

import java.util.Date;

public class FlowProcessinstance {
    private Long id;

    private Date startTime;

    private Date endTime;

    private Integer isSuspended;
    
    private Integer isFiled;

    private Long processdefinitionId;

    private Long rootTokenId;

    private Long swimlaneId;
    
    public Integer getIsFiled() {
		return isFiled;
	}

	public void setIsFiled(Integer isFiled) {
		this.isFiled = isFiled;
	}

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

    public Integer getIsSuspended() {
        return isSuspended;
    }

    public void setIsSuspended(Integer isSuspended) {
        this.isSuspended = isSuspended;
    }

    public Long getProcessdefinitionId() {
        return processdefinitionId;
    }

    public void setProcessdefinitionId(Long processdefinitionId) {
        this.processdefinitionId = processdefinitionId;
    }

    public Long getRootTokenId() {
        return rootTokenId;
    }

    public void setRootTokenId(Long rootTokenId) {
        this.rootTokenId = rootTokenId;
    }

    public Long getSwimlaneId() {
        return swimlaneId;
    }

    public void setSwimlaneId(Long swimlaneId) {
        this.swimlaneId = swimlaneId;
    }
}