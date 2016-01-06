package cn.workcenter.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.workcenter.admin.service.FlowVariableinstanceService;
import cn.workcenter.common.WorkcenterApplication;
import cn.workcenter.common.WorkcenterCodeEnum;
import cn.workcenter.common.WorkcenterResult;
import cn.workcenter.common.constant.CacheConstant;
import cn.workcenter.common.constant.SecurityConstant;
import cn.workcenter.common.constant.WebConstant;
import cn.workcenter.dao.FlowVariableinstanceMapper;
import cn.workcenter.model.FlowVariableinstance;

@Service("flowVariableinstanceServiceImpl")
public class FlowVariableinstanceServiceImpl extends WorkcenterApplication
		implements FlowVariableinstanceService, SecurityConstant, CacheConstant, WebConstant {
	@Autowired
	private FlowVariableinstanceMapper flowVariableinstanceMapper;

	@Override
	public List<FlowVariableinstance> queryFlowVariableinstance(FlowVariableinstance flowVariableinstance) {
		// TODO Auto-generated method stub
		List<FlowVariableinstance> flowVariableinstances = flowVariableinstanceMapper
				.getFlowVariableinstance(flowVariableinstance);
		return flowVariableinstances;
	}

	@Override
	public WorkcenterResult addFlowVariableinstance(FlowVariableinstance flowVariableinstance) {
		// TODO Auto-generated method stub

		int re = flowVariableinstanceMapper.insert(flowVariableinstance);
		if (re > 0) {
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_FVI_ADD)).build();
		} else {
			return WorkcenterResult.custom().setNO(WorkcenterCodeEnum.valueOf(NO_FVI_ADD)).build();
		}
	}

	@Override
	public Object editFlowVariableinstance(FlowVariableinstance flowVariableinstance) {
		// TODO Auto-generated method stub
		int re = flowVariableinstanceMapper.updateByPrimaryKeySelective(flowVariableinstance);
		if (re > 0) {
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_FVI_UPDATE)).build();
		} else {
			return WorkcenterResult.custom().setNO(WorkcenterCodeEnum.valueOf(NO_FVI_UPDATE)).build();
		}

	}

	@Override
	public Object getFlowVariableinstanceByFlowVariableinstanceid(Long flowVariableinstanceid) {
		// TODO Auto-generated method stub
		FlowVariableinstance flowVariableinstance = flowVariableinstanceMapper
				.selectByPrimaryKey(flowVariableinstanceid);
		if (flowVariableinstance != null && flowVariableinstance.getId() > 0) {
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_FVI_GET), flowVariableinstance)
					.build();
		} else {
			return WorkcenterResult.custom().setNO(WorkcenterCodeEnum.valueOf(NO_FVI_GET)).build();
		}
	}

	@Override
	public Object deleteFlowVariableinstances(String flowVariableinstanceids) {
		// TODO Auto-generated method stub
		String[] arrayId = flowVariableinstanceids.split(",");
		for (int i = 0; i < arrayId.length; i++) {
			deleteFlowVariableinstance(Long.parseLong(arrayId[i]));
		}
		return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_FVI_DELETE)).build();

	}

	public Object deleteFlowVariableinstance(Long id) {
		int re = flowVariableinstanceMapper.deleteByPrimaryKey(id);
		if (re > 0) {
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_FVI_DELETE)).build();
		} else {
			return WorkcenterResult.custom().setNO(WorkcenterCodeEnum.valueOf(NO_FVI_DELETE)).build();
		}
	}

	@Override
	public Object forbiddenFlowVariableinstances(String flowVariableinstanceids) {
		// TODO Auto-generated method stub
		String[] arrayId = flowVariableinstanceids.split(",");
		for (int i = 0; i < arrayId.length; i++) {
			forbiddenFlowVariableinstance(Long.parseLong(arrayId[i]));
		}
		return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_FVI_FORBIDDEN)).build();
	}

	private WorkcenterResult forbiddenFlowVariableinstance(Long id) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("id", id);
		parameterMap.put("status", 0);
		int re = flowVariableinstanceMapper.updateFlowVariableinstanceStatusById(parameterMap);
		if (re > 0) {
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_FVI_FORBIDDEN)).build();
		} else {
			return WorkcenterResult.custom().setNO(WorkcenterCodeEnum.valueOf(NO_FVI_FORBIDDEN)).build();
		}
	}
}
