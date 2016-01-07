package cn.workcenter.admin.model;

import java.util.ArrayList;
import java.util.List;

import cn.workcenter.model.Group;
import cn.workcenter.model.Nameable;

public class ZNode extends ZLeaf{
	
	private boolean open;
	
	private List<ZLeaf> children;
	
	private boolean isParent;
	
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public List<ZLeaf> getChildren() {
		return children;
	}
	public void setChildren(List<ZLeaf> children) {
		this.children = children;
	}
	public boolean isParent() {
		return isParent;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	
	public static <T extends Nameable> ZNode makeZNode(T name) {
		ZNode node = new ZNode();
		node.setName(name.getName());
		node.setOpen(true);
		node.setChildren(new ArrayList<ZLeaf>());
		node.setParent(true);
		return node;
	}
	public void setZLeafs(List<? extends Nameable> names) {
		for(int i=0;i<names.size();i++) {
			ZLeaf leaf = ZNode.makeZLeaf(names.get(i));
			children.add(leaf);
		}
	}
	
	public static <T extends Nameable> ZLeaf makeZLeaf(T name) {
		ZLeaf leaf = new ZLeaf();
		leaf.setName(name.getName());
		return leaf;
	}
}
