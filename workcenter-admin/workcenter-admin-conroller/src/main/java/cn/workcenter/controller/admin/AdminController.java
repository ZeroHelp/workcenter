package cn.workcenter.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.workcenter.admin.model.Menu;
import cn.workcenter.admin.service.AdminKpiService;
import cn.workcenter.admin.service.MenuService;
import cn.workcenter.common.constant.Constant;
import cn.workcenter.common.response.WorkcenterResponseBodyJson;

@Controller("adminController")
public class AdminController implements Constant {
	
	@Autowired
	private MenuService menuService;
	@Autowired
	private AdminKpiService adminKpiService;
	
	@RequestMapping(value="{sid}/admin/home", method=RequestMethod.GET)
	public Object home(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response) {
		
		List<Menu> menus = menuService.getMenuTree(MENU_RESOURCE_FLAG);
		
		request.setAttribute("menus", menus);
		request.setAttribute("viewPage", "home.jsp");
		
		return "admin/main";
	}
	
	@RequestMapping(value="{sid}/admin/kpi/list", method=RequestMethod.GET)
	public Object kpilist(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response) {
		
		List<Menu> menus = menuService.getMenuTree(MENU_RESOURCE_FLAG);
		
		request.setAttribute("menus", menus);
		request.setAttribute("viewPage", "kpi/list.jsp");
		
		return "admin/main";
	}
	
	@RequestMapping(value="{sid}/admin/kpi/init", method=RequestMethod.POST)
	@ResponseBody
	public Object init(@PathVariable String sid, 
			@RequestParam String year, @RequestParam String remark, HttpServletRequest request, HttpServletResponse response) {
		
		adminKpiService.initKpi(year, remark);
		
		return WorkcenterResponseBodyJson.custom().build();
	}
	
	
}
