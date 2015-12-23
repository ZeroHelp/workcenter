package cn.workcenter.kpi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.workcenter.kpi.common.KpiApplication;

@Controller
public class IndexController extends KpiApplication {
	
	@RequestMapping(value="{sid}/kpi/home", method=RequestMethod.GET)
	public Object loginPage1(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("basePath", BASE_PATH);
		
		return "/hello";
	}
	
	
	
}
