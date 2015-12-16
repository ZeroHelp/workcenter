package cn.workcenter.model;

public class FlowProcessdefinition {
    private Long id;

    private String name;

    private Long startnodeId;

    private String version;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStartnodeId() {
        return startnodeId;
    }

    public void setStartnodeId(Long startnodeId) {
        this.startnodeId = startnodeId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}