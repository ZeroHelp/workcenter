package cn.workcenter.model;

public class FlowTask {
    private Long id;

    private String name;

    private Long processdefinitionId;

    private String description;

    /**
     * 	1.起草人
		2.起草人直属领导
		3.某角色所有的人 假如角色一个人则直接设置
     */
    private Integer actorType;

    private Long roleId;
    
    private Long groupId;

    private Long nodeId;

    private Long startNodeId;

    public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

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

	public Long getProcessdefinitionId() {
		return processdefinitionId;
	}

	public void setProcessdefinitionId(Long processdefinitionId) {
		this.processdefinitionId = processdefinitionId;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getActorType() {
        return actorType;
    }

    public void setActorType(Integer actorType) {
        this.actorType = actorType;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Long getStartNodeId() {
        return startNodeId;
    }

    public void setStartNodeId(Long startNodeId) {
        this.startNodeId = startNodeId;
    }
}