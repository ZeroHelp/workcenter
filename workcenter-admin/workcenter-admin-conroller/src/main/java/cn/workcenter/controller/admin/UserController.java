package cn.workcenter.controller.admin;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.workcenter.admin.model.Menu;
import cn.workcenter.admin.service.MenuService;
import cn.workcenter.common.constant.Constant;
import cn.workcenter.dao.UserMapper;
import cn.workcenter.model.User;

@Controller("userController")
public class UserController implements Constant {
	@Autowired
	private MenuService menuService;
	@Autowired
	private UserMapper userMapper;

	@RequestMapping(value = "{sid}/admin/user/list", method = RequestMethod.GET)
	public Object userlist(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response) {
		List<Menu> menus = menuService.getMenuTree(MENU_RESOURCE_FLAG);
		request.setAttribute("menus", menus);
		request.setAttribute("viewPage", "userlist.jsp");
		return "admin/main";
	}

	@ResponseBody
	@RequestMapping(value = "{sid}/admin/user/loadallList", method = RequestMethod.GET)
	public Object userlists(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response) {

		System.out.println("返回json数据" + JSONObject.toJSON(userMapper.getUsers()));
		return JSONObject.toJSON(userMapper.getUsers());
	}

	@ResponseBody
	@RequestMapping(value = "{sid}/admin/user/insertUser", method = RequestMethod.POST)
	public Object insertUser(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response) {
		User user = new User();
		String id = request.getParameter("id");
		String userName = request.getParameter("userName");
		String realName = request.getParameter("realName");
		String password = request.getParameter("password");
		String lastLogin = request.getParameter("lastLogin");
		String status = request.getParameter("status");
		if (id != null && !"".equals(id)) {
			user.setId(Long.valueOf(id));
		}

		user.setUserName(userName);
		user.setRealName(realName);
		user.setPassword(password);
		if (lastLogin != null && !"".equals(lastLogin)) {
			user.setLastLogin(Date.valueOf(lastLogin));
		}
		if (status != null && !"".equals(status)) {
			user.setStatus(Integer.valueOf(status));
		}

		userMapper.insert(user);
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "{sid}/admin/user/updateUser", method = RequestMethod.POST)
	public Object updateUser(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response) {
		User user = new User();
		String id = request.getParameter("id");
		if (id != null && !"".equals(id)) {
			user.setId(Long.valueOf(id));
		}
		String userName = request.getParameter("userName");
		String realName = request.getParameter("realName");
		String password = request.getParameter("password");
		String lastLogin = request.getParameter("lastLogin");
		String status = request.getParameter("status");
		user.setId(Long.valueOf(id));
		user.setUserName(userName);
		user.setRealName(realName);
		user.setPassword(password);
/*		if (lastLogin != null && !"".equals(lastLogin)) {
			user.setLastLogin(Date.valueOf(lastLogin));
		}*/
		if (status != null && !"".equals(status)) {
			user.setStatus(Integer.valueOf(status));
		}
		userMapper.updateByPrimaryKey(user);
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "{sid}/admin/user/deleteUser", method = RequestMethod.POST)
	public Object deleteUser(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		userMapper.deleteByPrimaryKey(Long.valueOf(id));
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "{sid}/admin/user/loadone", method = RequestMethod.POST)
	public Object loadoneUser(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		User userOne = userMapper.selectByPrimaryKey(Long.valueOf(id));
		request.setAttribute("userOne", userOne);
		return userOne;
	}
}
