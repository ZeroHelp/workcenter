package cn.workcenter.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.workcenter.common.WorkcenterApplication;
import cn.workcenter.common.WorkcenterCodeEnum;
import cn.workcenter.common.WorkcenterResult;
import cn.workcenter.common.constant.WebConstant;
import cn.workcenter.dao.GroupMapper;
import cn.workcenter.model.Group;
import cn.workcenter.service.GroupService;
import cn.workcenter.service.UserService;

@Service("groupService")
public class GroupServiceImpl extends WorkcenterApplication implements GroupService , WebConstant{

	@Autowired
	private UserService userService;
	@Autowired
	private GroupMapper groupMapper;
	@Override
	public List<Group> queryGroups(Group group) {
		List<Group> groups = groupMapper.queryGroupsBySelective(group);
		return groups;
	}
	@Override
	public Object addGroup(Group group) {
		group.setCreateTime(new Date());
		group.setCreateUserid(userService.getUserId());
		int re = groupMapper.insert(group);
		if(re>0){
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_GROUP_ADD)).build();
		} else {
			return WorkcenterResult.custom().setNO(WorkcenterCodeEnum.valueOf(NO_GROUP_ADD)).build();
		}
	}
	@Override
	public Object getGroupByGroupid(Long groupid) {
		Group group = groupMapper.selectByPrimaryKey(groupid);
		if(group!=null && group.getId() >0) {
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_GROUP_GET), group).build();
		} else {
			return WorkcenterResult.custom().setNO(WorkcenterCodeEnum.valueOf(NO_GROUP_GET)).build();
		}
	}
	@Override
	public Object editGroup(Group group) {
		int re = groupMapper.updateByPrimaryKeySelective(group);
		if(re>0){
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_GROUP_UPDATE)).build();
		} else {
			return WorkcenterResult.custom().setNO(WorkcenterCodeEnum.valueOf(NO_GROUP_UPDATE)).build();
		}
	}
	@Override
	public Object forbiddenGroups(String groupids) {
		String[] arrayId = groupids.split(",");
		for(int i=0;i<arrayId.length;i++) {
			forbiddenGroup(Long.parseLong(arrayId[i]));
		}
		return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_GROUP_FORBIDDEN)).build();
	}
	
	private WorkcenterResult forbiddenGroup(Long id) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("id", id);
		parameterMap.put("status", 0);
		int re = groupMapper.updateGroupStatusById(parameterMap);
		if(re>0){
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_GROUP_FORBIDDEN)).build();
		} else {
			return WorkcenterResult.custom().setNO(WorkcenterCodeEnum.valueOf(NO_GROUP_FORBIDDEN)).build();
		}
	}
	
	@Override
	public Object deleteGroups(String groupids) {
		String[] arrayId = groupids.split(",");
		for(int i=0;i<arrayId.length;i++) {
			deleteGroup(Long.parseLong(arrayId[i]));
		}
		return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_GROUP_DELETE)).build();
	}

	private WorkcenterResult deleteGroup(Long id) {
		int re = groupMapper.deleteByPrimaryKey(id);
		if(re>0){
			return WorkcenterResult.custom().setOK(WorkcenterCodeEnum.valueOf(OK_GROUP_DELETE)).build();
		} else {
			return WorkcenterResult.custom().setNO(WorkcenterCodeEnum.valueOf(NO_GROUP_DELETE)).build();
		}
	}
	@Override
	public List<Group> getAllGroups() {
		List<Group> groups = groupMapper.queryAllGroup();
		return groups;
	}
}
