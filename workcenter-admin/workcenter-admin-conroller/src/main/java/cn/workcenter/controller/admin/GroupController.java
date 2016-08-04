package cn.workcenter.controller.admin;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import cn.workcenter.admin.model.ZNode;
import cn.workcenter.admin.service.MenuService;
import cn.workcenter.admin.service.ZtreeService;
import cn.workcenter.common.WorkcenterResult;
import cn.workcenter.common.constant.Constant;
import cn.workcenter.common.constant.WebConstant;
import cn.workcenter.common.response.WorkcenterResponseBodyJson;
import cn.workcenter.model.Group;
import cn.workcenter.service.GroupService;
import cn.workcenter.service.UserService;

@Controller("adminGroupController")
public class GroupController implements Constant, WebConstant {
	
	@Autowired
	private MenuService menuService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private ZtreeService ztreeService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="{sid}/admin/group/ztree", method=RequestMethod.GET)
	@ResponseBody
	public Object ztree(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response) {
		
		List<ZNode> ztree = ztreeService.getGroupZtree();
		
		return ztree;
	}
	@RequestMapping(value="{sid}/admin/group/list", method=RequestMethod.GET)
	public Object listpage(@PathVariable String sid, @ModelAttribute Group group, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		List<Menu> menus = menuService.getMenuTree(MENU_RESOURCE_FLAG);
		String groupChName = request.getParameter("groupChName");
		
		List<Group> groups = groupService.queryGroups(group);
		
		if(group.getGroupName()!=null) {
			request.setAttribute("queryLabel_en", "groupName");
			request.setAttribute("queryLabel_ch", "用户组名");
			request.setAttribute("queryValue", group.getGroupName());
		} else if(group.getGroupChName()!=null) {
			request.setAttribute("queryLabel_en", "groupChName");
			request.setAttribute("queryLabel_ch", "用户组中文名");
			request.setAttribute("queryValue", group.getGroupChName());
		} else if(group.getStatus()!=null) {
			request.setAttribute("queryLabel_en", "status");
			request.setAttribute("queryLabel_ch", "状态");
			request.setAttribute("queryValue", group.getStatus());
		} else {
			request.setAttribute("queryLabel_en", "groupName");
			request.setAttribute("queryLabel_ch", "用户组名");
			request.setAttribute("queryValue", "");
		}
		
		List<Group> parentGroups = groupService.getParentGroups();
		
		request.setAttribute("groups", groups);
		
		request.setAttribute("parentGroups", parentGroups);
		request.setAttribute("menus", menus);
		request.setAttribute("username", userService.getUsername());
		request.setAttribute("viewPage", "workcenter/group/list.jsp");
		
		return "admin/main";
	}
	
	@RequestMapping(value="{sid}/admin/group/add", method=RequestMethod.POST)
	@ResponseBody
	public Object addGroup(@ModelAttribute Group group, HttpServletRequest request, HttpServletResponse response) {
		
		WorkcenterResult result = (WorkcenterResult) groupService.addGroup(group);
		
		return WorkcenterResponseBodyJson.custom().setAll(result, GROUP_ADD).build();
	}
	
	@RequestMapping(value="{sid}/admin/group/get", method=RequestMethod.GET)
	@ResponseBody
	public Object getGroup(@RequestParam Long groupid, HttpServletRequest request, HttpServletResponse response) {
		
		WorkcenterResult result = (WorkcenterResult) groupService.getGroupByGroupid(groupid);
		
		return WorkcenterResponseBodyJson.custom().setAll(result, GROUP_GET).build();
	}
	
	@RequestMapping(value="{sid}/admin/group/edit", method=RequestMethod.POST)
	@ResponseBody
	public Object editGroup(@ModelAttribute Group group, HttpServletRequest request, HttpServletResponse response) {
		
		WorkcenterResult result = (WorkcenterResult) groupService.editGroup(group);
		
		return WorkcenterResponseBodyJson.custom().setAll(result, GROUP_EDIT).build();
	}
	
	@RequestMapping(value="{sid}/admin/group/forbidden", method=RequestMethod.POST) 
	@ResponseBody
	public Object forbiddenGroup(@RequestParam String groupids, HttpServletRequest request, HttpServletResponse response) {
		
		WorkcenterResult result = (WorkcenterResult) groupService.forbiddenGroups(groupids);
		
		return WorkcenterResponseBodyJson.custom().setAll(result, GROUP_FORBIDDEN).build();
	}
	
	@RequestMapping(value="{sid}/admin/group/delete", method=RequestMethod.POST)
	@ResponseBody
	public Object deleteGroup(@RequestParam String groupids, HttpServletRequest request, HttpServletResponse response) {
		
		WorkcenterResult result = (WorkcenterResult) groupService.deleteGroups(groupids);
		
		return WorkcenterResponseBodyJson.custom().setAll(result, GROUP_DELETE).build();
	}

}
