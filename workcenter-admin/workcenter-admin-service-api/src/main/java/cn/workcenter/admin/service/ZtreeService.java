package cn.workcenter.admin.service;

import java.util.List;

import cn.workcenter.admin.model.ZNode;

public interface ZtreeService {

	List<ZNode> getGroupZtree();

	List<ZNode> getRoleZtree();

	List<ZNode> getUserRoleZtree();
	List<ZNode> getResourcesZtree();
}
