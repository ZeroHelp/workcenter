package cn.workcenter.model;

import java.util.Date;

public class Group implements Nameable, Comparable<Group> {
    private Long id;

    private Long parentId;

    private String groupName;

    private String groupChName;

    private String type;

    private Date createTime;

    private Long createUserid;

    private Integer status;
    
    private Integer checked;
    
    public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupChName() {
        return groupChName;
    }

    public void setGroupChName(String groupChName) {
        this.groupChName = groupChName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUserid() {
        return createUserid;
    }

    public void setCreateUserid(Long createUserid) {
        this.createUserid = createUserid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    @Override
    public int hashCode() {
    	return this.getName()==null?0:this.getName().hashCode();
    };
    
    @Override
    public boolean equals(Object o) {
    	if(o == null&& this == null) return true;
    	if(o == null ) return false;
    	
    	if(o instanceof Nameable) {
    		String oName = ((Nameable) o).getName()==null?"":((Nameable) o).getName();
    		String name = this.getName()==null?"":this.getName();
    		return name.equals(oName);
    	}
		return false;
    	
    }
    
    @Override
	public int compareTo(Group o) {
		Long oId = o.getId();
		if(id >= oId) return 1;
		
		return -1;
	}
    
    @Override
	public String getName() {
		return groupName;
	}
}