package cn.workcenter.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.workcenter.admin.service.AdminUserService;
import cn.workcenter.dao.UserMapper;
import cn.workcenter.model.User;

@Service("adminUserService")
public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	private UserMapper userMapper;
	@Override
	public List<User> getWaitAssessments(Long groupId) {
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("group_id", groupId);
		List<User> users = userMapper.getUsersByGroupid(parameterMap);
		
		return users;
	}

}