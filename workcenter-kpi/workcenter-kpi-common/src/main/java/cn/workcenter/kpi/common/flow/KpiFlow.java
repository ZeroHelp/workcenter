package cn.workcenter.kpi.common.flow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.workcenter.common.flow.DefaultFlow;
import cn.workcenter.dao.FlowTaskMapper;
import cn.workcenter.dao.FlowTaskinstanceMapper;
import cn.workcenter.kpi.common.KpiApplication;
import cn.workcenter.kpi.service.KpiService;
import cn.workcenter.model.FlowTask;
import cn.workcenter.model.FlowTaskinstance;
import cn.workcenter.model.User;
import cn.workcenter.service.FlowService;
import cn.workcenter.service.UserService;

@Component("kpiFlow")
public class KpiFlow extends DefaultFlow {

	@Autowired
	private UserService userService;
	@Autowired
	private FlowService flowService;
	@Autowired
	private KpiService kpiService;
	@Autowired
	private FlowTaskinstanceMapper flowTaskinstanceMapper;
	@Autowired
	private FlowTaskMapper flowTaskMapper;
	@Override
	public Object enter(Long processinstance_id, Long taskinstance_id) {
		
		checkRelatedRight(processinstance_id);
		FlowTaskinstance taskinstance = flowTaskinstanceMapper.selectByPrimaryKey(taskinstance_id);
		FlowTask task = flowTaskMapper.selectByPrimaryKey(taskinstance.getTaskId());
		checkCurrentNodeRight(processinstance_id, task.getNodeId());
		
		
		Map<String, Object> attributesAccess = flowService.getVariableaccess(task.getNodeId());
		Map<String, Object> main = kpiService.getMain(processinstance_id);
		List<Map<String, Object>> selfAttributes = kpiService.getSelfAttributes(processinstance_id);
		List<Map<String, Object>> culturalAttributes = kpiService.getCulturalAttributes(processinstance_id);
		
		readFilterAttributes(selfAttributes, attributesAccess);
		readFilterAttributes(culturalAttributes, attributesAccess);
		
		KpiApplication.requestThreadLocal.get().setAttribute("main", main);
		KpiApplication.requestThreadLocal.get().setAttribute("selfAttributes", selfAttributes);
		KpiApplication.requestThreadLocal.get().setAttribute("culturalAttributes", culturalAttributes);
		
		KpiApplication.requestThreadLocal.setALLAccesses(attributesAccess);
		if(2 == task.getNodeId()){//起草节点
			return "kpi/drawup.jsp";
		} else {
			return "kpi/detail.jsp";
		}
	}

	private void readFilterAttributes(List<Map<String, Object>> attributes, Map<String, Object> attributesAccess) {
		for(Map<String, Object> attibuteMap: attributes) {
			for(String key: attibuteMap.keySet()) {
				int access = (Integer)attributesAccess.get(key);
				if(access <= 0) {
					attibuteMap.remove(key);
				}
			}
		}
	}
	
	private void writeFilterAttributes(List<Map<String, Object>> attributes, Map<String, Object> attributesAccess) {
		for(Map<String, Object> attibuteMap: attributes) {
			for(String key: attibuteMap.keySet()) {
				int access = (Integer)attributesAccess.get(key);
				if(access < 2) {
					attibuteMap.remove(key);
				}
			}
		}
	}

	private void checkCurrentNodeRight(Long processinstance_id, Long node_id) {
		
		List<User> currentRightUsers = userService.getNodeRelatedUsers(processinstance_id, node_id);
		List<String> currentRightUsernames = new ArrayList<String>();
		for(User u: currentRightUsers) {
			currentRightUsernames.add(u.getUserName());
		}
		String currentUsername = userService.getUsername();
		if(!currentRightUsernames.contains(currentUsername))
			throw new RuntimeException("you are a stupid man!"); // > _ <
	}

	private void checkRelatedRight(Long processinstance_id) {
		List<User> relatedUsers = userService.getFlowRelatedUsers(processinstance_id);
		List<String> relatedUsernames = new ArrayList<String>();
		for(User u: relatedUsers) {
			relatedUsernames.add(u.getUserName());
		}
		String currentUsername = userService.getUsername();
		if(!relatedUsernames.contains(currentUsername)) 
			throw new RuntimeException("you are a stupid man!"); // > _ <
	}

	@Override
	public Object save(Long processinstance_id, Long taskinstance_id) {
		
		return null;
	}

	@Override
	public Object doNext(Long processinstance_id, Long taskinstance_id) {
		
		return null;
	}

	@Override
	public Object reject(Long processinstance_id, Long taskinstance_id) {
		
		return null;
	}

	@Override
	public Object view(Long processinstance_id, Long taskinstance_id) {
		
		return null;
	}
}
