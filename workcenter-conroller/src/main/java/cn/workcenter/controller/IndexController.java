package cn.workcenter.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.workcenter.common.WorkcenterApplication;
import cn.workcenter.common.WorkcenterOperationEnum;
import cn.workcenter.common.WorkcenterResult;
import cn.workcenter.common.response.WorkcenterResponseBodyJson;
import cn.workcenter.common.util.StringUtil;
import cn.workcenter.service.UserService;

@Controller
@RequestMapping(value="workcenter")
public class IndexController extends WorkcenterApplication {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 跳到登录页
	 * 
	 * @param request
	 * @param response
	 * @return	
	 */
	@RequestMapping(value="index", method=RequestMethod.GET)
	public Object loginPage(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("basePath", BASE_PATH);
		return "/workcenter/login.jsp";
	}
	
	@RequestMapping(value="sid", method=RequestMethod.POST)
	@ResponseBody
	public Object dologin(HttpServletRequest request, HttpServletResponse response) {
		
		String username = StringUtil.getParameterExceptionEmpty(request, "username");
		String password = StringUtil.getParameterExceptionEmpty(request, "password");
		
		WorkcenterResult loginresult = userService.doLogin(username, password);
		
		return WorkcenterResponseBodyJson.custom().setAll(loginresult, LOGIN).build();
	}
	
	
	@RequestMapping(value="logout", method=RequestMethod.POST)
	@ResponseBody
	public Object doLogout(HttpServletRequest request, HttpServletResponse response) {
		
		String sid = StringUtil.getParameterExceptionEmpty(request, "sid");
		
		WorkcenterResult loginresult = userService.doLogout(sid);
		
		return WorkcenterResponseBodyJson.custom().setAll(loginresult, LOGOUT).build();
	}
}
