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
import cn.workcenter.admin.service.FlowVariableaccessService;
import cn.workcenter.admin.service.MenuService;
import cn.workcenter.common.WorkcenterResult;
import cn.workcenter.common.constant.Constant;
import cn.workcenter.common.constant.WebConstant;
import cn.workcenter.common.response.WorkcenterResponseBodyJson;
import cn.workcenter.model.FlowVariableaccess;

@Controller("flowVariableaccessController")
public class FlowVariableaccessController implements Constant, WebConstant {

	@Autowired
	private MenuService menuService;

	@Autowired
	private FlowVariableaccessService flowVariableaccessService;

	@RequestMapping(value = "{sid}/admin/flow/Variableaccess/list", method = RequestMethod.GET)
	public Object listpage(@PathVariable String sid, @ModelAttribute FlowVariableaccess flowVariableaccess,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

		List<Menu> menus = menuService.getMenuTree(MENU_RESOURCE_FLAG);

		List<FlowVariableaccess> flowVariableaccesss = flowVariableaccessService
				.queryFlowVariableaccess(flowVariableaccess);

		if (flowVariableaccess.getVariablename() != null) {
			request.setAttribute("queryLabel_en", "variablename");
			request.setAttribute("queryLabel_ch", "变量名称");
			request.setAttribute("queryValue", flowVariableaccess.getVariablename());
		} else if (flowVariableaccess.getNodeId() != null) {
			request.setAttribute("queryLabel_en", "nodeId");
			request.setAttribute("queryLabel_ch", "节点Id");
			request.setAttribute("queryValue", flowVariableaccess.getNodeId());
		} else if (flowVariableaccess.getVariableinstanceId() != null) {
			request.setAttribute("queryLabel_en", "variableinstanceId");
			request.setAttribute("queryLabel_ch", "流程Id");
			request.setAttribute("queryValue", flowVariableaccess.getVariableinstanceId());
		} else if (flowVariableaccess.getAccess() != null) {
			request.setAttribute("queryLabel_en", "access");
			request.setAttribute("queryLabel_ch", "权限");
			request.setAttribute("queryValue", flowVariableaccess.getAccess());
		} else {
			request.setAttribute("queryLabel_en", "variablename");
			request.setAttribute("queryLabel_ch", "变量名称");
			request.setAttribute("queryValue", "");
		}
		request.setAttribute("flowVariableaccesss", flowVariableaccesss);
		request.setAttribute("menus", menus);
		request.setAttribute("viewPage", "workcenter/flowVariable/accesslist.jsp");
		return "admin/main";
	}

	@RequestMapping(value = "{sid}/admin/flow/Variableaccess/add", method = RequestMethod.POST)
	@ResponseBody
	public Object addFlowVariableinstance(@ModelAttribute FlowVariableaccess flowVariableaccess,
			HttpServletRequest request, HttpServletResponse response) {

		WorkcenterResult result = (WorkcenterResult) flowVariableaccessService
				.addFlowVariableaccess(flowVariableaccess);
		return WorkcenterResponseBodyJson.custom().setAll(result, FVA_ADD).build();
	}

	@RequestMapping(value = "{sid}/admin/flow/Variableaccess/get", method = RequestMethod.GET)
	@ResponseBody
	public Object getFlowVariableinstance(@RequestParam Long flowVariableaccessid, HttpServletRequest request,
			HttpServletResponse response) {

		WorkcenterResult result = (WorkcenterResult) flowVariableaccessService
				.getFlowVariableaccessByFlowVariableaccessid(flowVariableaccessid);
		return WorkcenterResponseBodyJson.custom().setAll(result, FVA_GET).build();
	}

	@RequestMapping(value = "{sid}/admin/flow/Variableaccess/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object editFlowVariableinstance(@ModelAttribute FlowVariableaccess flowVariableaccess,
			HttpServletRequest request, HttpServletResponse response) {

		WorkcenterResult result = (WorkcenterResult) flowVariableaccessService
				.editFlowVariableaccess(flowVariableaccess);
		return WorkcenterResponseBodyJson.custom().setAll(result, FVA_EDIT).build();
	}

	@RequestMapping(value = "{sid}/admin/flow/Variableaccess/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteFlowVariableinstance(@RequestParam String flowVariableaccessServiceids,
			HttpServletRequest request, HttpServletResponse response) {
		WorkcenterResult result = (WorkcenterResult) flowVariableaccessService
				.deleteFlowVariableaccesss(flowVariableaccessServiceids);
		return WorkcenterResponseBodyJson.custom().setAll(result, FVA_DELETE).build();
	}

}
