package cn.workcenter.model;

public class FlowSwimlaneUser {
    private Long id;

    private Long userId;

    private Long swimlaneId;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSwimlaneId() {
		return swimlaneId;
	}

	public void setSwimlaneId(Long swimlaneId) {
		this.swimlaneId = swimlaneId;
	}

	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}