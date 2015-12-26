package cn.workcenter.service;

import java.util.List;

import cn.workcenter.model.Resource;

/**
 * parent_id 
 * 
 * -1:annonymous
 *  0: workcenter url
 * -2:admin后台菜单页
 * 
 */
public interface ResourceService {

	List<Resource> getResourcesByUserName(String username);

	List<Resource> getUserModule();

	List<Resource> initRedisResource(String username);

	List<Resource> getResoucesByParentid(String parentId);

}
