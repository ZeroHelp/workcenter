package cn.workcenter.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.workcenter.admin.service.FlowVariableaccessService;
import cn.workcenter.common.WorkcenterCodeEnum;
import cn.workcenter.common.WorkcenterResult;
import cn.workcenter.common.constant.WebConstant;
import cn.workcenter.dao.FlowVariableaccessMapper;
import cn.workcenter.model.FlowVariableaccess;
@Service("flowVariableaccessService")
public class FlowVariableaccessServiceImpl implements FlowVariableaccessService, WebConstant {

	@Autowired
	FlowVariableaccessMapper flowVariableaccessMapper;

	@Override
	public Object addFlowVariableaccess(FlowVariableaccess flowVariableaccess) {
		// TODO Auto-generated method stub
		int num = flowVariableaccessMapper.insert(flowVariableaccess);
		if (num > 0) {
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_FVA_ADD)).build();
		} else {
			return WorkcenterResult.custom().setNO(WorkcenterCodeEnum.valueOf(NO_FVA_ADD)).build();
		}
	}

	@Override
	public Object editFlowVariableaccess(FlowVariableaccess flowVariableaccess) {
		// TODO Auto-generated method stub
		int num = flowVariableaccessMapper.updateByPrimaryKeySelective(flowVariableaccess);
		if (num > 0) {
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_FVA_UPDATE)).build();
		} else {
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(NO_FVA_UPDATE)).build();
		}
	}

	@Override
	public Object getFlowVariableaccessByFlowVariableaccessid(Long flowVariableaccessid) {
		// TODO Auto-generated method stub
		FlowVariableaccess flowVariableaccess = flowVariableaccessMapper.selectByPrimaryKey(flowVariableaccessid);
		if (flowVariableaccess != null && flowVariableaccess.getId() > 0) {
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_FVA_GET), flowVariableaccess).build();
		} else {
			return WorkcenterResult.custom().setNO(WorkcenterCodeEnum.valueOf(NO_FVA_GET)).build();

		}
	}

	@Override
	public Object deleteFlowVariableaccesss(String flowVariableaccessids) {
		// TODO Auto-generated method stub
		String str[] = flowVariableaccessids.split(",");
		for (int i = 0; i < str.length; i++) {
			deleteFlowVariableaccess(Long.parseLong(str[i]));
		}
		return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_FVA_DELETE)).build();
	}

	public Object deleteFlowVariableaccess(long id) {
		int num = flowVariableaccessMapper.deleteByPrimaryKey(id);
		if (num > 0) {
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_FVA_DELETE)).build();
		} else {
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_FVA_DELETE)).build();
		}
	}

	@Override
	public List<FlowVariableaccess> queryFlowVariableaccess(FlowVariableaccess flowVariableaccess) {
		// TODO Auto-generated method stub
		List<FlowVariableaccess> flowVariableaccesss = flowVariableaccessMapper
				.queryFlowVariableaccesss(flowVariableaccess);
		return flowVariableaccesss;
	}

}
