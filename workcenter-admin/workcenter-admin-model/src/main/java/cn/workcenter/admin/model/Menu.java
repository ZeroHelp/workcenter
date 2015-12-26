package cn.workcenter.admin.model;

import java.util.ArrayList;
import java.util.List;


public class Menu {
	
	
	
	private String id;
	// -2:菜单标识
	private String rootFlag;
		
	private String parentId;
	private String menuName;
	private String url;
	private List<Menu> subMenus;
	
	public Menu(String id, String rootFlag, String parentId, String menuName, String url, List<Menu> subMenus) {
		this.id = id;
		this.rootFlag = rootFlag;
		this.parentId = parentId;
		this.menuName = menuName;
		this.url = url;
		this.subMenus = subMenus;
	}
	
	public static Menu createRootMenu(String menuResourceFlag) {
		return new Menu(menuResourceFlag, menuResourceFlag, null, "rootMenu", null, new ArrayList<Menu>());
	}
	
	public static Menu createMenu(Long id, String rootFlag, String parentId, String resourceName, String resourceUrl) {
		return new Menu(id+"", rootFlag, parentId, resourceName, resourceUrl, new ArrayList<Menu>());
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRootFlag() {
		return rootFlag;
	}
	public void setRootFlag(String rootFlag) {
		this.rootFlag = rootFlag;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<Menu> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(List<Menu> subMenus) {
		this.subMenus = subMenus;
	}

	
}
