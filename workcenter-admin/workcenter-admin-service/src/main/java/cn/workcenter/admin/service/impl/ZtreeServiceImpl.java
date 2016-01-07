package cn.workcenter.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.workcenter.admin.model.ZNode;
import cn.workcenter.admin.service.ZtreeService;
import cn.workcenter.dao.RoleResourceMapper;
import cn.workcenter.model.Group;
import cn.workcenter.model.Resource;
import cn.workcenter.model.Role;
import cn.workcenter.model.RoleResource;
import cn.workcenter.model.User;
import cn.workcenter.service.GroupService;
import cn.workcenter.service.ResourceService;
import cn.workcenter.service.RoleService;
import cn.workcenter.service.UserService;

@Service("ztreeService")
public class ZtreeServiceImpl implements ZtreeService {

	@Autowired
	private GroupService groupService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired 
	 private RoleResourceMapper roleResourceMapper;
	
	
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
	@Override
	public List<ZNode> getRoleZtree(){
		List<ZNode> ztree = new ArrayList<ZNode>();
		Role role=new Role();
		role.setStatus(1);
		List<Role> roles = roleService.queryRoles(role);
		for(int i=0;i<roles.size();i++) {
			Role r = roles.get(i);
			ZNode node = ZNode.makeZNode(r);
			ztree.add(node);
			List<RoleResource> RoleResources = roleResourceMapper.getResourceByRoleid(r.getId());			
			node.setZLeafs(RoleResources);
			}
		return ztree;
	}

}
