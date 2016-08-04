package cn.workcenter.model;

import java.util.Date;

public class Resource implements Nameable , Comparable<Resource> {
    private Long id;

    private Long parentId;

    private String resourceName;

    private String resourceUrl;

    private Date createTime;

    private Long createUserid;

    private Integer status;
    
    private Integer checked;
    //vo
    private String icons;
    
    public String getIcons() {
		return icons;
	}

	public void setIcons(String icons) {
		this.icons = icons;
	}

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

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
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
	public String getName() {
		return resourceName;
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
	public int compareTo(Resource o) {
		Long oId = o.getId();
		if(id >= oId) return 1;
		
		return -1;
	}
}