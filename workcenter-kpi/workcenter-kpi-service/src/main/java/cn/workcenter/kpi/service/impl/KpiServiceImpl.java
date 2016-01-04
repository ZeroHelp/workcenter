package cn.workcenter.kpi.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.workcenter.common.constant.FlowConstant;
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
public class KpiServiceImpl implements KpiService, KpiConstant, FlowConstant {

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
		
		List<Main> notfiledNotCurrentoperatorMains = getKpisByUsernameAndNotFiledAndNotoperator(username, NOT_FILED);
		setOperatorByIsFiled(notfiledNotCurrentoperatorMains, -1);
		
		List<Main> mains = new ArrayList<Main>();
		mains.addAll(filedMains);
		mains.addAll(notfiledMains);
		mains.addAll(notfiledNotCurrentoperatorMains);
		Collections.sort(mains, new cn.workcenter.common.util.IdComparator());
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
				//在运行任务 用户所属任务 操作
				main.setOperator(KpiOperatorEnum.getOperatorByAssessStatus(main.getAssessStatus()));
				main.setMethod("enter");
			} else {
				//在运行任务 用户关联任务 可查看
				main.setOperator(KpiOperatorEnum.view.getOperator());
				main.setMethod("view");
			}	
			
		}
	}
	
	private List<Main> getKpisByUsernameAndNotFiledAndNotoperator(String username, int notFiled) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("username", username);
		parameterMap.put("is_filed", notFiled);
		parameterMap.put("is_open", OPEN);
		List<Main> mains = mainMapper.findFiledKpisByUsernameAndNotfiledAndIsopentaskAndNotcurrentuser(parameterMap);
		return mains;
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
			parameterMap.put("is_open", OPEN);
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
	public Object doFlowGet(String method, Long main_id) {
		Object obj = null;
		switch (method) {
		case "enter":
			obj = enter(main_id);
			break;
		case "view":
			obj = view(main_id);
			break;
		default:
			throw new RuntimeException("go away please!");
		}
		return obj;
	}
	
	@Override
	public Object doFlowPost(String method, Long main_id) {
		Object obj = null;
		switch (method) {
		case "save"://保存草稿
			obj = save(main_id);
			break;
		case "submit"://提交审批
			obj = submit(main_id);
			break;
			
		case "pass"://通过审批
			obj = submit(main_id);
			break;
		case "unpass"://不通过审批
			obj = reject(main_id);
			break;
		case "saveEv"://保存自评
			obj = save(main_id);
			break;
		case "submitEv"://提交审评
			obj = submit(main_id);
			break;
		case "reject"://驳回
			obj = reject(main_id);
			break;
		case "finish"://审评完成
			obj = submit(main_id);
			break;
		default:
			throw new RuntimeException("go away please!");
		}
		return null;
	}

	private Object enter(Long main_id) {
		Main main = mainMapper.selectByPrimaryKey(main_id);
		FlowTaskinstance flowTaskinstance = flowTaskinstanceMapper.getCurrentOpenTaskinstance(main.getProcessinstanceId());
		return kpiFlow.enter(main.getProcessinstanceId(), flowTaskinstance.getId());
	}

	private Object view(Long main_id) {
		Main main = mainMapper.selectByPrimaryKey(main_id);
		return kpiFlow.view(main.getProcessinstanceId(), 0L);
	}

	private Object save(Long main_id) {
		Main main = mainMapper.selectByPrimaryKey(main_id);
		FlowTaskinstance flowTaskinstance = flowTaskinstanceMapper.getCurrentOpenTaskinstance(main.getProcessinstanceId());
		return kpiFlow.save(main.getProcessinstanceId(), flowTaskinstance.getId());
	}
	
	private Object submit(Long main_id) {
		Main main = mainMapper.selectByPrimaryKey(main_id);
		FlowTaskinstance flowTaskinstance = flowTaskinstanceMapper.getCurrentOpenTaskinstance(main.getProcessinstanceId());
		return kpiFlow.doNext(main.getProcessinstanceId(), flowTaskinstance.getId());
	}

	private Object reject(Long main_id) {
		Main main = mainMapper.selectByPrimaryKey(main_id);
		FlowTaskinstance flowTaskinstance = flowTaskinstanceMapper.getCurrentOpenTaskinstance(main.getProcessinstanceId());
		return kpiFlow.reject(main.getProcessinstanceId(), flowTaskinstance.getId());
	}

	@Override
	public List<Map<String, Object>> getSelfAttributes(Long processinstance_id) {
		List<EnactmentSelfWithBLOBs> enactmentSelfs = enactmentSelfMapper.getEnactmentSelfsByProcessinstanceid(processinstance_id);
		List<Map<String, Object>> enactmentSelfMapList = new ArrayList<Map<String, Object>>();
		for (EnactmentSelfWithBLOBs enactmentSelf : enactmentSelfs) {
			Map<String, Object> enactmentMap = new HashMap<String, Object>();
			enactmentMap.put("selfId", enactmentSelf.getId());
			enactmentMap.put("selfDirection", enactmentSelf.getSelfDirection());
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
	public boolean saveSelfList(List<Map<String, Object>> parameterSelfMapList, Long main_id) {
		for(int i=0;i<parameterSelfMapList.size();i++) {
			Map<String, Object> parameterSelfMap = parameterSelfMapList.get(i);
			if(StringUtils.isEmpty(parameterSelfMap.get(KpiApplication.requestThreadLocal.getSelfIdKey()))) {
				EnactmentSelfWithBLOBs enactSelf = new EnactmentSelfWithBLOBs();
				ClassHelper.setAttributes(enactSelf, parameterSelfMap);
				enactSelf.setKpiMainId(main_id);
				enactSelf.setStatus(1);
				enactSelf.setIndexNum(i);
				//空的 个人设定不入库
				if(enactSelf.getSelfDirection()==null && enactSelf.getSelfGoal()==null
						&& enactSelf.getSelfWeight()==null){
					continue;
				}
				enactmentSelfMapper.insert(enactSelf);
			} else {
				EnactmentSelfWithBLOBs enactSelf = new EnactmentSelfWithBLOBs();
				ClassHelper.setAttributes(enactSelf, parameterSelfMap);
				enactSelf.setKpiMainId(main_id);
				enactSelf.setStatus(1);
				enactSelf.setIndexNum(i);
				enactmentSelfMapper.updateByPrimaryKeySelective(enactSelf);
			}
		}
		return true;
	}

	@Override
	public boolean saveCulturalList(List<Map<String, Object>> parameterCulturalMapList, Long main_id) {
		for(int i=0;i<parameterCulturalMapList.size();i++ ) {
			Map<String, Object> parameterCulturalMap = parameterCulturalMapList.get(i);
			if(StringUtils.isEmpty(parameterCulturalMap.get(KpiApplication.requestThreadLocal.getCulturalIdKey()))) {
				EnactmentCultural enactCultural = new EnactmentCultural();
				ClassHelper.setAttributes(enactCultural, parameterCulturalMap);
				enactCultural.setKpiMainId(main_id);
				enactCultural.setStatus(1);
				enactCultural.setIndexNum(i);
				enactmentCulturalMapper.insert(enactCultural);
			} else {
				EnactmentCultural enactCultural = new EnactmentCultural();
				ClassHelper.setAttributes(enactCultural, parameterCulturalMap);
				enactCultural.setKpiMainId(main_id);
				enactCultural.setStatus(1);
				enactCultural.setIndexNum(i);
				enactmentCulturalMapper.updateByPrimaryKeySelective(enactCultural);
			}
		}
		return true;
	}

	@Override
	public FlowTaskinstance findTaskinstance(Long processinstance_id, Long nextNodeid) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("processinstance_id", processinstance_id);
		parameterMap.put("node_id", nextNodeid);
		FlowTaskinstance flowTaskinstance = flowTaskinstanceMapper.getFlowTaskinstanceByProcessinstanceidandNodeid(parameterMap);
		
		return flowTaskinstance;
	}

	@Override
	public void doCalculateLogic(Long processinstance_id) {
		Main main = mainMapper.getMainByProcessinstanceid(processinstance_id);
		List<EnactmentSelfWithBLOBs> selfs = enactmentSelfMapper.getEnactmentSelfsByProcessinstanceid(processinstance_id);
		List<EnactmentCultural> culturals = enactmentCulturalMapper.getEnactmentCulturalsByProcessinstanceid(processinstance_id);
		BigDecimal totalScore = new BigDecimal(0);
		for(EnactmentSelfWithBLOBs self : selfs) {
			BigDecimal partWeight = new BigDecimal(self.getSelfWeight()).divide(new BigDecimal(100));
			BigDecimal partScore = self.getLeaderScore();
			BigDecimal partTotal = partWeight.multiply(partScore);
			totalScore = totalScore.add(partTotal);
		}
		String grade = calculateGrade(totalScore);
		
		Main cmain = main.clone();
		cmain.setGrade(grade);
		cmain.setTotalScore(totalScore);
		mainMapper.updateByPrimaryKeySelective(cmain);
		
	}

	private String calculateGrade(BigDecimal totalScore) {
		double score = totalScore.doubleValue();
		if(score >=4.75 && score <= 5.5) 
			return "A";
		if(score >=4 && score < 4.75)
			return "B";
		if(score >= 3 && score < 4)
			return "C";
		if(score >=2 && score < 3)
			return "D";
		if(score >= 0 && score < 2)
			return "E";
		return "";
	}

	@Override
	public Main getMainByProcessinstanceid(Long processinstance_id) {
		Main main = mainMapper.getMainByProcessinstanceid(processinstance_id);
		return main;
	}

	@Override
	public void doNextTaskinstancePrepare(FlowTaskinstance currentTaskinstance, FlowTaskinstance nextTaskinstance) {
		FlowTaskinstance cTaskinstance = (FlowTaskinstance) currentTaskinstance.clone();
		cTaskinstance.setIsOpen(2);
		FlowTaskinstance nTaskinstance = (FlowTaskinstance) nextTaskinstance.clone();
		nTaskinstance.setIsOpen(1);
		
		flowTaskinstanceMapper.updateByPrimaryKeySelective(cTaskinstance);
		flowTaskinstanceMapper.updateByPrimaryKeySelective(nTaskinstance);
		
		
	}

	@Override
	public void doNextEndTaskinstancePrepare(FlowTaskinstance currentTaskinstance) {
		FlowTaskinstance cTaskinstance = (FlowTaskinstance) currentTaskinstance.clone();
		cTaskinstance.setIsOpen(2);
		flowTaskinstanceMapper.updateByPrimaryKeySelective(cTaskinstance);
	}

	@Override
	public void doPreTaskinstancePrepare(FlowTaskinstance preTaskinstance ,FlowTaskinstance currentTaskinstance) {
		FlowTaskinstance pTaskinstance = (FlowTaskinstance) preTaskinstance.clone();
		pTaskinstance.setIsOpen(1);
		FlowTaskinstance cTaskinstance = (FlowTaskinstance) currentTaskinstance.clone();
		cTaskinstance.setIsOpen(0);
		
		flowTaskinstanceMapper.updateByPrimaryKeySelective(pTaskinstance);
		flowTaskinstanceMapper.updateByPrimaryKeySelective(cTaskinstance);
		
	}

	@Override
	public void doPreStartTaskinstancePrepare(FlowTaskinstance currentTaskinstance) {
		FlowTaskinstance cTaskinstance = (FlowTaskinstance) currentTaskinstance.clone();
		cTaskinstance.setIsOpen(0);
		flowTaskinstanceMapper.updateByPrimaryKeySelective(cTaskinstance);
	}

	@Override
	public void doNextMainPrepare(Long processinstance_id, Long node_id) {
		Main main = getMainByProcessinstanceid(processinstance_id);
		Main cmain = main.clone();
		cmain.setAssessStatus(node_id.intValue());
		mainMapper.updateByPrimaryKeySelective(cmain);
	}

	@Override
	public void doPreMainPrepare(Long processinstance_id, Long node_id) {
		Main main = getMainByProcessinstanceid(processinstance_id);
		Main cmain = main.clone();
		cmain.setAssessStatus(node_id.intValue());
		mainMapper.updateByPrimaryKeySelective(cmain);
	}

}
