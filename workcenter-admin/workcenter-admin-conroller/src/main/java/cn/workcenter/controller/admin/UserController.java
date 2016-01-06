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
import cn.workcenter.model.User;
import cn.workcenter.service.UserService;

@Controller("adminUserController")
public class UserController implements Constant, WebConstant {
	
	@Autowired
	private MenuService menuService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="{sid}/admin/user/list", method=RequestMethod.GET)
	public Object listpage(@PathVariable String sid, @ModelAttribute User user, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		List<Menu> menus = menuService.getMenuTree(MENU_RESOURCE_FLAG);
		String realName = request.getParameter("realName");
		
		List<User> users = userService.queryUsers(user);
		
		if(user.getUserName()!=null) {
			request.setAttribute("queryLabel_en", "userName");
			request.setAttribute("queryLabel_ch", "用户名");
			request.setAttribute("queryValue", user.getUserName());
		} else if(user.getRealName()!=null) {
			request.setAttribute("queryLabel_en", "realName");
			request.setAttribute("queryLabel_ch", "真实姓名");
			request.setAttribute("queryValue", user.getRealName());
		} else if(user.getStatus()!=null) {
			request.setAttribute("queryLabel_en", "status");
			request.setAttribute("queryLabel_ch", "状态");
			request.setAttribute("queryValue", user.getStatus());
		} else {
			request.setAttribute("queryLabel_en", "userName");
			request.setAttribute("queryLabel_ch", "用户名");
			request.setAttribute("queryValue", "");
		}
		request.setAttribute("users", users);
		request.setAttribute("menus", menus);
		
		request.setAttribute("viewPage", "workcenter/user/list.jsp");
		
		return "admin/main";
	}
	
	@RequestMapping(value="{sid}/admin/user/add", method=RequestMethod.POST)
	@ResponseBody
	public Object addUser(@ModelAttribute User user, HttpServletRequest request, HttpServletResponse response) {
		
		WorkcenterResult result = (WorkcenterResult) userService.addUser(user);
		
		return WorkcenterResponseBodyJson.custom().setAll(result, USER_ADD).build();
	}
	
	@RequestMapping(value="{sid}/admin/user/get", method=RequestMethod.GET)
	@ResponseBody
	public Object getUser(@RequestParam Long userid, HttpServletRequest request, HttpServletResponse response) {
		
		WorkcenterResult result = (WorkcenterResult) userService.getUserByUserid(userid);
		
		return WorkcenterResponseBodyJson.custom().setAll(result, USER_GET).build();
	}
	
	@RequestMapping(value="{sid}/admin/user/edit", method=RequestMethod.POST)
	@ResponseBody
	public Object editUser(@ModelAttribute User user, HttpServletRequest request, HttpServletResponse response) {
		
		WorkcenterResult result = (WorkcenterResult) userService.editUser(user);
		
		return WorkcenterResponseBodyJson.custom().setAll(result, USER_EDIT).build();
	}
	
	@RequestMapping(value="{sid}/admin/user/forbidden", method=RequestMethod.POST) 
	@ResponseBody
	public Object forbiddenUser(@RequestParam String userids, HttpServletRequest request, HttpServletResponse response) {
		
		WorkcenterResult result = (WorkcenterResult) userService.forbiddenUsers(userids);
		
		return WorkcenterResponseBodyJson.custom().setAll(result, USER_FORBIDDEN).build();
	}
	
	@RequestMapping(value="{sid}/admin/user/delete", method=RequestMethod.POST)
	@ResponseBody
	public Object deleteUser(@RequestParam String userids, HttpServletRequest request, HttpServletResponse response) {
		
		WorkcenterResult result = (WorkcenterResult) userService.deleteUsers(userids);
		
		return WorkcenterResponseBodyJson.custom().setAll(result, USER_DELETE).build();
	}

}
