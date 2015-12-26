package cn.workcenter.admin.service;

import java.util.List;
import cn.workcenter.admin.model.Menu;

public interface MenuService {

	List<Menu> getMenuTree(String menuResourceFlag);

}
