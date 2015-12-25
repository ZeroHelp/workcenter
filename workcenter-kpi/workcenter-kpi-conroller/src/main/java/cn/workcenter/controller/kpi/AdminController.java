package cn.workcenter.controller.kpi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("adminController")
public class AdminController {
	
	//@RequestMapping(value="{sid}/admin/kpi/home", method=RequestMethod.GET)
	@RequestMapping(value="{sid}/admin/kpi/home", method=RequestMethod.GET)
	public Object home(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("viewPage", "initKpi.jsp");
		return "admin/home";
	}
	
	
}
