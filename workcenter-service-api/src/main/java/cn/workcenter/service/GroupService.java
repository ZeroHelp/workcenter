package cn.workcenter.service;

import java.util.List;

import cn.workcenter.model.Group;

public interface GroupService {
	List<Group> queryGroups(Group group);

	Object addGroup(Group group);

	Object getGroupByGroupid(Long groupid);

	Object editGroup(Group group);

	Object forbiddenGroups(String groupids);

	Object deleteGroups(String groupids);

	List<Group> getAllGroups();
	
}
