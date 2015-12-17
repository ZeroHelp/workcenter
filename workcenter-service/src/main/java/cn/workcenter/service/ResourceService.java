package cn.workcenter.service;

import java.util.List;

import cn.workcenter.model.Resource;

public interface ResourceService {

	List<Resource> getResourcesByUserName(String username);

}
