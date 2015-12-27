package cn.workcenter.kpi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.workcenter.common.util.StringUtil;
import cn.workcenter.dao.FlowTaskinstanceMapper;
import cn.workcenter.dao.FlowTokenMapper;
import cn.workcenter.kpi.common.KpiOperatorEnum;
import cn.workcenter.kpi.common.flow.KpiFlow;
import cn.workcenter.kpi.dao.EnactmentCulturalMapper;
import cn.workcenter.kpi.dao.EnactmentSelfMapper;
import cn.workcenter.kpi.dao.MainMapper;
import cn.workcenter.kpi.model.EnactmentCultural;
import cn.workcenter.kpi.model.EnactmentSelfWithBLOBs;
import cn.workcenter.kpi.model.Main;
import cn.workcenter.kpi.service.KpiService;
import cn.workcenter.model.FlowTaskinstance;
import cn.workcenter.model.FlowToken;
import cn.workcenter.service.UserService;

@Service("kpiService")
public class KpiServiceImpl implements KpiService {

	@Autowired
	private EnactmentSelfMapper enactmentSelfMapper;
	@Autowired
	private EnactmentCulturalMapper enactmentCulturalMapper;
	@Autowired
	private MainMapper mainMapper;
	@Autowired
	private KpiFlow kpiFlow;
	@Autowired
	private FlowTokenMapper flowTokenMapper;
	@Autowired
	private FlowTaskinstanceMapper flowTaskinstanceMapper;
	@Autowired
	private UserService userService;
	
	@Override
	public List<Map<String, Object>> getAssosiateKpis() {
		
		String username = userService.getUsername();
		List<Main> mains = mainMapper.findAssosiateKpisByUsername(username);
		
		setOperator(mains);
		
		List<Map<String, Object>> kpilist = new ArrayList<Map<String, Object>>();
		spellPageAttributes(kpilist, mains);
		return kpilist;
	}
	
	private void spellPageAttributes(List<Map<String, Object>> kpilist, List<Main> mains) {
		for(int i=1;i<=mains.size();i++) {
			Main main = mains.get(i-1);
			Map<String, Object> tempMap = new HashMap<String, Object>();
			tempMap.put("index", i);
			tempMap.put("year", main.getYear());
			tempMap.put("remark", main.getRemark());
			tempMap.put("waitAssessmentPerson", main.getWaitAssessmentPersonName());
			tempMap.put("assessmentPerson", main.getAssessmentPersonName());
			tempMap.put("grade" , StringUtil.isEmpty(main.getGrade()) ?"未评出": main.getGrade());
			tempMap.put("assessStatus", KpiOperatorEnum.getByAssessStatus(main.getAssessStatus()).getOperator());
			tempMap.put("operator", main.getOperator());
		}
	}

	private void setOperator(List<Main> mains) {
		
		for(Main main: mains) {
			String username = userService.getUsername();
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("username", username);
			parameterMap.put("tokenId", main.getRootTokenId());
			FlowTaskinstance taskInstance = flowTaskinstanceMapper.findTaskinstanceByTokenidAndUsername(parameterMap);
			String waitAssessmentPersonName = userService.getUsernameByUserid(main.getWaitAssessmentPersonId());
			String assessmentPersonName = userService.getUsernameByUserid(main.getAssessmentPersonId());
			main.setWaitAssessmentPersonName(waitAssessmentPersonName);
			main.setAssessmentPersonName(assessmentPersonName);
			
			if(taskInstance==null) {
				//当前节点非当前用户操作
				main.setOperator(KpiOperatorEnum.view.getOperator());
			} else {
				//当前节点为当前用户操作节点
				main.setOperator(KpiOperatorEnum.getByAssessStatus(main.getAssessStatus()).getOperator());
			}
		}
	}

	@Override
	public void doFlowGet(String method, Long main_id) {
		switch (method) {
		case "enter":
			enter(main_id);
			break;
		case "view":
			view(main_id);
			break;
		default:
			throw new RuntimeException("go away please!");
		}
	}
	
