package cn.workcenter.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.workcenter.admin.model.Menu;
import cn.workcenter.admin.service.MenuService;
import cn.workcenter.common.constant.Constant;

@Controller("adminController")
public class AdminController implements Constant {
	
	@Autowired
	private MenuService menuService;			
	@RequestMapping(value="{sid}/admin/home", method=RequestMethod.GET)
	public Object home(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response) {
		
		List<Menu> menus = menuService.getMenuTree(MENU_RESOURCE_FLAG);
		
		request.setAttribute("menus", menus);
		request.setAttribute("viewPage", "home.jsp");
		
		return "admin/main";
	}
	
	@RequestMapping(value="{sid}/admin/kpi/home", method=RequestMethod.GET)
	public Object kpihome(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response) {
		
		List<Menu> menus = menuService.getMenuTree(MENU_RESOURCE_FLAG);
		
		request.setAttribute("menus", menus);
		request.setAttribute("viewPage", "kpi/initKpi.jsp");
		
		return "admin/main";
	}
	
	
}
