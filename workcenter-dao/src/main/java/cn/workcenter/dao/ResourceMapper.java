package cn.workcenter.dao;

import java.util.List;
import java.util.Map;

import cn.workcenter.model.Group;
import cn.workcenter.model.Resource;

public interface ResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);
    
    List<Resource> getResourcesByRoleid(Long roleId);
    
	List<Resource> getResourcesByRolename(String rolename);

	List<Resource> getResourcesByUsername(String username);

	List<Resource> getRootResourcesByUsername(String username);

	List<Resource> getResoucesByParentid(String parentId);

	List<Resource> queryResourcesBySelective(Resource resource);

	int updateResourceStatusById(Map<String, Object> parameterMap);
	
	List<Resource> queryResouces(Long roleId);
	
	List<Resource> getResoucesByRootParentId();

	List<Resource> queryAllResource();
	
}