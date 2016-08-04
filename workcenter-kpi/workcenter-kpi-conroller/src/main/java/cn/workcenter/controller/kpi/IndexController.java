package cn.workcenter.controller.kpi;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.workcenter.common.response.WorkcenterResponseBodyJson;
import cn.workcenter.kpi.common.KpiApplication;
import cn.workcenter.kpi.service.KpiService;
import cn.workcenter.service.UserService;

@Controller("kpiIndexController")
public class IndexController extends KpiApplication {
	
	@Autowired
	private KpiService kpiService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="{sid}/kpi/superhome", method=RequestMethod.GET)
	public Object superKpiListPage(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> groupKpis = kpiService.getAllKpis();
		request.setAttribute("groupKpis", groupKpis);
		request.setAttribute("username", userService.getUsername());
		return "/kpi/superlist";
	}
	
	@RequestMapping(value="{sid}/kpi/superview/{main_id}", method=RequestMethod.GET)
	public Object superviewPage(@PathVariable String sid, @PathVariable Long main_id, 
			@RequestParam String method, HttpServletRequest request, HttpServletResponse response) {
		
		KpiApplication.requestThreadLocal.set(request);
		
		Object obj = kpiService.doFlowSuperView(method, main_id); 
		
		String waitAssessmentPerson = kpiService.getWaitAssessmentPerson(main_id);
		request.setAttribute("waitAssessmentPerson", waitAssessmentPerson);
		request.setAttribute("method", method);
		request.setAttribute("username", userService.getUsername());
		return "kpi/superdetail";
	}
	
	@RequestMapping(value="{sid}/kpi/home", method=RequestMethod.GET)
	public Object kpiListPage1(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> kpiList = kpiService.getAssosiateKpis();
		request.setAttribute("kpiList", kpiList);
		request.setAttribute("username", userService.getUsername());
		return "/kpi/list";
	}
	
	@RequestMapping(value="{sid}/kpi/assessment/{main_id}", method=RequestMethod.GET)
	public Object kpiPage(@PathVariable String sid, @PathVariable Long main_id, 
			@RequestParam String method, HttpServletRequest request, HttpServletResponse response) {
		
		KpiApplication.requestThreadLocal.set(request);
		
		Object obj = kpiService.doFlowGet(method, main_id); 
		
		String waitAssessmentPerson = kpiService.getWaitAssessmentPerson(main_id);
		request.setAttribute("waitAssessmentPerson", waitAssessmentPerson);
		request.setAttribute("method", method);
		request.setAttribute("username", userService.getUsername());
		return "kpi/detail";
	}
	
	@RequestMapping(value="{sid}/kpi/assessment/{main_id}", method=RequestMethod.POST)
	@ResponseBody
	public Object kpiPost(@PathVariable String sid, @PathVariable Long main_id,
			@RequestParam String method, HttpServletRequest request, HttpServletResponse response) {
		
		KpiApplication.requestThreadLocal.set(request);
		
		Object obj = kpiService.doFlowPost(method, main_id); 
		
		return WorkcenterResponseBodyJson.custom().build();
	}
	
}
