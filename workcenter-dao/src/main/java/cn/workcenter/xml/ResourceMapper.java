package cn.workcenter.xml;

import java.util.List;

import cn.workcenter.model.Resource;

public interface ResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

	List<Resource> getResourcesByRoleName(String rolename);

	List<Resource> getResourcesByUserName(String username);
}