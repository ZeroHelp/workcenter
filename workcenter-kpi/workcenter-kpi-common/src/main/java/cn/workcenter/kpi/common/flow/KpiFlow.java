package cn.workcenter.kpi.common.flow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.workcenter.common.constant.FlowConstant;
import cn.workcenter.common.flow.DefaultFlow;
import cn.workcenter.dao.FlowNodeMapper;
import cn.workcenter.dao.FlowTaskMapper;
import cn.workcenter.dao.FlowTaskinstanceMapper;
import cn.workcenter.dao.FlowTransitionMapper;
import cn.workcenter.kpi.common.KpiApplication;
import cn.workcenter.kpi.service.KpiService;
import cn.workcenter.model.FlowNode;
import cn.workcenter.model.FlowTask;
import cn.workcenter.model.FlowTaskinstance;
import cn.workcenter.model.FlowTransition;
import cn.workcenter.model.FlowVariableinstance;
import cn.workcenter.model.User;
import cn.workcenter.service.FlowService;
import cn.workcenter.service.UserService;

@Component("kpiFlow")
public class KpiFlow extends DefaultFlow implements FlowConstant {

	@Autowired
	private UserService userService;
	@Autowired
	private FlowService flowService;
	@Autowired
	private KpiService kpiService;
	
	@Override
	public Object enter(Long processinstance_id, Long taskinstance_id) {
		
		check(processinstance_id, taskinstance_id);
		
		FlowTaskinstance currentTaskinstance = flowService.getFlowTaskinstanceById(taskinstance_id);
		FlowTask task = flowService.getFlowTaskById(currentTaskinstance.getTaskId());
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
		
		return null;
	}
	
	@Override
	public Object doNext(Long processinstance_id, Long taskinstance_id) {
		check(processinstance_id, taskinstance_id);
		
		FlowTaskinstance currentTaskinstance = flowService.getFlowTaskinstanceById(taskinstance_id);
		FlowTask task = flowService.getFlowTaskById(currentTaskinstance.getTaskId());
		Map<String, FlowVariableinstance> flowvariableMap = flowService.getVariable(processinstance_id);
		Map<String, Object> attributesAccess = flowService.getVariableaccess(task.getNodeId());
		
		FlowNode currentNode = flowService.getFlowNodeById(task.getNodeId());
		if(!currentNode.getType().equals(END_NODE)&&!currentNode.getType().equals(START_NODE)) {
			List<Map<String, Object>> parameterSelfMapList = KpiApplication.requestThreadLocal.getSelfParameters(flowvariableMap);
			List<Map<String, Object>> parameterCulturalMapList = KpiApplication.requestThreadLocal.getCulturalParameters(flowvariableMap);
			
			writeFilterAttributes(parameterSelfMapList, attributesAccess);
			writeFilterAttributes(parameterCulturalMapList, attributesAccess);
			
			kpiService.saveSelfList(parameterSelfMapList);
			kpiService.saveCulturalList(parameterSelfMapList);
			
		} else if(currentNode.getType().equals(START_NODE)) {
			
		} else if(currentNode.getType().equals(END_NODE)) {
			
		} else {
			// never be here.
		}
		
		//super.doNext(processinstance_id, taskinstance_id);
		if(currentNode.getType().equals(TASK_NODE)){
			
			FlowTransition flowTransition = flowService.getFlowTransitionByFromNodeidAndProcessdefinationid(currentNode.getProcessdefinitionId(), currentNode.getId()); 
			FlowNode nextNode = flowService.getFlowNodeById(flowTransition.getToNodeId());
			
			FlowTaskinstance nextTaskinstance = (FlowTaskinstance) kpiService.findTaskinstance(processinstance_id, nextNode.getId());
			
			FlowTaskinstance cTaskinstance = (FlowTaskinstance) currentTaskinstance.clone();
			cTaskinstance.setIsOpen(2);
			FlowTaskinstance nTaskinstance = (FlowTaskinstance) nextTaskinstance.clone();
			nTaskinstance.setIsOpen(1);
			
			kpiService.updateTaskinstance(cTaskinstance);
			kpiService.updateTaskinstance(nTaskinstance);
		}
		
		FlowTransition flowTransition = flowService.getFlowTransitionByFromNodeidAndProcessdefinationid(currentNode.getProcessdefinitionId(), currentNode.getId()); 
		FlowNode nextNode = flowService.getFlowNodeById(flowTransition.getToNodeId());
		
		if(nextNode.getType().equals(END_NODE)) {
			//计算 总分  归档  
		}
		
		return null;
	}
	
	private void check(Long processinstance_id, Long taskinstance_id) {
		checkRelatedRight(processinstance_id);
		FlowTaskinstance currentTaskinstance = flowService.getFlowTaskinstanceById(taskinstance_id);
		FlowTask task = flowService.getFlowTaskById(currentTaskinstance.getTaskId());
		checkCurrentNodeRight(processinstance_id, task.getNodeId());
	}
	
	private void readFilterAttributes(List<Map<String, Object>> attributes, Map<String, Object> attributesAccess) {
		for(Map<String, Object> attibuteMap: attributes) {
			Set<String> keyset = attibuteMap.keySet();
			Iterator<String> iterator = keyset.iterator();
			for(String key = iterator.next();iterator.hasNext(); key = iterator.next()) {
				int access = (Integer)(attributesAccess.get(key + VARIABLEACCESS_SUFFIX)==null?0:attributesAccess.get(key + VARIABLEACCESS_SUFFIX));
				if(access <= 0) {
					if(key.contains("id")||key.contains("Id")) {
						continue;
					}
					iterator.remove();
				}
			}
		}
	}
	
	private void writeFilterAttributes(List<Map<String, Object>> attributes, Map<String, Object> attributesAccess) {
		for(Map<String, Object> attributeMap: attributes) {
			Set<String> keySet = attributeMap.keySet();
			Iterator<String> iterator = keySet.iterator();
			for(String key = iterator.next();iterator.hasNext();key = iterator.next()){
				int access = (Integer)(attributesAccess.get(key + VARIABLEACCESS_SUFFIX)==null?0:attributesAccess.get(key + VARIABLEACCESS_SUFFIX));
				if(access < 2) {
					if(key.contains("id")||key.contains("Id")) {
						continue;
					}
					iterator.remove();
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
	public Object reject(Long processinstance_id, Long taskinstance_id) {
		
		return null;
	}

	@Override
	public Object view(Long processinstance_id, Long taskinstance_id) {
		
		return null;
	}
}
