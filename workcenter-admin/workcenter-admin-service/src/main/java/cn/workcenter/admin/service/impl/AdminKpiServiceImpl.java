package cn.workcenter.admin.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.workcenter.admin.service.AdminKpiService;
import cn.workcenter.admin.service.AdminUserService;
import cn.workcenter.common.constant.FlowConstant;
import cn.workcenter.dao.FlowProcessinstanceMapper;
import cn.workcenter.dao.FlowSwimlaneMapper;
import cn.workcenter.dao.FlowSwimlaneUserMapper;
import cn.workcenter.dao.FlowTaskMapper;
import cn.workcenter.dao.FlowTaskinstanceMapper;
import cn.workcenter.kpi.dao.EnactmentCulturalMapper;
import cn.workcenter.kpi.dao.MainMapper;
import cn.workcenter.kpi.model.EnactmentCultural;
import cn.workcenter.kpi.model.Main;
import cn.workcenter.model.FlowProcessinstance;
import cn.workcenter.model.FlowSwimlane;
import cn.workcenter.model.FlowSwimlaneUser;
import cn.workcenter.model.FlowTask;
import cn.workcenter.model.FlowTaskinstance;
import cn.workcenter.model.User;
import cn.workcenter.service.UserService;

@Service("adminKpiService")
public class AdminKpiServiceImpl implements AdminKpiService, FlowConstant {

	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private UserService userService;
	@Autowired
	private FlowTaskMapper flowTaskMapper;
	@Autowired
	private FlowProcessinstanceMapper flowProcessinstanceMapper;
	@Autowired
	private FlowSwimlaneMapper flowSwimlaneMapper;
	@Autowired
	private FlowTaskinstanceMapper flowTaskinstanceMapper;
	@Autowired
	private FlowSwimlaneUserMapper flowSwimlaneUserMapper;
	@Autowired
	private MainMapper mainMapper;
	@Autowired
	private EnactmentCulturalMapper enactmentCulturalMapper;
	@Override
	public void initKpi(String year, String remark, Long groupId) {
		
		List<User> users = adminUserService.getWaitAssessments(groupId);
		
		for(User writer: users) {
			Long flowProcessinstance_id = creatKpi(writer, year, remark);
			startKpi(flowProcessinstance_id);
		}
	}

	private void startKpi(Long flowProcessinstance_id) {
		List<FlowTaskinstance> taskinstanceList = flowTaskinstanceMapper.getTaskinstanceByProcessinstanceid(flowProcessinstance_id);
		FlowTaskinstance firstTaskinstance = taskinstanceList.get(0);
		FlowTaskinstance cfirstTaskinstance = firstTaskinstance.clone();
		cfirstTaskinstance.setIsOpen(OPEN);
		flowTaskinstanceMapper.updateByPrimaryKeySelective(cfirstTaskinstance);
	}

	/**
	 * 1.创建processinstance
	 * 2.根据task组件 创建任务 关联人组 swimelane
	 * 3.创建taskinstance
	 * 4.创建main cultural
	 * @param writer
	 * @param year
	 * @param remark
	 */
	public Long creatKpi(User writer, String year, String remark) {
		
		Long processdefinition_id = 1L;
		
		Long flowProcessinstance_id = createProcessinstance(processdefinition_id);
		
		List<FlowTask> processtaskList = flowTaskMapper.getFlowTaskByprocessdefinitionid(processdefinition_id);
		Long wait_swimlane_id = null;
		Long assessment_swimlane_id = null;
		User assessment = null;
		for(FlowTask flowTask: processtaskList) {
			int actorType = flowTask.getActorType();
			
			switch(actorType) {
			case 1://起草人
				if(wait_swimlane_id==null){
					wait_swimlane_id = createTaskinstanceSwimlane(writer, flowProcessinstance_id);
				} 
				//创建节点实例关联人
				createFlowTaskinstance(wait_swimlane_id, flowProcessinstance_id, flowTask);
				break;
			case 2://起草人直属领导
				if(assessment_swimlane_id==null){
					assessment = userService.getUserTeamLeader(writer.getId());
					assessment_swimlane_id = createTaskinstanceSwimlane(assessment, flowProcessinstance_id);
				} 
				//创建节点实例关联人
				createFlowTaskinstance(assessment_swimlane_id, flowProcessinstance_id, flowTask);
				break;
			case 3://某角色所有的人 假如角色一个人则直接设置
				break;
			}
		}
		//创建流程实例关联人
		Long processinstanceSwimlane_id = createProcessinstanceSwimlane(flowProcessinstance_id, writer, assessment);
		updateProcessinstanceSwimlaneid(flowProcessinstance_id, processinstanceSwimlane_id);
		
		
		//3.创建main
		Long mainId = createMain(writer, assessment, flowProcessinstance_id, year, remark);
		createCultural(mainId);
		
		return flowProcessinstance_id;
	}
	
	
	private void createCultural(Long mainId) {
		EnactmentCultural enactmentCultural = new EnactmentCultural();
		enactmentCultural.setKpiMainId(mainId);
		enactmentCultural.setIndexNum(0);
		enactmentCultural.setTaskDirection("能力素质");
		enactmentCultural.setStatus(1);
		enactmentCulturalMapper.insertSelective(enactmentCultural);
		
	}

