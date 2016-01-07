package cn.workcenter.controller.admin;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.workcenter.admin.model.Menu;
import cn.workcenter.admin.service.MenuService;
import cn.workcenter.common.WorkcenterResult;
import cn.workcenter.common.constant.Constant;
import cn.workcenter.common.constant.WebConstant;
import cn.workcenter.common.response.WorkcenterResponseBodyJson;
import cn.workcenter.model.Role;
import cn.workcenter.service.RoleService;
import cn.workcenter.service.UserService;

@Controller("adminRoleController")
public class RoleController implements Constant, WebConstant {
	
	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="{sid}/admin/role/list", method=RequestMethod.GET)
	public Object listpage(@PathVariable String sid, @ModelAttribute Role role, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		List<Menu> menus = menuService.getMenuTree(MENU_RESOURCE_FLAG);
		String roleChName = request.getParameter("roleChName");
		
		List<Role> roles = roleService.queryRoles(role);
		
		if(role.getRoleName()!=null) {
			request.setAttribute("queryLabel_en", "roleName");
			request.setAttribute("queryLabel_ch", "角色名");
			request.setAttribute("queryValue", role.getRoleName());
		} else if(role.getRoleChName()!=null) {
			request.setAttribute("queryLabel_en", "roleChName");
			request.setAttribute("queryLabel_ch", "角色中文名");
			request.setAttribute("queryValue", role.getRoleChName());
		} else if(role.getStatus()!=null) {
			request.setAttribute("queryLabel_en", "status");
			request.setAttribute("queryLabel_ch", "状态");
			request.setAttribute("queryValue", role.getStatus());
		} else {
			request.setAttribute("queryLabel_en", "roleName");
			request.setAttribute("queryLabel_ch", "角色名");
			request.setAttribute("queryValue", "");
		}
		request.setAttribute("roles", roles);
		request.setAttribute("menus", menus);
		request.setAttribute("username", userService.getUsername());
		request.setAttribute("viewPage", "workcenter/role/list.jsp");
		
		return "admin/main";
	}
	
	@RequestMapping(value="{sid}/admin/role/add", method=RequestMethod.POST)
	@ResponseBody
	public Object addRole(@ModelAttribute Role role, HttpServletRequest request, HttpServletResponse response) {
		
		WorkcenterResult result = (WorkcenterResult) roleService.addRole(role);
		
		return WorkcenterResponseBodyJson.custom().setAll(result, ROLE_ADD).build();
	}
	
	@RequestMapping(value="{sid}/admin/role/get", method=RequestMethod.GET)
	@ResponseBody
	public Object getRole(@RequestParam Long roleid, HttpServletRequest request, HttpServletResponse response) {
		
		WorkcenterResult result = (WorkcenterResult) roleService.getRoleByRoleid(roleid);
		
		return WorkcenterResponseBodyJson.custom().setAll(result, ROLE_GET).build();
	}
	
	@RequestMapping(value="{sid}/admin/role/edit", method=RequestMethod.POST)
	@ResponseBody
	public Object editRole(@ModelAttribute Role role, HttpServletRequest request, HttpServletResponse response) {
		
		WorkcenterResult result = (WorkcenterResult) roleService.editRole(role);
		
		return WorkcenterResponseBodyJson.custom().setAll(result, ROLE_EDIT).build();
	}
	
	@RequestMapping(value="{sid}/admin/role/forbidden", method=RequestMethod.POST) 
	@ResponseBody
	public Object forbiddenRole(@RequestParam String roleids, HttpServletRequest request, HttpServletResponse response) {
		
		WorkcenterResult result = (WorkcenterResult) roleService.forbiddenRoles(roleids);
		
		return WorkcenterResponseBodyJson.custom().setAll(result, ROLE_FORBIDDEN).build();
	}
	
	@RequestMapping(value="{sid}/admin/role/delete", method=RequestMethod.POST)
	@ResponseBody
	public Object deleteRole(@RequestParam String roleids, HttpServletRequest request, HttpServletResponse response) {
		
		WorkcenterResult result = (WorkcenterResult) roleService.deleteRoles(roleids);
		
		return WorkcenterResponseBodyJson.custom().setAll(result, ROLE_DELETE).build();
	}

}
