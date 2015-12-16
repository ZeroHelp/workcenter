package cn.workcenter.model;

public class FlowSwimlaneUser {
    private Long id;

    private Long userId;

    private Long swimlanceId;

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

    public Long getSwimlanceId() {
        return swimlanceId;
    }

    public void setSwimlanceId(Long swimlanceId) {
        this.swimlanceId = swimlanceId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}