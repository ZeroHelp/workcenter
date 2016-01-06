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
import cn.workcenter.admin.service.FlowVariableinstanceService;
import cn.workcenter.admin.service.MenuService;
import cn.workcenter.common.WorkcenterResult;
import cn.workcenter.common.constant.Constant;
import cn.workcenter.common.constant.WebConstant;
import cn.workcenter.common.response.WorkcenterResponseBodyJson;
import cn.workcenter.model.FlowVariableinstance;

@Controller("flowInstanceController")
public class FlowInstanceController implements Constant, WebConstant {

	@Autowired
	private MenuService menuService;

	@Autowired
	private FlowVariableinstanceService flowVariableinstanceService;

	@RequestMapping(value = "{sid}/admin/flow/Variable/instance/list", method = RequestMethod.GET)
	public Object listpage(@PathVariable String sid, @ModelAttribute FlowVariableinstance flowVariableinstance,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

		List<Menu> menus = menuService.getMenuTree(MENU_RESOURCE_FLAG);
		String varName = request.getParameter("varName");

		List<FlowVariableinstance> flowVariableinstances = flowVariableinstanceService
				.queryFlowVariableinstance(flowVariableinstance);

		if (flowVariableinstance.getVarName() != null) {
			request.setAttribute("queryLabel_en", "varName");
			request.setAttribute("queryLabel_ch", "变量名称");
			request.setAttribute("queryValue", flowVariableinstance.getVarName());
		} else if (flowVariableinstance.getVarValue() != null) {
			request.setAttribute("queryLabel_en", "varValue");
			request.setAttribute("queryLabel_ch", "变量值");
			request.setAttribute("queryValue", flowVariableinstance.getVarValue());
		} else if (flowVariableinstance.getStatus() != null) {
			request.setAttribute("queryLabel_en", "status");
			request.setAttribute("queryLabel_ch", "状态");
			request.setAttribute("queryValue", flowVariableinstance.getStatus());
		} else {
			request.setAttribute("queryLabel_en", "varName");
			request.setAttribute("queryLabel_ch", "变量名称");
			request.setAttribute("queryValue", "");
		}
		request.setAttribute("flowVariableinstances", flowVariableinstances);
		request.setAttribute("menus", menus);

		request.setAttribute("viewPage", "workcenter/flowVariable/instancelist.jsp");

		return "admin/main";
	}

	@RequestMapping(value = "{sid}/admin/flow/Variable/instance/add", method = RequestMethod.POST)
	@ResponseBody
	public Object addFlowVariableinstance(@ModelAttribute FlowVariableinstance flowVariableinstance,
			HttpServletRequest request, HttpServletResponse response) {

		WorkcenterResult result = (WorkcenterResult) flowVariableinstanceService
				.addFlowVariableinstance(flowVariableinstance);
		return WorkcenterResponseBodyJson.custom().setAll(result, FVI_ADD).build();
	}

	@RequestMapping(value = "{sid}/admin/flow/Variable/instance/get", method = RequestMethod.GET)
	@ResponseBody
	public Object getFlowVariableinstance(@RequestParam Long flowVariableinstanceid, HttpServletRequest request,
			HttpServletResponse response) {

		WorkcenterResult result = (WorkcenterResult) flowVariableinstanceService
				.getFlowVariableinstanceByFlowVariableinstanceid(flowVariableinstanceid);
		return WorkcenterResponseBodyJson.custom().setAll(result, FVI_GET).build();
	}

	@RequestMapping(value = "{sid}/admin/flow/Variable/instance/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object editFlowVariableinstance(@ModelAttribute FlowVariableinstance flowVariableinstance,
			HttpServletRequest request, HttpServletResponse response) {

		WorkcenterResult result = (WorkcenterResult) flowVariableinstanceService
				.editFlowVariableinstance(flowVariableinstance);

		return WorkcenterResponseBodyJson.custom().setAll(result, FVI_EDIT).build();
	}

	@RequestMapping(value = "{sid}/admin/flow/Variable/instance/forbidden", method = RequestMethod.POST)
	@ResponseBody
	public Object forbiddenFlowVariableinstance(@RequestParam String flowVariableinstanceids,
			HttpServletRequest request, HttpServletResponse response) {

		WorkcenterResult result = (WorkcenterResult) flowVariableinstanceService
				.forbiddenFlowVariableinstances(flowVariableinstanceids);

		return WorkcenterResponseBodyJson.custom().setAll(result, FVI_FORBIDDEN).build();
	}

	@RequestMapping(value = "{sid}/admin/flow/Variable/instance/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteFlowVariableinstance(@RequestParam String flowVariableinstanceids, HttpServletRequest request,
			HttpServletResponse response) {
		WorkcenterResult result = (WorkcenterResult) flowVariableinstanceService
				.deleteFlowVariableinstances(flowVariableinstanceids);

		return WorkcenterResponseBodyJson.custom().setAll(result, FVI_DELETE).build();
	}

}