	@Override
	public Object doFlowPost(String method, Long main_id) {
		switch (method) {
		case "save":
			save(main_id);
			break;
		case "submit":
			submit(main_id);
			break;
		case "reject":
			reject(main_id);
			break;
		case "pass":
			pass(main_id);
			break;
		default:
			throw new RuntimeException("go away please!");
		}
		return null;
	}

	private void enter(Long main_id) {
		Main main = mainMapper.selectByPrimaryKey(main_id);
		Long tokenId = main.getRootTokenId();
		FlowToken flowToken = flowTokenMapper.selectByPrimaryKey(tokenId);
		Long nodeId = flowToken.getNodeId();
		kpiFlow.enter(main.getProcessinstanceId(), nodeId);
	}

	private void view(Long main_id) {

	}

	private void pass(Long main_id) {

	}

	private void save(Long main_id) {

	}

	private void submit(Long main_id) {

	}

	private void reject(Long main_id) {

	}

	@Override
	public List<Map<String, Object>> getSelfAttributes(Long processinstance_id) {
		List<EnactmentSelfWithBLOBs> enactmentSelfs = enactmentSelfMapper.getEnactmentSelfsByProcessinstanceid(processinstance_id);
		List<Map<String, Object>> enactmentSelfMapList = new ArrayList<Map<String, Object>>();
		for (EnactmentSelfWithBLOBs enactmentSelf : enactmentSelfs) {
			Map<String, Object> enactmentMap = new HashMap<String, Object>();
			enactmentMap.put("selfId", enactmentSelf.getId());
			enactmentMap.put("taskDirection", enactmentSelf.getSelfDirection());
			enactmentMap.put("selfGoal", enactmentSelf.getSelfGoal());
			enactmentMap.put("selfWeight", enactmentSelf.getSelfWeight());
			enactmentMap.put("selfEvaluate", enactmentSelf.getSelfEvaluate());
			enactmentMap.put("selfScore", enactmentSelf.getSelfScore());
			enactmentMap.put("leaderEvaluation", enactmentSelf.getLeaderEvaluation());
			enactmentMap.put("leaderScore", enactmentSelf.getLeaderScore());
			enactmentSelfMapList.add(enactmentMap);
		}
		return enactmentSelfMapList;
	}

	@Override
	public List<Map<String, Object>> getCulturalAttributes(Long processinstance_id) {
		List<EnactmentCultural> enactmentCulturals = enactmentCulturalMapper.getEnactmentCulturalsByProcessinstanceid(processinstance_id);
		List<Map<String, Object>> enactmentCulturalMapList = new ArrayList<Map<String, Object>>();
		for (EnactmentCultural enactmentCultural : enactmentCulturals) {
			Map<String, Object> enactmentMap = new HashMap<String, Object>();
			enactmentMap.put("cultualId", enactmentCultural.getId());
			enactmentMap.put("taskDirection", enactmentCultural.getTaskDirection());
			enactmentMap.put("taskContent", enactmentCultural.getTaskContent());
			enactmentMap.put("taskWeight", enactmentCultural.getTaskWeight());
			enactmentMap.put("taskScore", enactmentCultural.getTaskScore());
			enactmentCulturalMapList.add(enactmentMap);
		}
		return enactmentCulturalMapList;
	}

	@Override
	public Map<String, Object> getMain(Long processinstance_id) {
		Main main = mainMapper.getMainByProcessinstanceid(processinstance_id);
		Map<String, Object> mainMap = new HashMap<String, Object>();
		mainMap.put("mainId", main.getId());
		mainMap.put("year", main.getYear());
		mainMap.put("remark", main.getRemark());
		mainMap.put("grade", main.getGrade());
		mainMap.put("totalScore", main.getTotalScore());
		return mainMap;
	}

}
