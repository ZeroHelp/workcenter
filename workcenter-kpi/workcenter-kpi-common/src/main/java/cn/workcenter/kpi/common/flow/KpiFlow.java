package cn.workcenter.kpi.common.flow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.workcenter.common.flow.DefaultFlow;
import cn.workcenter.kpi.service.KpiService;
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
	@Override
	public Object enter(Long processinstance_id, Long node_id) {
		
		checkRelatedRight(processinstance_id);
		checkCurrentNodeRight(processinstance_id, node_id);
		
		Map<String, Integer> attributesAccess = flowService.getVariableaccess(node_id);
		List<Map<String, String>> selfAttributes = kpiService.getSelfAttributes();
		List<Map<String, String>> cultureAttributes = kpiService.getCultureAttributes();
		
		return null;
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
	public Object save(Long processinstance_id, Long node_id) {
		
		return null;
	}

	@Override
	public Object doNext(Long processinstance_id, Long node_id) {
		
		return null;
	}

	@Override
	public Object reject(Long processinstance_id, Long node_id) {
		
		return null;
	}

	@Override
	public Object view(Long processinstance_id, Long node_id) {
		
		return null;
	}
}
