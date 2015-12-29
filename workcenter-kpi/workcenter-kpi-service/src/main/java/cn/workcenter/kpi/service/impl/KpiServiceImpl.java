package cn.workcenter.kpi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.workcenter.common.util.StringUtil;
import cn.workcenter.dao.FlowTaskinstanceMapper;
import cn.workcenter.kpi.common.ClassHelper;
import cn.workcenter.kpi.common.KpiApplication;
import cn.workcenter.kpi.common.KpiConstant;
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
import cn.workcenter.service.UserService;

@Service("kpiService")
public class KpiServiceImpl implements KpiService, KpiConstant {

	@Autowired
	private EnactmentSelfMapper enactmentSelfMapper;
	@Autowired
	private EnactmentCulturalMapper enactmentCulturalMapper;
	@Autowired
	private MainMapper mainMapper;
	@Autowired
	private KpiFlow kpiFlow;
	@Autowired
	private FlowTaskinstanceMapper flowTaskinstanceMapper;
	@Autowired
	private UserService userService;
	
	@Override
	public List<Map<String, Object>> getAssosiateKpis() {
		
		String username = userService.getUsername();
		List<Main> filedMains = getKpisByUsernameAndIsfiled(username, FILED);
		setOperatorByIsFiled(filedMains, FILED);
		
		List<Main> notfiledMains = getKpisByUsernameAndIsfiled(username, NOT_FILED);
		setOperatorByIsFiled(notfiledMains, NOT_FILED);
		
		List<Main> mains = new ArrayList<Main>();
		mains.addAll(filedMains);
		mains.addAll(notfiledMains);
		
		List<Map<String, Object>> kpilist = new ArrayList<Map<String, Object>>();
		spellPageAttributes(kpilist, mains);
		
		return kpilist;
	}
	
	private void setOperatorByIsFiled(List<Main> mains, int isFiled) {
		for(Main main: mains) {
			String username = userService.getUsername();
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("username", username);
			parameterMap.put("processinstanceId", main.getProcessinstanceId());
			String waitAssessmentPersonName = userService.getUserRealnameByUserid(main.getWaitAssessmentPersonId());
			String assessmentPersonName = userService.getUserRealnameByUserid(main.getAssessmentPersonId());
			main.setWaitAssessmentPersonName(waitAssessmentPersonName);
			main.setAssessmentPersonName(assessmentPersonName);
			if(FILED == isFiled) {
				//历史归档 绩效
				main.setOperator(KpiOperatorEnum.view.getOperator());
				main.setMethod("view");
			} else if(NOT_FILED == isFiled) {
				//用户所属任务 操作
				main.setOperator(KpiOperatorEnum.getOperatorByAssessStatus(main.getAssessStatus()));
				main.setMethod("enter");
			} 
			
		}
	}

	private List<Main> getKpisByUsernameAndIsfiled(String username, int isFiled) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("username", username);
		parameterMap.put("is_filed", isFiled);
		List<Main> mains = null;
		if(FILED == isFiled) {
			mains =mainMapper.findFiledKpisByUsernameAndIsfiled(parameterMap);
		} else {
			//NOT_FILED
			parameterMap.put("is_open", OPENED);
			mains =mainMapper.findFiledKpisByUsernameAndNotfiledAndIsopentask(parameterMap);
		}
				
		return mains;
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
			tempMap.put("assessStatus", main.getAssessStatus());
			tempMap.put("assessStatusName", KpiOperatorEnum.getassessStatusByAssessStatus(main.getAssessStatus()));
			tempMap.put("method", main.getMethod());
			tempMap.put("operator", main.getOperator());
			tempMap.put("taskinstanceId", main.getTaskinstanceId());
			tempMap.put("mainId", main.getId());
			kpilist.add(tempMap);
		}
	}

	@Override
	public Object doFlowGet(String method, Long main_id, Long taskinstance_id) {
		Object obj = null;
		switch (method) {
		case "enter":
			obj = enter(main_id, taskinstance_id);
			break;
		case "view":
			obj = view(main_id, taskinstance_id);
			break;
		default:
			throw new RuntimeException("go away please!");
		}
		return obj;
	}
	
	@Override
	public Object doFlowPost(String method, Long main_id, Long taskinstance_id) {
		Object obj = null;
		switch (method) {
		case "save":
			obj = save(main_id, taskinstance_id);
			break;
		case "submit":
			obj = submit(main_id, taskinstance_id);
			break;
		case "reject":
			obj = reject(main_id, taskinstance_id);
			break;
		default:
			throw new RuntimeException("go away please!");
		}
		return null;
	}

	private Object enter(Long main_id, Long taskinstance_id) {
		Main main = mainMapper.selectByPrimaryKey(main_id);
		return kpiFlow.enter(main.getProcessinstanceId(), taskinstance_id);
	}

	private Object view(Long main_id, Long taskinstance_id) {
		
		return null;
	}

	private Object save(Long main_id, Long taskinstance_id) {
		return null;
	}
	
	private Object submit(Long main_id, Long taskinstance_id) {
		Main main = mainMapper.selectByPrimaryKey(main_id);
		return kpiFlow.doNext(main.getProcessinstanceId(), taskinstance_id);
		//return null;
	}

	private Object reject(Long main_id, Long taskinstance_id) {
		return null;
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

	@Override
	public boolean saveSelfList(List<Map<String, Object>> parameterSelfMapList) {
		for(Map<String, Object> parameterSelfMap: parameterSelfMapList) {
			if(StringUtils.isEmpty(parameterSelfMap.get(KpiApplication.requestThreadLocal.getSelfIdKey()))) {
				EnactmentSelfWithBLOBs enactSelf = new EnactmentSelfWithBLOBs();
				ClassHelper.setAttributes(enactSelf, parameterSelfMap);
				enactmentSelfMapper.insert(enactSelf);
			} else {
				EnactmentSelfWithBLOBs enactSelf = new EnactmentSelfWithBLOBs();
				ClassHelper.setAttributes(enactSelf, parameterSelfMap);
				enactmentSelfMapper.updateByPrimaryKeyWithBLOBs(enactSelf);
			}
		}
		return true;
	}

	@Override
	public boolean saveCulturalList(List<Map<String, Object>> parameterSelfMapList) {
		for(Map<String, Object> parameterSelfMap: parameterSelfMapList) {
			if(StringUtils.isEmpty(parameterSelfMap.get(KpiApplication.requestThreadLocal.getSelfIdKey()))) {
				EnactmentCultural enactCultural = new EnactmentCultural();
				ClassHelper.setAttributes(enactCultural, parameterSelfMap);
				enactmentCulturalMapper.insert(enactCultural);
			} else {
				EnactmentCultural enactCultural = new EnactmentCultural();
				ClassHelper.setAttributes(enactCultural, parameterSelfMap);
				enactmentCulturalMapper.updateByPrimaryKeyWithBLOBs(enactCultural);
			}
		}
		return true;
	}

	@Override
	public FlowTaskinstance findTaskinstance(Long processinstance_id, Long nextNodeid) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("processinstance_id", processinstance_id);
		parameterMap.put("nextNodeid", nextNodeid);
		FlowTaskinstance flowTaskinstance = flowTaskinstanceMapper.getFlowTaskinstanceByProcessinstanceidandNodeid(parameterMap);
		
		return flowTaskinstance;
	}

	@Override
	public void updateTaskinstance(FlowTaskinstance taskinstance) {
		flowTaskinstanceMapper.updateByPrimaryKeySelective(taskinstance);
	}

}
