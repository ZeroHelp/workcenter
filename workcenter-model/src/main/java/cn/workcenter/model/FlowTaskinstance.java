package cn.workcenter.model;

import java.util.Date;

public class FlowTaskinstance implements Cloneable {
    private Long id;

    private Long swimlaneId;

    private Long processinstanceId;

    private Long taskId;

    private String name;

    private String description;

    private Date createTime;

    private Date startTime;

    private Date endTime;

    private Date duedate;

    private Integer priority;

    private Integer isCancelled;

    private Integer isSuspended;

    /**
     * 0.未开始
     * 1.正在进行
     * 2.执行完毕
     */
    private Integer isOpen;

    private Integer isBlocking;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSwimlaneId() {
        return swimlaneId;
    }

    public void setSwimlaneId(Long swimlaneId) {
        this.swimlaneId = swimlaneId;
    }

    public Long getProcessinstanceId() {
        return processinstanceId;
    }

    public void setProcessinstanceId(Long processinstanceId) {
        this.processinstanceId = processinstanceId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getIsCancelled() {
        return isCancelled;
    }

    public void setIsCancelled(Integer isCancelled) {
        this.isCancelled = isCancelled;
    }

    public Integer getIsSuspended() {
        return isSuspended;
    }

    public void setIsSuspended(Integer isSuspended) {
        this.isSuspended = isSuspended;
    }

    public Integer getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }

    public Integer getIsBlocking() {
        return isBlocking;
    }

    public void setIsBlocking(Integer isBlocking) {
        this.isBlocking = isBlocking;
    }
    
    public FlowTaskinstance clone() {
    	try {
			return (FlowTaskinstance)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
    }

	public FlowTaskinstance getCurrentTaskinstance(Long processinstanceId2) {
		
		return null;
	}
}