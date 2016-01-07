package cn.workcenter.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.workcenter.admin.model.ZNode;
import cn.workcenter.admin.service.ZtreeService;
import cn.workcenter.model.Group;
import cn.workcenter.model.User;
import cn.workcenter.service.GroupService;
import cn.workcenter.service.UserService;

@Service("ztreeService")
public class ZtreeServiceImpl implements ZtreeService {

	@Autowired
	private GroupService groupService;
	@Autowired
	private UserService userService;
	
	@Override
	public List<ZNode> getGroupZtree() {
		List<ZNode> ztree = new ArrayList<ZNode>();
		
		Group group = new Group();
		group.setStatus(1);
		List<Group> groups = groupService.queryGroups(group);
		for(int i=0;i<groups.size();i++) {
			Group g = groups.get(i);
			ZNode node = ZNode.makeZNode(g);
			ztree.add(node);
			List<User> users = userService.getUsersByGroupid(g.getId());
			node.setZLeafs(users);
		}
		return ztree;
	}

}
