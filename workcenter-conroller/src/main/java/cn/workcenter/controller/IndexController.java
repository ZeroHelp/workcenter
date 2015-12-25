package cn.workcenter.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.workcenter.common.WorkcenterApplication;
import cn.workcenter.common.WorkcenterResult;
import cn.workcenter.common.response.WorkcenterResponseBodyJson;
import cn.workcenter.common.util.StringUtil;
import cn.workcenter.model.Resource;
import cn.workcenter.service.ResourceService;
import cn.workcenter.service.UserService;

@Controller("workcenterIndexController")
public class IndexController extends WorkcenterApplication {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping(value="workcenter/index", method=RequestMethod.GET)
	public Object loginPage(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("basePath", BASE_PATH);
		return "/workcenter/login";
	}
	
	@RequestMapping(value="workcenter/sid", method=RequestMethod.POST)
	@ResponseBody
	public Object dologin(HttpServletRequest request, HttpServletResponse response) {
		
		String username = StringUtil.getParameterExceptionEmpty(request, "username");
		String password = StringUtil.getParameterExceptionEmpty(request, "password");
		
		WorkcenterResult loginresult = (WorkcenterResult)userService.doLogin(username, password);
		
		return WorkcenterResponseBodyJson.custom().setAll(loginresult, LOGIN).build();
	}
	
	@RequestMapping(value="{sid}/workcenter/password", method=RequestMethod.GET)
	@ResponseBody
	public Object getPassword(HttpServletRequest request, HttpServletResponse response) {
		
		String username = StringUtil.getParameterExceptionEmpty(request, "username");
		String password = StringUtil.getParameterExceptionEmpty(request, "password");
		
		WorkcenterResult loginresult = (WorkcenterResult)userService.doLogin(username, password);
		
		return WorkcenterResponseBodyJson.custom().setAll(loginresult, LOGIN).build();
	}
	
	@RequestMapping(value="{sid}/workcenter/password", method=RequestMethod.POST)
	@ResponseBody
	public Object postPassword(HttpServletRequest request, HttpServletResponse response) {
		
		String username = StringUtil.getParameterExceptionEmpty(request, "username");
		String password = StringUtil.getParameterExceptionEmpty(request, "password");
		
		WorkcenterResult loginresult = (WorkcenterResult)userService.doLogin(username, password);
		
		return WorkcenterResponseBodyJson.custom().setAll(loginresult, LOGIN).build();
	}
	
	@RequestMapping(value="workcenter/logout", method=RequestMethod.POST)
	@ResponseBody
	public Object doLogout(HttpServletRequest request, HttpServletResponse response) {
		
		String sid = StringUtil.getParameterExceptionEmpty(request, "sid");
		
		WorkcenterResult loginresult = (WorkcenterResult)userService.doLogout(sid);
		
		return WorkcenterResponseBodyJson.custom().setAll(loginresult, LOGOUT).build();
	}
	
	@RequestMapping(value="{sid}/workcenter/home", method=RequestMethod.GET)
	public Object home(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response) {
		
		List<Resource> modules = resourceService.getUserModule();
		
		request.setAttribute("sid", sid);
		request.setAttribute("modules", modules);
		return "/workcenter/home";
	}
}
