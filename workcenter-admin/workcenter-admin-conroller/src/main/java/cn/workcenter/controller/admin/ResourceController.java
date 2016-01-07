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
import cn.workcenter.model.Resource;
import cn.workcenter.service.ResourceService;
import cn.workcenter.service.UserService;

@Controller("adminResourceController")
public class ResourceController implements Constant, WebConstant {
	
	@Autowired
	private MenuService menuService;
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="{sid}/admin/resource/list", method=RequestMethod.GET)
	public Object listpage(@PathVariable String sid, @ModelAttribute Resource resource, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		List<Menu> menus = menuService.getMenuTree(MENU_RESOURCE_FLAG);
		String resourceName = request.getParameter("resourceName");
		
		List<Resource> resources = resourceService.queryResources(resource);
		
		if(resource.getResourceName()!=null) {
			request.setAttribute("queryLabel_en", "resourceName");
			request.setAttribute("queryLabel_ch", "资源名");
			request.setAttribute("queryValue", resource.getResourceName());
		} else if(resource.getResourceUrl()!=null) {
			request.setAttribute("queryLabel_en", "resourceUrl");
			request.setAttribute("queryLabel_ch", "资源链接地址");
			request.setAttribute("queryValue", resource.getResourceUrl());
		} else if(resource.getStatus()!=null) {
			request.setAttribute("queryLabel_en", "status");
			request.setAttribute("queryLabel_ch", "状态");
			request.setAttribute("queryValue", resource.getStatus());
		} else {
			request.setAttribute("queryLabel_en", "resourceName");
			request.setAttribute("queryLabel_ch", "资源名");
			request.setAttribute("queryValue", "");
		}
		request.setAttribute("resources", resources);
		request.setAttribute("menus", menus);
		request.setAttribute("username", userService.getUsername());
		request.setAttribute("viewPage", "workcenter/resource/list.jsp");
		
		return "admin/main";
	}
	
	@RequestMapping(value="{sid}/admin/resource/add", method=RequestMethod.POST)
	@ResponseBody
	public Object addResource(@ModelAttribute Resource resource, HttpServletRequest request, HttpServletResponse response) {
		
		WorkcenterResult result = (WorkcenterResult) resourceService.addResource(resource);
		
		return WorkcenterResponseBodyJson.custom().setAll(result, ROLE_ADD).build();
	}
	
	@RequestMapping(value="{sid}/admin/resource/get", method=RequestMethod.GET)
	@ResponseBody
	public Object getResource(@RequestParam Long resourceid, HttpServletRequest request, HttpServletResponse response) {
		
		WorkcenterResult result = (WorkcenterResult) resourceService.getResourceByResourceid(resourceid);
		
		return WorkcenterResponseBodyJson.custom().setAll(result, ROLE_GET).build();
	}
	
	@RequestMapping(value="{sid}/admin/resource/edit", method=RequestMethod.POST)
	@ResponseBody
	public Object editResource(@ModelAttribute Resource resource, HttpServletRequest request, HttpServletResponse response) {
		
		WorkcenterResult result = (WorkcenterResult) resourceService.editResource(resource);
		
		return WorkcenterResponseBodyJson.custom().setAll(result, ROLE_EDIT).build();
	}
	
	@RequestMapping(value="{sid}/admin/resource/forbidden", method=RequestMethod.POST) 
	@ResponseBody
	public Object forbiddenResource(@RequestParam String resourceids, HttpServletRequest request, HttpServletResponse response) {
		
		WorkcenterResult result = (WorkcenterResult) resourceService.forbiddenResources(resourceids);
		
		return WorkcenterResponseBodyJson.custom().setAll(result, ROLE_FORBIDDEN).build();
	}
	
	@RequestMapping(value="{sid}/admin/resource/delete", method=RequestMethod.POST)
	@ResponseBody
	public Object deleteResource(@RequestParam String resourceids, HttpServletRequest request, HttpServletResponse response) {
		
		WorkcenterResult result = (WorkcenterResult) resourceService.deleteResources(resourceids);
		
		return WorkcenterResponseBodyJson.custom().setAll(result, ROLE_DELETE).build();
	}

}
