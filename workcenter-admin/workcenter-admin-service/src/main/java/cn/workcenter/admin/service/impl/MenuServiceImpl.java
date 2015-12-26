package cn.workcenter.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.workcenter.admin.common.constant.AdminConstant;
import cn.workcenter.admin.model.Menu;
import cn.workcenter.admin.service.MenuService;
import cn.workcenter.model.Resource;
import cn.workcenter.service.ResourceService;

@Service("menuService")
public class MenuServiceImpl implements MenuService, AdminConstant {

	@Autowired
	private ResourceService resourceService;
	@Override
	public List<Menu> getMenuTree(String menuResourceFlag) {
		Menu rootMenu = Menu.createRootMenu(menuResourceFlag);
		
		List<Menu> submenus = getSubMenus(rootMenu, 0);
		
		return submenus;
	}

	private List<Menu> getSubMenus(Menu rootMenu, int depth) {
		if(depth >=MAX_MENU_DEPTH) {
			return null;
		}
		String subMenu_parentId = rootMenu.getId();
		List<Resource> resources = resourceService.getResoucesByParentid(subMenu_parentId);
		List<Menu> subMenus = new ArrayList<Menu>();
		for(Resource resource: resources) {
			Menu subMenu = Menu.createMenu(resource.getId(), rootMenu.getRootFlag(), rootMenu.getId(), resource.getResourceName(), resource.getResourceUrl());
			getSubMenus(subMenu, depth+1);
			subMenus.add(subMenu);
		}
		rootMenu.setSubMenus(subMenus);
		return rootMenu.getSubMenus();
	}
	
}
