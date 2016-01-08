package cn.workcenter.model;

import java.util.Date;

public class Role implements Nameable, Comparable<Role> {
    private Long id;

    private String roleName;

    private String roleChName;

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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleChName() {
        return roleChName;
    }

    public void setRoleChName(String roleChName) {
        this.roleChName = roleChName;
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
	public String getName() {
		return roleName;
	}

	@Override
	public int compareTo(Role o) {
		Long oId = o.getId();
		if(id >= oId) return 1;
		
		return -1;
	}
}