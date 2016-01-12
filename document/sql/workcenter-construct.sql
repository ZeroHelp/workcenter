/*
Navicat MySQL Data Transfer

Source Server         : 10.100.142.11
Source Server Version : 50520
Source Host           : 10.100.142.11:3306
Source Database       : workcenter

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2016-01-12 11:01:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_flow_node
-- ----------------------------
DROP TABLE IF EXISTS `base_flow_node`;
CREATE TABLE `base_flow_node` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '流程节点标识',
  `processdefinition_id` bigint(20) DEFAULT NULL COMMENT '节点所在的流程模版id',
  `index_num` int(4) DEFAULT NULL COMMENT '节点顺序号',
  `name` varchar(50) DEFAULT NULL COMMENT '流程节点名称',
  `type` varchar(10) DEFAULT NULL COMMENT '节点类型 D:  org.jbpm.graph.node.Decisi on E:  org.jbpm.graph.node.EndStateF:  org.jbpm.graph.node.Fork  J:org.jbpm.graph.node.Join??K:?org.jbpm.graph.node.TaskNode??N:?org.jbpm.graph.def.Node??R:?org.jbpm.graph.node.StartState?',
  `enter_class_name` varchar(500) DEFAULT NULL COMMENT '进入执行类',
  `leave_class_name` varchar(500) DEFAULT NULL COMMENT '退出执行类',
  `task_id` bigint(20) DEFAULT NULL COMMENT '进入下一节点执行任务模版',
  `decision_delegation_id` bigint(20) DEFAULT NULL COMMENT '节点类型为Decision时使用，表明Decision对应的代理类',
  `decision_expression` varchar(500) DEFAULT NULL COMMENT '节点类型为Decision时使用，该属性表示Decision中使用的判断表达式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='流程节点表';

-- ----------------------------
-- Table structure for base_flow_processdefinition
-- ----------------------------
DROP TABLE IF EXISTS `base_flow_processdefinition`;
CREATE TABLE `base_flow_processdefinition` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '流程模版标识',
  `name` varchar(50) DEFAULT NULL COMMENT '流程模版名称',
  `startnode_id` bigint(20) DEFAULT NULL COMMENT '起始节点',
  `version` varchar(50) DEFAULT NULL COMMENT '流程模版版本',
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='流程模版表';

-- ----------------------------
-- Table structure for base_flow_processinstance
-- ----------------------------
DROP TABLE IF EXISTS `base_flow_processinstance`;
CREATE TABLE `base_flow_processinstance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `start_time` datetime DEFAULT NULL COMMENT '流程实例开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '流程实例结束时间',
  `is_suspended` int(2) DEFAULT NULL COMMENT '流程实例是否暂停',
  `is_filed` int(2) DEFAULT NULL,
  `processdefinition_id` bigint(20) DEFAULT NULL COMMENT '流程实例所属的流程模版',
  `root_token_id` bigint(20) DEFAULT NULL COMMENT '流程实例对应的当前TOKEN',
  `swimlane_id` bigint(20) DEFAULT NULL COMMENT '所有关系人 （可查看此流程人员）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8 COMMENT='流程实例表';

-- ----------------------------
-- Table structure for base_flow_swimlane
-- ----------------------------
DROP TABLE IF EXISTS `base_flow_swimlane`;
CREATE TABLE `base_flow_swimlane` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `ch_name` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=197 DEFAULT CHARSET=utf8 COMMENT='干系角色表';

-- ----------------------------
-- Table structure for base_flow_swimlane_user
-- ----------------------------
DROP TABLE IF EXISTS `base_flow_swimlane_user`;
CREATE TABLE `base_flow_swimlane_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `swimlane_id` bigint(20) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=262 DEFAULT CHARSET=utf8 COMMENT='流程干系人表';

-- ----------------------------
-- Table structure for base_flow_task
-- ----------------------------
DROP TABLE IF EXISTS `base_flow_task`;
CREATE TABLE `base_flow_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '流程任务标识',
  `name` varchar(50) DEFAULT NULL COMMENT '流程任务名称',
  `processdefinition_id` bigint(20) DEFAULT NULL COMMENT '流程任务所属流程定义',
  `description` varchar(100) DEFAULT NULL COMMENT '流程任务描述',
  `actor_type` int(2) DEFAULT NULL COMMENT 'actor类型\r\n            1.起草人\r\n            2.起草人直属领导\r\n            3.某角色所有的人 假如角色一个人则直接设置',
  `role_id` bigint(20) DEFAULT NULL COMMENT '干系表id',
  `group_id` bigint(20) DEFAULT NULL,
  `node_id` bigint(20) DEFAULT NULL COMMENT 'node id',
  `start_node_id` bigint(20) DEFAULT NULL COMMENT '起始节点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='流程任务表';

-- ----------------------------
-- Table structure for base_flow_taskinstance
-- ----------------------------
DROP TABLE IF EXISTS `base_flow_taskinstance`;
CREATE TABLE `base_flow_taskinstance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `swimlane_id` bigint(20) DEFAULT NULL COMMENT '干系人id ',
  `processinstance_id` bigint(20) DEFAULT NULL,
  `task_id` bigint(20) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '任务名称',
  `description` varchar(1000) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `duedate` datetime DEFAULT NULL COMMENT '到期时间',
  `priority` int(2) DEFAULT NULL COMMENT '优先级 ',
  `is_cancelled` int(2) DEFAULT NULL COMMENT '是否取消',
  `is_suspended` int(2) DEFAULT NULL COMMENT '是否挂起',
  `is_open` int(2) DEFAULT NULL COMMENT '是否打开',
  `is_blocking` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=264 DEFAULT CHARSET=utf8 COMMENT='任务实例';

-- ----------------------------
-- Table structure for base_flow_transition
-- ----------------------------
DROP TABLE IF EXISTS `base_flow_transition`;
CREATE TABLE `base_flow_transition` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '流程迁移标识',
  `name` varchar(100) DEFAULT NULL COMMENT '流程迁移名称',
  `type` varchar(10) DEFAULT NULL,
  `processdefinition_id` bigint(20) DEFAULT NULL COMMENT '迁移所在流程模版id',
  `from_node_id` bigint(20) DEFAULT NULL COMMENT '迁移来源',
  `to_node_id` bigint(20) DEFAULT NULL COMMENT '迁移目的',
  `condition_expression` varchar(100) DEFAULT NULL COMMENT '转移的条件表达式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='流程迁移表';

-- ----------------------------
-- Table structure for base_flow_variableaccess
-- ----------------------------
DROP TABLE IF EXISTS `base_flow_variableaccess`;
CREATE TABLE `base_flow_variableaccess` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `node_id` bigint(20) DEFAULT NULL COMMENT '流程变量，所属的流程节点',
  `variableinstance_id` bigint(20) DEFAULT NULL,
  `variablename` varchar(100) DEFAULT NULL COMMENT '流程变量名称',
  `access` int(2) DEFAULT NULL COMMENT '流程变量访问方式：1read ,2write,3readwrite',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 COMMENT='流程变量访问方式表 (待用)';

-- ----------------------------
-- Table structure for base_flow_variableinstance
-- ----------------------------
DROP TABLE IF EXISTS `base_flow_variableinstance`;
CREATE TABLE `base_flow_variableinstance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `processdefinition_id` bigint(20) DEFAULT NULL COMMENT '变量所在的流程实例',
  `var_type` varchar(500) DEFAULT NULL COMMENT '变量所属',
  `var_name` text COMMENT '变量名称',
  `var_value` varchar(1000) DEFAULT NULL COMMENT '变量值',
  `index_num` int(4) DEFAULT NULL COMMENT '变量序号',
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='流程变量实例表(暂时不用)';

-- ----------------------------
-- Table structure for base_group
-- ----------------------------
DROP TABLE IF EXISTS `base_group`;
CREATE TABLE `base_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL,
  `group_name` varchar(50) DEFAULT NULL,
  `group_ch_name` varchar(50) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_userid` bigint(20) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='组织架构';

-- ----------------------------
-- Table structure for base_group_user
-- ----------------------------
DROP TABLE IF EXISTS `base_group_user`;
CREATE TABLE `base_group_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='汇集参与者表';

-- ----------------------------
-- Table structure for base_resource
-- ----------------------------
DROP TABLE IF EXISTS `base_resource`;
CREATE TABLE `base_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父资源(0为模块资源)',
  `resource_name` varchar(500) DEFAULT NULL,
  `resource_url` varchar(500) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_userid` bigint(20) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for base_role
-- ----------------------------
DROP TABLE IF EXISTS `base_role`;
CREATE TABLE `base_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL,
  `role_ch_name` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_userid` bigint(20) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for base_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `base_role_resource`;
CREATE TABLE `base_role_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `resource_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for base_user
-- ----------------------------
DROP TABLE IF EXISTS `base_user`;
CREATE TABLE `base_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL,
  `real_name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=267 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for base_user_role
-- ----------------------------
DROP TABLE IF EXISTS `base_user_role`;
CREATE TABLE `base_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for kpi_enactment_cultural
-- ----------------------------
DROP TABLE IF EXISTS `kpi_enactment_cultural`;
CREATE TABLE `kpi_enactment_cultural` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `kpi_main_id` bigint(20) DEFAULT NULL,
  `index_num` int(4) DEFAULT NULL,
  `task_direction` varchar(500) DEFAULT NULL COMMENT '方向',
  `task_content` text COMMENT '内容',
  `task_weight` int(4) DEFAULT NULL COMMENT '权重',
  `task_score` decimal(8,2) DEFAULT NULL COMMENT '分值',
  `status` int(2) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8 COMMENT='kpi文化设定';

-- ----------------------------
-- Table structure for kpi_enactment_self
-- ----------------------------
DROP TABLE IF EXISTS `kpi_enactment_self`;
CREATE TABLE `kpi_enactment_self` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `kpi_main_id` bigint(20) DEFAULT NULL,
  `index_num` int(4) DEFAULT NULL,
  `self_direction` varchar(500) DEFAULT NULL COMMENT '方向',
  `self_goal` text COMMENT '个人设定目标',
  `self_weight` int(4) DEFAULT NULL COMMENT '权重',
  `self_evaluate` text COMMENT '自我评价',
  `self_score` decimal(8,2) DEFAULT NULL COMMENT '自评分',
  `leader_evaluation` text COMMENT '领导评价',
  `leader_score` decimal(8,2) DEFAULT NULL COMMENT '审评分',
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 COMMENT='kpi个人设定';

-- ----------------------------
-- Table structure for kpi_main
-- ----------------------------
DROP TABLE IF EXISTS `kpi_main`;
CREATE TABLE `kpi_main` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `processinstance_id` bigint(20) DEFAULT NULL,
  `root_token_id` bigint(20) DEFAULT NULL,
  `wait_assessment_person_id` bigint(20) DEFAULT NULL COMMENT 'user_id',
  `assessment_person_id` bigint(20) DEFAULT NULL COMMENT 'user_id',
  `year` varchar(50) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `assess_status` int(2) DEFAULT NULL,
  `grade` varchar(50) DEFAULT NULL,
  `total_score` decimal(8,2) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8 COMMENT='kpi审核主表';
