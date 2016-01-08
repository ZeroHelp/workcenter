package cn.workcenter.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.workcenter.admin.model.ZLeaf;
import cn.workcenter.admin.model.ZNode;
import cn.workcenter.admin.service.ZtreeService;
import cn.workcenter.dao.GroupMapper;
import cn.workcenter.dao.ResourceMapper;
import cn.workcenter.model.Group;
import cn.workcenter.model.Resource;
import cn.workcenter.model.Role;
import cn.workcenter.model.User;
import cn.workcenter.service.RoleService;
import cn.workcenter.service.UserService;

@Service("ztreeService")
public class ZtreeServiceImpl implements ZtreeService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired 
	private ResourceMapper resourceMapper;
	@Autowired 
	private GroupMapper groupMapper;
	@Override
	public List<ZNode> getGroupZtree() {
		List<ZNode> ztree = new ArrayList<ZNode>();	
		Group group = new Group();
		group.setStatus(1);
		List<Group> groups = groupMapper.queryGroupsByParentId(group);
		for(int i=0;i<groups.size();i++) {
			Group g = groups.get(i);
			ZNode node = ZNode.makeZNode(g);
			ztree.add(node);
			List<User> users = userService.getUsersByGroupid(g.getId());
			node.setZLeafs(users);
			setSubZNode(node,g.getId());
		}
		return ztree;
	}
	
	public void setSubZNode(ZNode rootzNode,Long id){
		List<ZLeaf> ztreec = new ArrayList<ZLeaf>();	
		Group groupc = new Group();
		groupc.setStatus(1);
		groupc.setParentId(id);
		List<Group> groupcs=groupMapper.selectByParent(groupc);	
		if(groupcs!=null &&groupcs.size()>0){
			for(int j=0;j<groupcs.size();j++){
				Group c = groupcs.get(j);	
				ZNode nodec = ZNode.makeZNode(c);
				ztreec.add(nodec);
				List<User> userc = userService.getUsersByGroupid(c.getId());
				nodec.setZLeafs(userc);	
				rootzNode.getChildren().addAll(ztreec);
				setSubZNode(nodec,c.getId());
			}
		}
	}
	@Override
	public List<ZNode> getRoleZtree() {
		List<ZNode> ztree = new ArrayList<ZNode>();
		Role role = new Role();
		role.setStatus(1);
		List<Role> roles = roleService.queryRoles(role);
		for (int i = 0; i < roles.size(); i++) {
			Role r = roles.get(i);
			ZNode node = ZNode.makeZNode(r);
			ztree.add(node);
			List<Resource> resources = resourceMapper.queryResouces(r.getId());
			node.setZLeafs(resources);
		}
		return ztree;
	}

	@Override
	public List<ZNode> getUserRoleZtree() {
		// TODO Auto-generated method stub
		List<ZNode> ztree=new ArrayList<ZNode>();
		List<User> users= userService.getAllUsers();
		for(int i=0;i<users.size();i++){		
			User u=users.get(i);
			ZNode node=ZNode.makeZNode(u);
			ztree.add(node);
			List<Role> roles=roleService.queryRolesByUserId(u.getId());
			node.setZLeafs(roles);
		}
		return ztree;
	}

	@Override
	public List<ZNode> getResourcesZtree() {
		// TODO Auto-generated method stub
		
		List<ZNode> ztree=new ArrayList<ZNode>();
		List<Resource> resources=resourceMapper.getResoucesByRootParentId();
		
		
		for(int i=0;i<resources.size();i++){
			Resource resource=resources.get(i);
			ZNode node=ZNode.makeZNode(resource);
			ztree.add(node);
			
			makeResourcesZtree(node, resource.getId());
			/*List<Resource> r=resourceMapper.getResoucesById(resource.getId());
			node.setZLeafs(r);
			setSubResourceZNode(node,resource.getId());*/
		}
		return ztree;
	}
	private List<Resource> makeResourcesZtree(ZNode node, Long resourceid) {
		
		List<Resource> childs=resourceMapper.getResoucesByParentid(resourceid+"");	
		for(int i=0;i<childs.size();i++) {
			ZNode znode = ZNode.makeZNode(childs.get(i));
			
			List<Resource> childschilds = makeResourcesZtree(znode, childs.get(i).getId());
			if(childschilds==null||childschilds.size()==0) {
				ZLeaf zleaf = ZNode.makeZLeaf(childs.get(i));
				node.addChilden(zleaf);
				continue;
			}
			node.addChilden(znode);
		}
		return childs;
	}

	public void setSubResourceZNode(ZNode rootzNode,Long id){
		List<ZLeaf> ztreec = new ArrayList<ZLeaf>();	
		Resource resource = new Resource();
		resource.setStatus(1);
		resource.setParentId(id);
		List<Resource> rs=resourceMapper.selectByParent(resource);	
		if(rs!=null &&rs.size()>0){
			for(int j=0;j<rs.size();j++){
				Resource c = rs.get(j);	
				ZNode nodec = ZNode.makeZNode(c);
				ztreec.add(nodec);
				List<Resource> resourcec = resourceMapper.selectByParent(c);	
				nodec.setZLeafs(resourcec);	
				rootzNode.getChildren().addAll(ztreec);
				setSubZNode(nodec,c.getId());
			}
		}
	}
}