	private Long createMain(User writer, User assessment, Long flowProcessinstance_id, String year, String remark) {
		Main main = new Main();
		main.setId(null);
		main.setProcessinstanceId(flowProcessinstance_id);
		main.setWaitAssessmentPersonId(writer.getId());
		main.setAssessmentPersonId(assessment.getId());
		main.setYear(year);
		main.setRemark(remark);
		main.setAssessStatus(2);
		main.setStatus(1);
		mainMapper.insertSelective(main);
		return main.getId();
	}

	private Long createProcessinstanceSwimlane(Long flowProcessinstance_id, User writer, User assessment) {
		
		FlowSwimlane flowSwimlane = new FlowSwimlane();
		flowSwimlane.setName("process_"+flowProcessinstance_id);
		flowSwimlane.setChName("process_"+flowProcessinstance_id+"流程关联人");
		flowSwimlane.setCreateTime(new Date());
		flowSwimlane.setCreateUserId(userService.getUserId());

		flowSwimlaneMapper.insertSelective(flowSwimlane);
		
		FlowSwimlaneUser flowSwimlaneUser = new FlowSwimlaneUser();
		flowSwimlaneUser.setUserId(writer.getId());
		flowSwimlaneUser.setSwimlaneId(flowSwimlane.getId());
		flowSwimlaneUser.setStatus(1);
		flowSwimlaneUserMapper.insertSelective(flowSwimlaneUser);
		
		FlowSwimlaneUser otherFlowSwimlaneUser = flowSwimlaneUser.clone();
		otherFlowSwimlaneUser.setId(null);
		otherFlowSwimlaneUser.setUserId(assessment.getId());
		flowSwimlaneUserMapper.insertSelective(otherFlowSwimlaneUser);
		
		return flowSwimlane.getId();
	}

	private void updateProcessinstanceSwimlaneid(Long flowProcessinstance_id, Long processinstanceSwimlane_id) {
		FlowProcessinstance flowProcessinstance = flowProcessinstanceMapper.selectByPrimaryKey(flowProcessinstance_id);
		FlowProcessinstance cflowProcessinstance = flowProcessinstance.clone();
		cflowProcessinstance.setSwimlaneId(processinstanceSwimlane_id);
		flowProcessinstanceMapper.updateByPrimaryKeySelective(cflowProcessinstance);
		
	}

	private void createFlowTaskinstance(Long swimlane_id, Long flowProcessinstance_id, FlowTask flowTask) {
		FlowTaskinstance flowTaskinstance = new FlowTaskinstance();
		flowTaskinstance.setSwimlaneId(swimlane_id);
		flowTaskinstance.setProcessinstanceId(flowProcessinstance_id);
		flowTaskinstance.setTaskId(flowTask.getId());
		flowTaskinstance.setName(flowTask.getName());
		flowTaskinstance.setCreateTime(new Date());
		flowTaskinstance.setIsCancelled(NOT_CANCELLED);
		flowTaskinstance.setIsSuspended(NOT_SUSPENDED);
		flowTaskinstance.setIsOpen(NOT_OPEN);
		flowTaskinstance.setIsBlocking(NOT_BLOCKING);
		
		flowTaskinstanceMapper.insertSelective(flowTaskinstance);
	}
	
	
	
	private Long createTaskinstanceSwimlane(User user, Long flowProcessinstance_id) {
		FlowSwimlane flowSwimlane = new FlowSwimlane();
		flowSwimlane.setName("node_"+flowProcessinstance_id+"_"+user.getUserName());
		flowSwimlane.setChName("node_"+flowProcessinstance_id+"_"+user.getRealName()+"节点流程关联人");
		flowSwimlane.setCreateTime(new Date());
		flowSwimlane.setCreateUserId(userService.getUserId());

		flowSwimlaneMapper.insertSelective(flowSwimlane);
		
		FlowSwimlaneUser flowSwimlaneUser = new FlowSwimlaneUser();
		flowSwimlaneUser.setUserId(user.getId());
		flowSwimlaneUser.setSwimlaneId(flowSwimlane.getId());
		flowSwimlaneUser.setStatus(1);
		flowSwimlaneUserMapper.insertSelective(flowSwimlaneUser);
		
		return flowSwimlane.getId();
	}

	private Long createProcessinstance(Long processdefinition_id) {
		FlowProcessinstance flowProcessinstance = new FlowProcessinstance();
		flowProcessinstance.setStartTime(new Date());
		flowProcessinstance.setIsFiled(NOT_FILED);
		flowProcessinstance.setIsSuspended(NOT_SUSPENDED);
		flowProcessinstance.setProcessdefinitionId(processdefinition_id);
		flowProcessinstanceMapper.insertSelective(flowProcessinstance);
		
		return flowProcessinstance.getId();
	}

}
