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

import cn.workcenter.kpi.common.KpiApplication;
import cn.workcenter.kpi.service.KpiService;

@Controller("kpiIndexController")
public class IndexController extends KpiApplication {
	
	@Autowired
	private KpiService kpiService;
	
	@RequestMapping(value="{sid}/kpi/home", method=RequestMethod.GET)
	public Object loginPage1(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> kpiList = kpiService.getAssosiateKpis();
		request.setAttribute("kpiList", kpiList);
		return "/kpi/list";
	}
	
	@RequestMapping(value="{sid}/kpi/assessment/{main_id}", method=RequestMethod.GET)
	public Object kpiPage(@PathVariable String sid, @PathVariable Long main_id, 
			@RequestParam String method,
			@RequestParam Long taskinstanceId, HttpServletRequest request, HttpServletResponse response) {
		
		KpiApplication.requestThreadLocal.set(request);
		
		Object obj = kpiService.doFlowGet(method, main_id, taskinstanceId); 
		
		request.setAttribute("taskinstanceId", taskinstanceId);
		return "kpi/detail";
	}
	
	@RequestMapping(value="{sid}/kpi/assessment/{main_id}", method=RequestMethod.POST)
	@ResponseBody
	public Object kpiPost(@PathVariable String sid, @PathVariable Long main_id,
			@RequestParam String method,
			@RequestParam Long taskinstanceId, HttpServletRequest request, HttpServletResponse response) {
		
		KpiApplication.requestThreadLocal.set(request);
		
		Object obj = kpiService.doFlowPost(method, main_id, taskinstanceId); 
		
		request.setAttribute("taskinstanceId", taskinstanceId);
		return obj;
	}
	
}
