/*
Navicat MySQL Data Transfer

Source Server         : 10.100.142.11
Source Server Version : 50520
Source Host           : 10.100.142.11:3306
Source Database       : workcenter

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2016-01-12 11:00:39
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
-- Records of base_flow_node
-- ----------------------------
INSERT INTO `base_flow_node` VALUES ('1', '1', '1', '发起', 'S', null, null, '0', null, null);
INSERT INTO `base_flow_node` VALUES ('2', '1', '2', '起草', 'T', null, null, '1', null, null);
INSERT INTO `base_flow_node` VALUES ('3', '1', '3', '领导审批', 'T', null, null, '2', null, null);
INSERT INTO `base_flow_node` VALUES ('4', '1', '4', '任务自评', 'T', null, null, '3', null, null);
INSERT INTO `base_flow_node` VALUES ('5', '1', '5', '领导审评', 'T', null, null, '4', null, null);
INSERT INTO `base_flow_node` VALUES ('6', '1', '6', '结束', 'E', null, null, '0', null, null);

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
-- Records of base_flow_processdefinition
-- ----------------------------
INSERT INTO `base_flow_processdefinition` VALUES ('1', '绩效考核', '1', '1', '1');

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
-- Records of base_flow_processinstance
-- ----------------------------
INSERT INTO `base_flow_processinstance` VALUES ('30', '2016-01-11 19:01:02', null, '0', '0', '1', null, '85');
INSERT INTO `base_flow_processinstance` VALUES ('31', '2016-01-11 19:01:02', null, '0', '0', '1', null, '88');
INSERT INTO `base_flow_processinstance` VALUES ('32', '2016-01-11 19:01:03', null, '0', '0', '1', null, '91');
INSERT INTO `base_flow_processinstance` VALUES ('33', '2016-01-11 19:01:03', null, '0', '0', '1', null, '94');
INSERT INTO `base_flow_processinstance` VALUES ('34', '2016-01-11 19:01:03', null, '0', '0', '1', null, '97');
INSERT INTO `base_flow_processinstance` VALUES ('35', '2016-01-11 19:01:03', null, '0', '0', '1', null, '100');
INSERT INTO `base_flow_processinstance` VALUES ('36', '2016-01-11 19:01:03', null, '0', '0', '1', null, '103');
INSERT INTO `base_flow_processinstance` VALUES ('37', '2016-01-11 19:01:03', null, '0', '0', '1', null, '106');
INSERT INTO `base_flow_processinstance` VALUES ('38', '2016-01-11 19:01:03', null, '0', '0', '1', null, '109');
INSERT INTO `base_flow_processinstance` VALUES ('39', '2016-01-11 19:01:03', null, '0', '0', '1', null, '112');
INSERT INTO `base_flow_processinstance` VALUES ('40', '2016-01-11 19:01:03', null, '0', '0', '1', null, '115');
INSERT INTO `base_flow_processinstance` VALUES ('41', '2016-01-11 19:01:03', null, '0', '0', '1', null, '118');
INSERT INTO `base_flow_processinstance` VALUES ('42', '2016-01-11 19:01:03', null, '0', '0', '1', null, '121');
INSERT INTO `base_flow_processinstance` VALUES ('43', '2016-01-11 19:01:03', null, '0', '0', '1', null, '124');
INSERT INTO `base_flow_processinstance` VALUES ('44', '2016-01-11 19:01:03', null, '0', '0', '1', null, '127');
INSERT INTO `base_flow_processinstance` VALUES ('45', '2016-01-11 19:01:03', null, '0', '0', '1', null, '130');
INSERT INTO `base_flow_processinstance` VALUES ('46', '2016-01-11 19:01:04', null, '0', '0', '1', null, '133');
INSERT INTO `base_flow_processinstance` VALUES ('47', '2016-01-11 19:01:04', null, '0', '0', '1', null, '136');
INSERT INTO `base_flow_processinstance` VALUES ('48', '2016-01-11 19:01:04', null, '0', '0', '1', null, '139');
INSERT INTO `base_flow_processinstance` VALUES ('49', '2016-01-11 19:04:25', null, '0', '0', '1', null, '142');
INSERT INTO `base_flow_processinstance` VALUES ('50', '2016-01-11 19:04:25', null, '0', '0', '1', null, '145');
INSERT INTO `base_flow_processinstance` VALUES ('51', '2016-01-11 19:04:25', null, '0', '0', '1', null, '148');
INSERT INTO `base_flow_processinstance` VALUES ('52', '2016-01-11 19:04:25', null, '0', '0', '1', null, '151');
INSERT INTO `base_flow_processinstance` VALUES ('53', '2016-01-11 19:04:25', null, '0', '0', '1', null, '154');
INSERT INTO `base_flow_processinstance` VALUES ('54', '2016-01-11 19:04:25', null, '0', '0', '1', null, '157');
INSERT INTO `base_flow_processinstance` VALUES ('55', '2016-01-11 19:04:25', null, '0', '0', '1', null, '160');
INSERT INTO `base_flow_processinstance` VALUES ('56', '2016-01-11 19:04:25', null, '0', '0', '1', null, '163');
INSERT INTO `base_flow_processinstance` VALUES ('57', '2016-01-11 19:04:25', null, '0', '0', '1', null, '166');
INSERT INTO `base_flow_processinstance` VALUES ('58', '2016-01-11 19:04:25', null, '0', '0', '1', null, '169');
INSERT INTO `base_flow_processinstance` VALUES ('59', '2016-01-11 19:04:25', null, '0', '0', '1', null, '172');
INSERT INTO `base_flow_processinstance` VALUES ('60', '2016-01-11 19:04:25', null, '0', '0', '1', null, '175');
INSERT INTO `base_flow_processinstance` VALUES ('61', '2016-01-11 19:04:25', null, '0', '0', '1', null, '178');
INSERT INTO `base_flow_processinstance` VALUES ('62', '2016-01-11 19:04:25', null, '0', '0', '1', null, '181');
INSERT INTO `base_flow_processinstance` VALUES ('63', '2016-01-11 19:04:26', null, '0', '0', '1', null, '184');
INSERT INTO `base_flow_processinstance` VALUES ('64', '2016-01-11 19:04:26', null, '0', '0', '1', null, '187');
INSERT INTO `base_flow_processinstance` VALUES ('65', '2016-01-11 19:04:26', null, '0', '0', '1', null, '190');
INSERT INTO `base_flow_processinstance` VALUES ('66', '2016-01-11 19:04:26', null, '0', '0', '1', null, '193');
INSERT INTO `base_flow_processinstance` VALUES ('67', '2016-01-11 19:04:26', null, '0', '0', '1', null, '196');

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
-- Records of base_flow_swimlane
-- ----------------------------
INSERT INTO `base_flow_swimlane` VALUES ('83', 'node_30_zhongweili', 'node_30_李忠伟节点流程关联人', '2016-01-11 19:01:02', '2');
INSERT INTO `base_flow_swimlane` VALUES ('84', 'node_30_baojianliu', 'node_30_刘宝剑节点流程关联人', '2016-01-11 19:01:02', '2');
INSERT INTO `base_flow_swimlane` VALUES ('85', 'process_30', 'process_30流程关联人', '2016-01-11 19:01:02', '2');
INSERT INTO `base_flow_swimlane` VALUES ('86', 'node_31_congwu2', 'node_31_武聪节点流程关联人', '2016-01-11 19:01:02', '2');
INSERT INTO `base_flow_swimlane` VALUES ('87', 'node_31_baojianliu', 'node_31_刘宝剑节点流程关联人', '2016-01-11 19:01:02', '2');
INSERT INTO `base_flow_swimlane` VALUES ('88', 'process_31', 'process_31流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('89', 'node_32_v-qiaoqiaozhu', 'node_32_朱巧巧节点流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('90', 'node_32_baojianliu', 'node_32_刘宝剑节点流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('91', 'process_32', 'process_32流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('92', 'node_33_zhibinyang2', 'node_33_杨志斌节点流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('93', 'node_33_baojianliu', 'node_33_刘宝剑节点流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('94', 'process_33', 'process_33流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('95', 'node_34_shuailiu13', 'node_34_刘帅节点流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('96', 'node_34_baojianliu', 'node_34_刘宝剑节点流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('97', 'process_34', 'process_34流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('98', 'node_35_yibiaoxiang', 'node_35_项翼彪节点流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('99', 'node_35_baojianliu', 'node_35_刘宝剑节点流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('100', 'process_35', 'process_35流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('101', 'node_36_leimingluo', 'node_36_罗磊明节点流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('102', 'node_36_baojianliu', 'node_36_刘宝剑节点流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('103', 'process_36', 'process_36流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('104', 'node_37_chaoli48', 'node_37_李超节点流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('105', 'node_37_baojianliu', 'node_37_刘宝剑节点流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('106', 'process_37', 'process_37流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('107', 'node_38_fangwu3', 'node_38_吴芳节点流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('108', 'node_38_baojianliu', 'node_38_刘宝剑节点流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('109', 'process_38', 'process_38流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('110', 'node_39_zonghailiu', 'node_39_刘宗海节点流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('111', 'node_39_baojianliu', 'node_39_刘宝剑节点流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('112', 'process_39', 'process_39流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('113', 'node_40_chenliu10', 'node_40_刘辰节点流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('114', 'node_40_baojianliu', 'node_40_刘宝剑节点流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('115', 'process_40', 'process_40流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('116', 'node_41_dongli19', 'node_41_李冬节点流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('117', 'node_41_baojianliu', 'node_41_刘宝剑节点流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('118', 'process_41', 'process_41流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('119', 'node_42_zhihuami', 'node_42_米志华节点流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('120', 'node_42_baojianliu', 'node_42_刘宝剑节点流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('121', 'process_42', 'process_42流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('122', 'node_43_ronglongzhang', 'node_43_张荣龙节点流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('123', 'node_43_baojianliu', 'node_43_刘宝剑节点流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('124', 'process_43', 'process_43流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('125', 'node_44_jinshanbai', 'node_44_白金山节点流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('126', 'node_44_baojianliu', 'node_44_刘宝剑节点流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('127', 'process_44', 'process_44流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('128', 'node_45_qinjingfan', 'node_45_范秦京节点流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('129', 'node_45_baojianliu', 'node_45_刘宝剑节点流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('130', 'process_45', 'process_45流程关联人', '2016-01-11 19:01:03', '2');
INSERT INTO `base_flow_swimlane` VALUES ('131', 'node_46_yanggao43', 'node_46_高扬节点流程关联人', '2016-01-11 19:01:04', '2');
INSERT INTO `base_flow_swimlane` VALUES ('132', 'node_46_baojianliu', 'node_46_刘宝剑节点流程关联人', '2016-01-11 19:01:04', '2');
INSERT INTO `base_flow_swimlane` VALUES ('133', 'process_46', 'process_46流程关联人', '2016-01-11 19:01:04', '2');
INSERT INTO `base_flow_swimlane` VALUES ('134', 'node_47_v-mingma', 'node_47_马明节点流程关联人', '2016-01-11 19:01:04', '2');
INSERT INTO `base_flow_swimlane` VALUES ('135', 'node_47_baojianliu', 'node_47_刘宝剑节点流程关联人', '2016-01-11 19:01:04', '2');
INSERT INTO `base_flow_swimlane` VALUES ('136', 'process_47', 'process_47流程关联人', '2016-01-11 19:01:04', '2');
INSERT INTO `base_flow_swimlane` VALUES ('137', 'node_48_linwang37', 'node_48_王林节点流程关联人', '2016-01-11 19:01:04', '2');
INSERT INTO `base_flow_swimlane` VALUES ('138', 'node_48_baojianliu', 'node_48_刘宝剑节点流程关联人', '2016-01-11 19:01:04', '2');
INSERT INTO `base_flow_swimlane` VALUES ('139', 'process_48', 'process_48流程关联人', '2016-01-11 19:01:04', '2');
INSERT INTO `base_flow_swimlane` VALUES ('140', 'node_49_zhongweili', 'node_49_李忠伟节点流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('141', 'node_49_baojianliu', 'node_49_刘宝剑节点流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('142', 'process_49', 'process_49流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('143', 'node_50_congwu2', 'node_50_武聪节点流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('144', 'node_50_baojianliu', 'node_50_刘宝剑节点流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('145', 'process_50', 'process_50流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('146', 'node_51_v-qiaoqiaozhu', 'node_51_朱巧巧节点流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('147', 'node_51_baojianliu', 'node_51_刘宝剑节点流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('148', 'process_51', 'process_51流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('149', 'node_52_zhibinyang2', 'node_52_杨志斌节点流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('150', 'node_52_baojianliu', 'node_52_刘宝剑节点流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('151', 'process_52', 'process_52流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('152', 'node_53_shuailiu13', 'node_53_刘帅节点流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('153', 'node_53_baojianliu', 'node_53_刘宝剑节点流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('154', 'process_53', 'process_53流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('155', 'node_54_yibiaoxiang', 'node_54_项翼彪节点流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('156', 'node_54_baojianliu', 'node_54_刘宝剑节点流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('157', 'process_54', 'process_54流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('158', 'node_55_leimingluo', 'node_55_罗磊明节点流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('159', 'node_55_baojianliu', 'node_55_刘宝剑节点流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('160', 'process_55', 'process_55流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('161', 'node_56_chaoli48', 'node_56_李超节点流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('162', 'node_56_baojianliu', 'node_56_刘宝剑节点流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('163', 'process_56', 'process_56流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('164', 'node_57_fangwu3', 'node_57_吴芳节点流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('165', 'node_57_baojianliu', 'node_57_刘宝剑节点流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('166', 'process_57', 'process_57流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('167', 'node_58_zonghailiu', 'node_58_刘宗海节点流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('168', 'node_58_baojianliu', 'node_58_刘宝剑节点流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('169', 'process_58', 'process_58流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('170', 'node_59_chenliu10', 'node_59_刘辰节点流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('171', 'node_59_baojianliu', 'node_59_刘宝剑节点流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('172', 'process_59', 'process_59流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('173', 'node_60_dongli19', 'node_60_李冬节点流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('174', 'node_60_baojianliu', 'node_60_刘宝剑节点流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('175', 'process_60', 'process_60流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('176', 'node_61_zhihuami', 'node_61_米志华节点流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('177', 'node_61_baojianliu', 'node_61_刘宝剑节点流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('178', 'process_61', 'process_61流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('179', 'node_62_ronglongzhang', 'node_62_张荣龙节点流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('180', 'node_62_baojianliu', 'node_62_刘宝剑节点流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('181', 'process_62', 'process_62流程关联人', '2016-01-11 19:04:25', '2');
INSERT INTO `base_flow_swimlane` VALUES ('182', 'node_63_jinshanbai', 'node_63_白金山节点流程关联人', '2016-01-11 19:04:26', '2');
INSERT INTO `base_flow_swimlane` VALUES ('183', 'node_63_baojianliu', 'node_63_刘宝剑节点流程关联人', '2016-01-11 19:04:26', '2');
INSERT INTO `base_flow_swimlane` VALUES ('184', 'process_63', 'process_63流程关联人', '2016-01-11 19:04:26', '2');
INSERT INTO `base_flow_swimlane` VALUES ('185', 'node_64_qinjingfan', 'node_64_范秦京节点流程关联人', '2016-01-11 19:04:26', '2');
INSERT INTO `base_flow_swimlane` VALUES ('186', 'node_64_baojianliu', 'node_64_刘宝剑节点流程关联人', '2016-01-11 19:04:26', '2');
INSERT INTO `base_flow_swimlane` VALUES ('187', 'process_64', 'process_64流程关联人', '2016-01-11 19:04:26', '2');
INSERT INTO `base_flow_swimlane` VALUES ('188', 'node_65_yanggao43', 'node_65_高扬节点流程关联人', '2016-01-11 19:04:26', '2');
INSERT INTO `base_flow_swimlane` VALUES ('189', 'node_65_baojianliu', 'node_65_刘宝剑节点流程关联人', '2016-01-11 19:04:26', '2');
INSERT INTO `base_flow_swimlane` VALUES ('190', 'process_65', 'process_65流程关联人', '2016-01-11 19:04:26', '2');
INSERT INTO `base_flow_swimlane` VALUES ('191', 'node_66_v-mingma', 'node_66_马明节点流程关联人', '2016-01-11 19:04:26', '2');
INSERT INTO `base_flow_swimlane` VALUES ('192', 'node_66_baojianliu', 'node_66_刘宝剑节点流程关联人', '2016-01-11 19:04:26', '2');
INSERT INTO `base_flow_swimlane` VALUES ('193', 'process_66', 'process_66流程关联人', '2016-01-11 19:04:26', '2');
INSERT INTO `base_flow_swimlane` VALUES ('194', 'node_67_linwang37', 'node_67_王林节点流程关联人', '2016-01-11 19:04:26', '2');
INSERT INTO `base_flow_swimlane` VALUES ('195', 'node_67_baojianliu', 'node_67_刘宝剑节点流程关联人', '2016-01-11 19:04:26', '2');
INSERT INTO `base_flow_swimlane` VALUES ('196', 'process_67', 'process_67流程关联人', '2016-01-11 19:04:26', '2');

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
-- Records of base_flow_swimlane_user
-- ----------------------------
INSERT INTO `base_flow_swimlane_user` VALUES ('110', '246', '83', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('111', '2', '84', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('112', '246', '85', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('113', '2', '85', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('114', '248', '86', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('115', '2', '87', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('116', '248', '88', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('117', '2', '88', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('118', '250', '89', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('119', '2', '90', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('120', '250', '91', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('121', '2', '91', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('122', '251', '92', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('123', '2', '93', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('124', '251', '94', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('125', '2', '94', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('126', '252', '95', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('127', '2', '96', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('128', '252', '97', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('129', '2', '97', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('130', '253', '98', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('131', '2', '99', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('132', '253', '100', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('133', '2', '100', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('134', '254', '101', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('135', '2', '102', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('136', '254', '103', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('137', '2', '103', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('138', '255', '104', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('139', '2', '105', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('140', '255', '106', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('141', '2', '106', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('142', '256', '107', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('143', '2', '108', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('144', '256', '109', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('145', '2', '109', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('146', '257', '110', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('147', '2', '111', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('148', '257', '112', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('149', '2', '112', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('150', '258', '113', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('151', '2', '114', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('152', '258', '115', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('153', '2', '115', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('154', '259', '116', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('155', '2', '117', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('156', '259', '118', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('157', '2', '118', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('158', '260', '119', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('159', '2', '120', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('160', '260', '121', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('161', '2', '121', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('162', '261', '122', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('163', '2', '123', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('164', '261', '124', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('165', '2', '124', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('166', '262', '125', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('167', '2', '126', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('168', '262', '127', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('169', '2', '127', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('170', '263', '128', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('171', '2', '129', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('172', '263', '130', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('173', '2', '130', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('174', '264', '131', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('175', '2', '132', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('176', '264', '133', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('177', '2', '133', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('178', '265', '134', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('179', '2', '135', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('180', '265', '136', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('181', '2', '136', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('182', '1', '137', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('183', '2', '138', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('184', '1', '139', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('185', '2', '139', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('186', '246', '140', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('187', '2', '141', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('188', '246', '142', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('189', '2', '142', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('190', '248', '143', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('191', '2', '144', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('192', '248', '145', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('193', '2', '145', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('194', '250', '146', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('195', '2', '147', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('196', '250', '148', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('197', '2', '148', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('198', '251', '149', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('199', '2', '150', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('200', '251', '151', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('201', '2', '151', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('202', '252', '152', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('203', '2', '153', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('204', '252', '154', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('205', '2', '154', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('206', '253', '155', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('207', '2', '156', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('208', '253', '157', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('209', '2', '157', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('210', '254', '158', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('211', '2', '159', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('212', '254', '160', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('213', '2', '160', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('214', '255', '161', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('215', '2', '162', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('216', '255', '163', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('217', '2', '163', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('218', '256', '164', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('219', '2', '165', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('220', '256', '166', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('221', '2', '166', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('222', '257', '167', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('223', '2', '168', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('224', '257', '169', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('225', '2', '169', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('226', '258', '170', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('227', '2', '171', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('228', '258', '172', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('229', '2', '172', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('230', '259', '173', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('231', '2', '174', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('232', '259', '175', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('233', '2', '175', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('234', '260', '176', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('235', '2', '177', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('236', '260', '178', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('237', '2', '178', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('238', '261', '179', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('239', '2', '180', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('240', '261', '181', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('241', '2', '181', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('242', '262', '182', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('243', '2', '183', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('244', '262', '184', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('245', '2', '184', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('246', '263', '185', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('247', '2', '186', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('248', '263', '187', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('249', '2', '187', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('250', '264', '188', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('251', '2', '189', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('252', '264', '190', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('253', '2', '190', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('254', '265', '191', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('255', '2', '192', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('256', '265', '193', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('257', '2', '193', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('258', '1', '194', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('259', '2', '195', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('260', '1', '196', '1');
INSERT INTO `base_flow_swimlane_user` VALUES ('261', '2', '196', '1');

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
-- Records of base_flow_task
-- ----------------------------
INSERT INTO `base_flow_task` VALUES ('1', '起草', '1', null, '1', null, null, '2', '1');
INSERT INTO `base_flow_task` VALUES ('2', '审批', '1', null, '2', null, null, '3', '1');
INSERT INTO `base_flow_task` VALUES ('3', '自评', '1', null, '1', null, null, '4', '1');
INSERT INTO `base_flow_task` VALUES ('4', '审评', '1', null, '2', null, null, '5', '1');

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
-- Records of base_flow_taskinstance
-- ----------------------------
INSERT INTO `base_flow_taskinstance` VALUES ('112', '83', '30', '1', '起草', null, '2016-01-11 19:01:02', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('113', '84', '30', '2', '审批', null, '2016-01-11 19:01:02', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('114', '83', '30', '3', '自评', null, '2016-01-11 19:01:02', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('115', '84', '30', '4', '审评', null, '2016-01-11 19:01:02', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('116', '86', '31', '1', '起草', null, '2016-01-11 19:01:02', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('117', '87', '31', '2', '审批', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('118', '86', '31', '3', '自评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('119', '87', '31', '4', '审评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('120', '89', '32', '1', '起草', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('121', '90', '32', '2', '审批', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('122', '89', '32', '3', '自评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('123', '90', '32', '4', '审评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('124', '92', '33', '1', '起草', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('125', '93', '33', '2', '审批', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('126', '92', '33', '3', '自评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('127', '93', '33', '4', '审评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('128', '95', '34', '1', '起草', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('129', '96', '34', '2', '审批', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('130', '95', '34', '3', '自评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('131', '96', '34', '4', '审评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('132', '98', '35', '1', '起草', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('133', '99', '35', '2', '审批', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('134', '98', '35', '3', '自评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('135', '99', '35', '4', '审评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('136', '101', '36', '1', '起草', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('137', '102', '36', '2', '审批', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('138', '101', '36', '3', '自评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('139', '102', '36', '4', '审评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('140', '104', '37', '1', '起草', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('141', '105', '37', '2', '审批', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('142', '104', '37', '3', '自评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('143', '105', '37', '4', '审评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('144', '107', '38', '1', '起草', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('145', '108', '38', '2', '审批', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('146', '107', '38', '3', '自评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('147', '108', '38', '4', '审评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('148', '110', '39', '1', '起草', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('149', '111', '39', '2', '审批', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('150', '110', '39', '3', '自评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('151', '111', '39', '4', '审评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('152', '113', '40', '1', '起草', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('153', '114', '40', '2', '审批', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('154', '113', '40', '3', '自评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('155', '114', '40', '4', '审评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('156', '116', '41', '1', '起草', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('157', '117', '41', '2', '审批', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('158', '116', '41', '3', '自评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('159', '117', '41', '4', '审评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('160', '119', '42', '1', '起草', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('161', '120', '42', '2', '审批', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('162', '119', '42', '3', '自评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('163', '120', '42', '4', '审评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('164', '122', '43', '1', '起草', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('165', '123', '43', '2', '审批', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('166', '122', '43', '3', '自评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('167', '123', '43', '4', '审评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('168', '125', '44', '1', '起草', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('169', '126', '44', '2', '审批', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('170', '125', '44', '3', '自评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('171', '126', '44', '4', '审评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('172', '128', '45', '1', '起草', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('173', '129', '45', '2', '审批', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('174', '128', '45', '3', '自评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('175', '129', '45', '4', '审评', null, '2016-01-11 19:01:03', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('176', '131', '46', '1', '起草', null, '2016-01-11 19:01:04', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('177', '132', '46', '2', '审批', null, '2016-01-11 19:01:04', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('178', '131', '46', '3', '自评', null, '2016-01-11 19:01:04', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('179', '132', '46', '4', '审评', null, '2016-01-11 19:01:04', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('180', '134', '47', '1', '起草', null, '2016-01-11 19:01:04', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('181', '135', '47', '2', '审批', null, '2016-01-11 19:01:04', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('182', '134', '47', '3', '自评', null, '2016-01-11 19:01:04', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('183', '135', '47', '4', '审评', null, '2016-01-11 19:01:04', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('184', '137', '48', '1', '起草', null, '2016-01-11 19:01:04', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('185', '138', '48', '2', '审批', null, '2016-01-11 19:01:04', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('186', '137', '48', '3', '自评', null, '2016-01-11 19:01:04', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('187', '138', '48', '4', '审评', null, '2016-01-11 19:01:04', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('188', '140', '49', '1', '起草', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('189', '141', '49', '2', '审批', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('190', '140', '49', '3', '自评', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('191', '141', '49', '4', '审评', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('192', '143', '50', '1', '起草', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('193', '144', '50', '2', '审批', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('194', '143', '50', '3', '自评', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('195', '144', '50', '4', '审评', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('196', '146', '51', '1', '起草', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('197', '147', '51', '2', '审批', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('198', '146', '51', '3', '自评', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('199', '147', '51', '4', '审评', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('200', '149', '52', '1', '起草', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('201', '150', '52', '2', '审批', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('202', '149', '52', '3', '自评', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('203', '150', '52', '4', '审评', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('204', '152', '53', '1', '起草', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('205', '153', '53', '2', '审批', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('206', '152', '53', '3', '自评', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('207', '153', '53', '4', '审评', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('208', '155', '54', '1', '起草', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('209', '156', '54', '2', '审批', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('210', '155', '54', '3', '自评', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('211', '156', '54', '4', '审评', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('212', '158', '55', '1', '起草', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('213', '159', '55', '2', '审批', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('214', '158', '55', '3', '自评', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('215', '159', '55', '4', '审评', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('216', '161', '56', '1', '起草', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('217', '162', '56', '2', '审批', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('218', '161', '56', '3', '自评', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('219', '162', '56', '4', '审评', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('220', '164', '57', '1', '起草', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('221', '165', '57', '2', '审批', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('222', '164', '57', '3', '自评', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('223', '165', '57', '4', '审评', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('224', '167', '58', '1', '起草', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('225', '168', '58', '2', '审批', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('226', '167', '58', '3', '自评', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('227', '168', '58', '4', '审评', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('228', '170', '59', '1', '起草', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('229', '171', '59', '2', '审批', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('230', '170', '59', '3', '自评', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('231', '171', '59', '4', '审评', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('232', '173', '60', '1', '起草', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('233', '174', '60', '2', '审批', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('234', '173', '60', '3', '自评', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('235', '174', '60', '4', '审评', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('236', '176', '61', '1', '起草', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('237', '177', '61', '2', '审批', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('238', '176', '61', '3', '自评', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('239', '177', '61', '4', '审评', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('240', '179', '62', '1', '起草', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('241', '180', '62', '2', '审批', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('242', '179', '62', '3', '自评', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('243', '180', '62', '4', '审评', null, '2016-01-11 19:04:25', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('244', '182', '63', '1', '起草', null, '2016-01-11 19:04:26', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('245', '183', '63', '2', '审批', null, '2016-01-11 19:04:26', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('246', '182', '63', '3', '自评', null, '2016-01-11 19:04:26', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('247', '183', '63', '4', '审评', null, '2016-01-11 19:04:26', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('248', '185', '64', '1', '起草', null, '2016-01-11 19:04:26', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('249', '186', '64', '2', '审批', null, '2016-01-11 19:04:26', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('250', '185', '64', '3', '自评', null, '2016-01-11 19:04:26', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('251', '186', '64', '4', '审评', null, '2016-01-11 19:04:26', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('252', '188', '65', '1', '起草', null, '2016-01-11 19:04:26', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('253', '189', '65', '2', '审批', null, '2016-01-11 19:04:26', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('254', '188', '65', '3', '自评', null, '2016-01-11 19:04:26', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('255', '189', '65', '4', '审评', null, '2016-01-11 19:04:26', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('256', '191', '66', '1', '起草', null, '2016-01-11 19:04:26', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('257', '192', '66', '2', '审批', null, '2016-01-11 19:04:26', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('258', '191', '66', '3', '自评', null, '2016-01-11 19:04:26', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('259', '192', '66', '4', '审评', null, '2016-01-11 19:04:26', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('260', '194', '67', '1', '起草', null, '2016-01-11 19:04:26', null, null, null, null, '0', '0', '1', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('261', '195', '67', '2', '审批', null, '2016-01-11 19:04:26', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('262', '194', '67', '3', '自评', null, '2016-01-11 19:04:26', null, null, null, null, '0', '0', '0', '0');
INSERT INTO `base_flow_taskinstance` VALUES ('263', '195', '67', '4', '审评', null, '2016-01-11 19:04:26', null, null, null, null, '0', '0', '0', '0');

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
-- Records of base_flow_transition
-- ----------------------------
INSERT INTO `base_flow_transition` VALUES ('1', '发起绩效', 'N', '1', '1', '2', null);
INSERT INTO `base_flow_transition` VALUES ('2', '提交审批', 'N', '1', '2', '3', null);
INSERT INTO `base_flow_transition` VALUES ('3', '通过审批', 'N', '1', '3', '4', null);
INSERT INTO `base_flow_transition` VALUES ('4', '提交审评', 'N', '1', '4', '5', null);
INSERT INTO `base_flow_transition` VALUES ('5', '审评完成', 'N', '1', '5', '6', null);

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
-- Records of base_flow_variableaccess
-- ----------------------------
INSERT INTO `base_flow_variableaccess` VALUES ('1', '2', '1', 'selfDirection', '3');
INSERT INTO `base_flow_variableaccess` VALUES ('2', '2', '2', 'selfGoal', '3');
INSERT INTO `base_flow_variableaccess` VALUES ('3', '2', '3', 'selfWeight', '3');
INSERT INTO `base_flow_variableaccess` VALUES ('4', '2', '4', 'selfEvaluate', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('5', '2', '5', 'selfScore', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('6', '2', '6', 'leaderEvaluation', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('7', '2', '7', 'leaderScore', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('8', '2', '8', 'grade', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('9', '2', '9', 'totalScore', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('10', '2', '10', 'taskDirection', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('11', '2', '11', 'taskContent', '3');
INSERT INTO `base_flow_variableaccess` VALUES ('12', '2', '12', 'taskWeight', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('13', '2', '13', 'taskScore', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('21', '3', '1', 'selfDirection', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('22', '3', '2', 'selfGoal', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('23', '3', '3', 'selfWeight', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('24', '3', '4', 'selfEvaluate', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('25', '3', '5', 'selfScore', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('26', '3', '6', 'leaderEvaluation', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('27', '3', '7', 'leaderScore', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('28', '3', '8', 'grade', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('29', '3', '9', 'totalScore', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('30', '3', '10', 'taskDirection', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('31', '3', '11', 'taskContent', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('32', '3', '12', 'taskWeight', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('33', '3', '13', 'taskScore', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('41', '4', '1', 'selfDirection', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('42', '4', '2', 'selfGoal', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('43', '4', '3', 'selfWeight', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('44', '4', '4', 'selfEvaluate', '3');
INSERT INTO `base_flow_variableaccess` VALUES ('45', '4', '5', 'selfScore', '3');
INSERT INTO `base_flow_variableaccess` VALUES ('46', '4', '6', 'leaderEvaluation', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('47', '4', '7', 'leaderScore', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('48', '4', '8', 'grade', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('49', '4', '9', 'totalScore', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('50', '4', '10', 'taskDirection', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('51', '4', '11', 'taskContent', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('52', '4', '12', 'taskWeight', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('53', '4', '13', 'taskScore', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('61', '5', '1', 'selfDirection', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('62', '5', '2', 'selfGoal', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('63', '5', '3', 'selfWeight', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('64', '5', '4', 'selfEvaluate', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('65', '5', '5', 'selfScore', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('66', '5', '6', 'leaderEvaluation', '3');
INSERT INTO `base_flow_variableaccess` VALUES ('67', '5', '7', 'leaderScore', '3');
INSERT INTO `base_flow_variableaccess` VALUES ('68', '5', '8', 'grade', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('69', '5', '9', 'totalScore', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('70', '5', '10', 'taskDirection', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('71', '5', '11', 'taskContent', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('72', '5', '12', 'taskWeight', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('73', '5', '13', 'taskScore', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('81', '6', '1', 'selfDirection', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('82', '6', '2', 'selfGoal', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('83', '6', '3', 'selfWeight', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('84', '6', '4', 'selfEvaluate', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('85', '6', '5', 'selfScore', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('86', '6', '6', 'leaderEvaluation', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('87', '6', '7', 'leaderScore', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('88', '6', '8', 'grade', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('89', '6', '9', 'totalScore', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('90', '6', '10', 'taskDirection', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('91', '6', '11', 'taskContent', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('92', '6', '12', 'taskWeight', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('93', '6', '13', 'taskScore', '1');
INSERT INTO `base_flow_variableaccess` VALUES ('94', null, null, null, null);
INSERT INTO `base_flow_variableaccess` VALUES ('95', null, null, null, null);
INSERT INTO `base_flow_variableaccess` VALUES ('96', null, null, null, null);

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
-- Records of base_flow_variableinstance
-- ----------------------------
INSERT INTO `base_flow_variableinstance` VALUES ('1', '1', 'S', 'selfDirection', null, '1', '1');
INSERT INTO `base_flow_variableinstance` VALUES ('2', '1', 'S', 'selfGoal', null, '2', '1');
INSERT INTO `base_flow_variableinstance` VALUES ('3', '1', 'I', 'selfWeight', null, '3', '1');
INSERT INTO `base_flow_variableinstance` VALUES ('4', '1', 'S', 'selfEvaluate', null, '4', '1');
INSERT INTO `base_flow_variableinstance` VALUES ('5', '1', 'B', 'selfScore', null, '5', '1');
INSERT INTO `base_flow_variableinstance` VALUES ('6', '1', 'S', 'leaderEvaluation', null, '6', '1');
INSERT INTO `base_flow_variableinstance` VALUES ('7', '1', 'B', 'leaderScore', null, '7', '1');
INSERT INTO `base_flow_variableinstance` VALUES ('8', '1', 'S', 'grade', null, '8', '1');
INSERT INTO `base_flow_variableinstance` VALUES ('9', '1', 'B', 'totalScore', null, '9', '1');
INSERT INTO `base_flow_variableinstance` VALUES ('10', '1', 'S', 'taskDirection', null, '10', '1');
INSERT INTO `base_flow_variableinstance` VALUES ('11', '1', 'S', 'taskContent', null, '11', '1');
INSERT INTO `base_flow_variableinstance` VALUES ('12', '1', 'I', 'taskWeight', null, '12', '1');
INSERT INTO `base_flow_variableinstance` VALUES ('13', '1', 'B', 'taskScore', null, '13', '1');
INSERT INTO `base_flow_variableinstance` VALUES ('14', '1', 'L', 'selfId', null, '14', '1');
INSERT INTO `base_flow_variableinstance` VALUES ('15', '1', 'L', 'culturalId', null, '15', '1');

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
-- Records of base_group
-- ----------------------------
INSERT INTO `base_group` VALUES ('1', '0', 'develop_manager', '技术部经理', '1', '2015-12-21 10:58:19', '1', '1');
INSERT INTO `base_group` VALUES ('2', '1', 'develop_depart', '技术研发部', '1', '2015-12-21 10:59:46', '1', '1');

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
-- Records of base_group_user
-- ----------------------------
INSERT INTO `base_group_user` VALUES ('2', '2', '1', '1');
INSERT INTO `base_group_user` VALUES ('8', '246', '2', '1');
INSERT INTO `base_group_user` VALUES ('9', '247', '2', '1');
INSERT INTO `base_group_user` VALUES ('10', '248', '2', '1');
INSERT INTO `base_group_user` VALUES ('11', '249', '2', '1');
INSERT INTO `base_group_user` VALUES ('12', '250', '2', '1');
INSERT INTO `base_group_user` VALUES ('13', '251', '2', '1');
INSERT INTO `base_group_user` VALUES ('14', '252', '2', '1');
INSERT INTO `base_group_user` VALUES ('15', '253', '2', '1');
INSERT INTO `base_group_user` VALUES ('16', '254', '2', '1');
INSERT INTO `base_group_user` VALUES ('17', '255', '2', '1');
INSERT INTO `base_group_user` VALUES ('18', '256', '2', '1');
INSERT INTO `base_group_user` VALUES ('19', '257', '2', '1');
INSERT INTO `base_group_user` VALUES ('20', '258', '2', '1');
INSERT INTO `base_group_user` VALUES ('21', '259', '2', '1');
INSERT INTO `base_group_user` VALUES ('22', '260', '2', '1');
INSERT INTO `base_group_user` VALUES ('23', '261', '2', '1');
INSERT INTO `base_group_user` VALUES ('24', '262', '2', '1');
INSERT INTO `base_group_user` VALUES ('25', '263', '2', '1');
INSERT INTO `base_group_user` VALUES ('26', '264', '2', '1');
INSERT INTO `base_group_user` VALUES ('27', '265', '2', '1');
INSERT INTO `base_group_user` VALUES ('28', '266', '2', '1');
INSERT INTO `base_group_user` VALUES ('32', '1', '2', '1');

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
-- Records of base_resource
-- ----------------------------
INSERT INTO `base_resource` VALUES ('1', '-1', 'workcenter主页', 'workcenter/home', '2015-12-18 13:52:28', '1', '1');
INSERT INTO `base_resource` VALUES ('2', '1', 'workcenter登录页', 'workcenter/index', '2015-12-18 13:53:47', '1', '1');
INSERT INTO `base_resource` VALUES ('3', '1', 'workcenter登录请求', 'workcenter/sid', '2015-12-18 13:55:29', '1', '1');
INSERT INTO `base_resource` VALUES ('4', '1', 'workcenter退出登录', 'workcenter/logout', '2015-12-18 13:56:03', '1', '1');
INSERT INTO `base_resource` VALUES ('5', '0', 'KPI系统', 'kpi/home', '2015-12-18 15:16:44', '1', '1');
INSERT INTO `base_resource` VALUES ('6', '0', 'admin后台', 'admin/home', '2015-12-25 13:11:16', '1', '1');
INSERT INTO `base_resource` VALUES ('7', '6', 'kpi创建新季度绩效', 'admin/kpi/init', '2015-12-25 13:15:36', '1', '1');
INSERT INTO `base_resource` VALUES ('8', '1', 'workcenter更改密码', 'workcenter/password', '2015-12-31 13:51:18', '1', '1');
INSERT INTO `base_resource` VALUES ('20', '5', 'KPI审批页', 'kpi/assessment', '2015-12-27 17:27:23', '1', '1');
INSERT INTO `base_resource` VALUES ('41', '6', '角色资源更新', 'admin/role/updateResource', '2015-12-27 17:27:23', '1', '1');
INSERT INTO `base_resource` VALUES ('42', '6', '角色资源列表', 'admin/role/resourcelist', '2015-12-27 17:27:23', '1', '1');
INSERT INTO `base_resource` VALUES ('43', '6', '用户用户组更新', 'admin/user/updateGroup', '2015-12-27 17:27:23', '1', '1');
INSERT INTO `base_resource` VALUES ('44', '6', '用户用户组列表', 'admin/user/grouplist', '2015-12-27 17:27:23', '1', '1');
INSERT INTO `base_resource` VALUES ('45', '6', '用户角色更新', 'admin/user/updateRole', '2015-12-27 17:27:23', '1', '1');
INSERT INTO `base_resource` VALUES ('46', '6', '用户角色列表', 'admin/user/rolelist', '2015-12-31 13:51:18', '1', '1');
INSERT INTO `base_resource` VALUES ('47', '6', '用户组管理页', 'admin/group/list', '2015-12-31 13:51:18', '1', '1');
INSERT INTO `base_resource` VALUES ('48', '6', '资源管理页', 'admin/resource/list', '2015-12-31 13:51:18', '1', '1');
INSERT INTO `base_resource` VALUES ('49', '6', '角色管理页', 'admin/role/list', '2015-12-31 13:51:18', '1', '1');
INSERT INTO `base_resource` VALUES ('50', '6', '用户管理页', 'admin/user/list', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('51', '6', 'KPI管理页', 'admin/kpi/list', '2015-12-31 13:58:13', '1', '1');
INSERT INTO `base_resource` VALUES ('52', '6', '用户添加', 'admin/user/add', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('53', '6', '用户获取', 'admin/user/get', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('54', '6', '用户修改', 'admin/user/edit', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('55', '6', '用户删除', 'admin/user/delete', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('56', '6', '用户禁用', 'admin/user/forbidden', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('57', '6', '角色添加', 'admin/role/add', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('58', '6', '角色获取', 'admin/role/get', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('59', '6', '角色修改', 'admin/role/edit', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('60', '6', '角色删除', 'admin/role/delete', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('61', '6', '角色禁用', 'admin/role/forbidden', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('62', '6', '流程变量管理页', 'admin/flow/Variable/instance/list', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('63', '6', '流程变量添加', 'admin/flow/Variable/instance/add', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('64', '6', '流程变量获取', 'admin/flow/Variable/instance/get', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('65', '6', '流程变量修改', 'admin/flow/Variable/instance/edit', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('66', '6', '流程变量删除', 'admin/flow/Variable/instance/delete', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('67', '6', '流程变量禁用', 'admin/flow/Variable/instance/forbidden', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('68', '6', '资源添加', 'admin/resource/add', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('69', '6', '资源获取', 'admin/resource/get', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('70', '6', '资源修改', 'admin/resource/edit', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('71', '6', '资源删除', 'admin/resource/delete', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('72', '6', '资源禁用', 'admin/resource/forbidden', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('73', '6', '用户组添加', 'admin/group/add', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('74', '6', '用户组获取', 'admin/group/get', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('75', '6', '用户组修改', 'admin/group/edit', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('76', '6', '用户组删除', 'admin/group/delete', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('77', '6', '用户组禁用', 'admin/group/forbidden', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('78', '6', '用户组树', 'admin/group/ztree', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('100', '-2', '用户权限模块', '#', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('101', '100', '用户管理', 'admin/user/list', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('102', '100', '角色管理', 'admin/role/list', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('103', '100', '资源管理', 'admin/resource/list', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('104', '100', '用户组管理', 'admin/group/list', '2015-12-25 13:17:19', '1', '1');
INSERT INTO `base_resource` VALUES ('105', '100', 'KPI管理', 'admin/kpi/list', '2015-12-31 13:51:18', '1', '1');
INSERT INTO `base_resource` VALUES ('106', '100', '流程变量管理', 'admin/flow/Variable/instance/list', '2015-12-31 13:51:18', '1', '0');
INSERT INTO `base_resource` VALUES ('112', null, '资源树', 'admin/resources/ztree', '2016-01-08 17:09:29', '1', '1');
INSERT INTO `base_resource` VALUES ('113', null, '角色树', 'admin/role/ztree', '2016-01-08 17:09:56', '1', '1');
INSERT INTO `base_resource` VALUES ('114', null, '用户树', 'admin/user/ztree', '2016-01-08 17:10:13', '1', '1');

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
-- Records of base_role
-- ----------------------------
INSERT INTO `base_role` VALUES ('1', 'ANONYMOUS_ROLE', 'ANONYMOUS_ROLE匿名访问权限', '2015-12-18 13:51:10', '1', '1');
INSERT INTO `base_role` VALUES ('2', 'WORKCENTER_ROLE', 'WORKCENTER访问权限', '2015-12-18 14:54:09', '1', '1');
INSERT INTO `base_role` VALUES ('3', 'KPI_ROLE', 'KPI_ROLE访问权限', '2015-12-18 15:17:29', '1', '1');
INSERT INTO `base_role` VALUES ('4', 'ADMIN_KPI_ROLE', 'ADMIN_KPI_ROLE访问权限', '2015-12-25 13:28:28', '1', '1');
INSERT INTO `base_role` VALUES ('5', 'ADMIN_ROLE', 'ADMIN访问权限', '2015-12-26 14:57:24', '1', '1');
INSERT INTO `base_role` VALUES ('6', 'ADMIN_USER_ROLE', 'ADMIN_USER_ROLE访问权限', '2015-12-26 14:57:24', '1', '1');
INSERT INTO `base_role` VALUES ('7', 'ADMIN_ROLE_ROLE', 'ADMIN_ROLE_ROLE访问权限', '2015-12-26 14:57:24', '1', '1');
INSERT INTO `base_role` VALUES ('8', 'ADMIN_RESOURCE_ROLE', 'ADMIN_RESOURCE_ROLE访问权限', '2015-12-26 14:57:24', '1', '1');
INSERT INTO `base_role` VALUES ('9', 'ADMIN_GROUP_ROLE', 'ADMIN_GROUP_ROLE访问权限', '2015-12-26 14:57:24', '1', '1');

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
-- Records of base_role_resource
-- ----------------------------
INSERT INTO `base_role_resource` VALUES ('1', '2', '1', '2015-12-18 14:03:32', '1', '1');
INSERT INTO `base_role_resource` VALUES ('5', '3', '5', '2015-12-18 15:17:59', '1', '1');
INSERT INTO `base_role_resource` VALUES ('6', '4', '7', '2015-12-25 13:29:29', '1', '1');
INSERT INTO `base_role_resource` VALUES ('7', '4', '8', '2015-12-25 13:29:32', '1', '1');
INSERT INTO `base_role_resource` VALUES ('8', '4', '9', '2015-12-25 13:29:34', '1', '1');
INSERT INTO `base_role_resource` VALUES ('9', '5', '6', '2015-12-25 13:29:37', '1', '1');
INSERT INTO `base_role_resource` VALUES ('10', '3', '20', '2015-12-27 17:29:10', '1', '1');
INSERT INTO `base_role_resource` VALUES ('11', '4', '50', '2015-12-31 14:10:32', '1', '1');
INSERT INTO `base_role_resource` VALUES ('12', '4', '51', '2015-12-31 14:10:35', '1', '1');
INSERT INTO `base_role_resource` VALUES ('30', '9', '47', '2015-12-31 14:10:32', '1', '1');
INSERT INTO `base_role_resource` VALUES ('31', '9', '73', '2015-12-31 14:10:32', '1', '1');
INSERT INTO `base_role_resource` VALUES ('32', '9', '74', '2015-12-31 14:10:32', '1', '1');
INSERT INTO `base_role_resource` VALUES ('33', '9', '75', '2015-12-31 14:10:32', '1', '1');
INSERT INTO `base_role_resource` VALUES ('34', '9', '76', '2015-12-31 14:10:32', '1', '1');
INSERT INTO `base_role_resource` VALUES ('35', '9', '77', '2015-12-31 14:10:32', '1', '1');
INSERT INTO `base_role_resource` VALUES ('36', '9', '78', '2015-12-31 14:10:32', '1', '1');
INSERT INTO `base_role_resource` VALUES ('37', '2', '8', '2016-01-07 11:03:01', '1', '1');
INSERT INTO `base_role_resource` VALUES ('48', '1', '2', '2016-01-08 15:05:12', '1', '1');
INSERT INTO `base_role_resource` VALUES ('49', '1', '3', '2016-01-08 15:05:12', '1', '1');
INSERT INTO `base_role_resource` VALUES ('50', '1', '4', '2016-01-08 15:05:12', '1', '1');
INSERT INTO `base_role_resource` VALUES ('51', '6', '41', '2016-01-08 17:10:37', '1', '1');
INSERT INTO `base_role_resource` VALUES ('52', '6', '42', '2016-01-08 17:10:37', '1', '1');
INSERT INTO `base_role_resource` VALUES ('53', '6', '43', '2016-01-08 17:10:37', '1', '1');
INSERT INTO `base_role_resource` VALUES ('54', '6', '44', '2016-01-08 17:10:37', '1', '1');
INSERT INTO `base_role_resource` VALUES ('55', '6', '45', '2016-01-08 17:10:37', '1', '1');
INSERT INTO `base_role_resource` VALUES ('56', '6', '46', '2016-01-08 17:10:37', '1', '1');
INSERT INTO `base_role_resource` VALUES ('57', '6', '52', '2016-01-08 17:10:37', '1', '1');
INSERT INTO `base_role_resource` VALUES ('58', '6', '53', '2016-01-08 17:10:37', '1', '1');
INSERT INTO `base_role_resource` VALUES ('59', '6', '54', '2016-01-08 17:10:37', '1', '1');
INSERT INTO `base_role_resource` VALUES ('60', '6', '55', '2016-01-08 17:10:37', '1', '1');
INSERT INTO `base_role_resource` VALUES ('61', '6', '56', '2016-01-08 17:10:37', '1', '1');
INSERT INTO `base_role_resource` VALUES ('62', '6', '114', '2016-01-08 17:10:37', '1', '1');
INSERT INTO `base_role_resource` VALUES ('63', '7', '49', '2016-01-08 17:10:45', '1', '1');
INSERT INTO `base_role_resource` VALUES ('64', '7', '57', '2016-01-08 17:10:45', '1', '1');
INSERT INTO `base_role_resource` VALUES ('65', '7', '58', '2016-01-08 17:10:45', '1', '1');
INSERT INTO `base_role_resource` VALUES ('66', '7', '59', '2016-01-08 17:10:45', '1', '1');
INSERT INTO `base_role_resource` VALUES ('67', '7', '60', '2016-01-08 17:10:45', '1', '1');
INSERT INTO `base_role_resource` VALUES ('68', '7', '61', '2016-01-08 17:10:45', '1', '1');
INSERT INTO `base_role_resource` VALUES ('69', '7', '113', '2016-01-08 17:10:45', '1', '1');
INSERT INTO `base_role_resource` VALUES ('70', '8', '48', '2016-01-08 17:10:58', '1', '1');
INSERT INTO `base_role_resource` VALUES ('71', '8', '68', '2016-01-08 17:10:58', '1', '1');
INSERT INTO `base_role_resource` VALUES ('72', '8', '69', '2016-01-08 17:10:58', '1', '1');
INSERT INTO `base_role_resource` VALUES ('73', '8', '70', '2016-01-08 17:10:58', '1', '1');
INSERT INTO `base_role_resource` VALUES ('74', '8', '71', '2016-01-08 17:10:58', '1', '1');
INSERT INTO `base_role_resource` VALUES ('75', '8', '72', '2016-01-08 17:10:58', '1', '1');
INSERT INTO `base_role_resource` VALUES ('76', '8', '112', '2016-01-08 17:10:58', '1', '1');

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
-- Records of base_user
-- ----------------------------
INSERT INTO `base_user` VALUES ('1', 'linwang37', '王林', '96e79218965eb72c92a549dd5a330112', '2015-12-21 10:53:49', '1');
INSERT INTO `base_user` VALUES ('2', 'baojianliu', '刘宝剑', '96e79218965eb72c92a549dd5a330112', null, '1');
INSERT INTO `base_user` VALUES ('3', 'anbang', '安邦', '96e79218965eb72c92a549dd5a330112', '2015-12-21 10:53:49', '1');
INSERT INTO `base_user` VALUES ('246', 'zhongweili', '李忠伟', '96e79218965eb72c92a549dd5a330112', null, '1');
INSERT INTO `base_user` VALUES ('248', 'congwu2', '武聪', '96e79218965eb72c92a549dd5a330112', null, '1');
INSERT INTO `base_user` VALUES ('250', 'v-qiaoqiaozhu', '朱巧巧', '96e79218965eb72c92a549dd5a330112', null, '1');
INSERT INTO `base_user` VALUES ('251', 'zhibinyang2', '杨志斌', '96e79218965eb72c92a549dd5a330112', null, '1');
INSERT INTO `base_user` VALUES ('252', 'shuailiu13', '刘帅', '96e79218965eb72c92a549dd5a330112', null, '1');
INSERT INTO `base_user` VALUES ('253', 'yibiaoxiang', '项翼彪', '96e79218965eb72c92a549dd5a330112', null, '1');
INSERT INTO `base_user` VALUES ('254', 'leimingluo', '罗磊明', '96e79218965eb72c92a549dd5a330112', null, '1');
INSERT INTO `base_user` VALUES ('255', 'chaoli48', '李超', '96e79218965eb72c92a549dd5a330112', null, '1');
INSERT INTO `base_user` VALUES ('256', 'fangwu3', '吴芳', '96e79218965eb72c92a549dd5a330112', null, '1');
INSERT INTO `base_user` VALUES ('257', 'zonghailiu', '刘宗海', '96e79218965eb72c92a549dd5a330112', null, '1');
INSERT INTO `base_user` VALUES ('258', 'chenliu10', '刘辰', '96e79218965eb72c92a549dd5a330112', null, '1');
INSERT INTO `base_user` VALUES ('259', 'dongli19', '李冬', '96e79218965eb72c92a549dd5a330112', null, '1');
INSERT INTO `base_user` VALUES ('260', 'zhihuami', '米志华', '96e79218965eb72c92a549dd5a330112', null, '1');
INSERT INTO `base_user` VALUES ('261', 'ronglongzhang', '张荣龙', '96e79218965eb72c92a549dd5a330112', null, '1');
INSERT INTO `base_user` VALUES ('262', 'jinshanbai', '白金山', '96e79218965eb72c92a549dd5a330112', null, '1');
INSERT INTO `base_user` VALUES ('263', 'qinjingfan', '范秦京', '96e79218965eb72c92a549dd5a330112', null, '1');
INSERT INTO `base_user` VALUES ('264', 'yanggao43', '高扬', '96e79218965eb72c92a549dd5a330112', null, '1');
INSERT INTO `base_user` VALUES ('265', 'v-mingma', '马明', '96e79218965eb72c92a549dd5a330112', null, '1');

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
-- Records of base_user_role
-- ----------------------------
INSERT INTO `base_user_role` VALUES ('5', '2', '2', '2015-12-31 11:24:42', '1', '1');
INSERT INTO `base_user_role` VALUES ('6', '2', '3', '2015-12-31 11:24:45', '1', '1');
INSERT INTO `base_user_role` VALUES ('7', '2', '5', '2015-12-31 11:24:47', '1', '1');
INSERT INTO `base_user_role` VALUES ('8', '2', '4', '2015-12-31 11:24:51', '1', '1');
INSERT INTO `base_user_role` VALUES ('28', '246', '2', '2016-01-07 17:19:01', '1', '1');
INSERT INTO `base_user_role` VALUES ('29', '246', '3', '2016-01-07 17:19:01', '1', '1');
INSERT INTO `base_user_role` VALUES ('30', '247', '2', '2016-01-07 17:37:53', '1', '1');
INSERT INTO `base_user_role` VALUES ('31', '247', '3', '2016-01-07 17:37:53', '1', '1');
INSERT INTO `base_user_role` VALUES ('32', '248', '2', '2016-01-07 17:38:13', '1', '1');
INSERT INTO `base_user_role` VALUES ('33', '248', '3', '2016-01-07 17:38:13', '1', '1');
INSERT INTO `base_user_role` VALUES ('34', '249', '2', '2016-01-07 17:38:32', '1', '1');
INSERT INTO `base_user_role` VALUES ('35', '249', '3', '2016-01-07 17:38:32', '1', '1');
INSERT INTO `base_user_role` VALUES ('36', '250', '2', '2016-01-07 17:38:57', '1', '1');
INSERT INTO `base_user_role` VALUES ('37', '250', '3', '2016-01-07 17:38:57', '1', '1');
INSERT INTO `base_user_role` VALUES ('38', '251', '2', '2016-01-07 17:39:11', '1', '1');
INSERT INTO `base_user_role` VALUES ('39', '251', '3', '2016-01-07 17:39:11', '1', '1');
INSERT INTO `base_user_role` VALUES ('40', '252', '2', '2016-01-07 17:39:26', '1', '1');
INSERT INTO `base_user_role` VALUES ('41', '252', '3', '2016-01-07 17:39:26', '1', '1');
INSERT INTO `base_user_role` VALUES ('42', '253', '2', '2016-01-07 17:39:42', '1', '1');
INSERT INTO `base_user_role` VALUES ('43', '253', '3', '2016-01-07 17:39:42', '1', '1');
INSERT INTO `base_user_role` VALUES ('44', '254', '2', '2016-01-07 17:39:57', '1', '1');
INSERT INTO `base_user_role` VALUES ('45', '254', '3', '2016-01-07 17:39:57', '1', '1');
INSERT INTO `base_user_role` VALUES ('46', '255', '2', '2016-01-07 17:40:16', '1', '1');
INSERT INTO `base_user_role` VALUES ('47', '255', '3', '2016-01-07 17:40:16', '1', '1');
INSERT INTO `base_user_role` VALUES ('48', '256', '2', '2016-01-07 17:40:38', '1', '1');
INSERT INTO `base_user_role` VALUES ('49', '256', '3', '2016-01-07 17:40:38', '1', '1');
INSERT INTO `base_user_role` VALUES ('50', '257', '2', '2016-01-07 17:40:54', '1', '1');
INSERT INTO `base_user_role` VALUES ('51', '257', '3', '2016-01-07 17:40:54', '1', '1');
INSERT INTO `base_user_role` VALUES ('52', '258', '2', '2016-01-07 17:41:11', '1', '1');
INSERT INTO `base_user_role` VALUES ('53', '258', '3', '2016-01-07 17:41:11', '1', '1');
INSERT INTO `base_user_role` VALUES ('54', '259', '2', '2016-01-07 17:41:26', '1', '1');
INSERT INTO `base_user_role` VALUES ('55', '259', '3', '2016-01-07 17:41:26', '1', '1');
INSERT INTO `base_user_role` VALUES ('56', '260', '2', '2016-01-07 17:41:40', '1', '1');
INSERT INTO `base_user_role` VALUES ('57', '260', '3', '2016-01-07 17:41:40', '1', '1');
INSERT INTO `base_user_role` VALUES ('58', '261', '2', '2016-01-07 17:41:55', '1', '1');
INSERT INTO `base_user_role` VALUES ('59', '261', '3', '2016-01-07 17:41:55', '1', '1');
INSERT INTO `base_user_role` VALUES ('60', '262', '2', '2016-01-07 17:42:10', '1', '1');
INSERT INTO `base_user_role` VALUES ('61', '262', '3', '2016-01-07 17:42:10', '1', '1');
INSERT INTO `base_user_role` VALUES ('62', '263', '2', '2016-01-07 17:42:26', '1', '1');
INSERT INTO `base_user_role` VALUES ('63', '263', '3', '2016-01-07 17:42:26', '1', '1');
INSERT INTO `base_user_role` VALUES ('64', '264', '2', '2016-01-07 17:42:39', '1', '1');
INSERT INTO `base_user_role` VALUES ('65', '264', '3', '2016-01-07 17:42:39', '1', '1');
INSERT INTO `base_user_role` VALUES ('66', '265', '2', '2016-01-07 17:42:53', '1', '1');
INSERT INTO `base_user_role` VALUES ('67', '265', '3', '2016-01-07 17:42:53', '1', '1');
INSERT INTO `base_user_role` VALUES ('68', '266', '2', '2016-01-07 17:43:07', '1', '1');
INSERT INTO `base_user_role` VALUES ('69', '266', '3', '2016-01-07 17:43:07', '1', '1');
INSERT INTO `base_user_role` VALUES ('85', '3', '2', '2016-01-08 13:32:14', '1', '1');
INSERT INTO `base_user_role` VALUES ('86', '3', '3', '2016-01-08 13:32:14', '1', '1');
INSERT INTO `base_user_role` VALUES ('87', '3', '4', '2016-01-08 13:32:14', '1', '1');
INSERT INTO `base_user_role` VALUES ('88', '3', '5', '2016-01-08 13:32:14', '1', '1');
INSERT INTO `base_user_role` VALUES ('89', '3', '6', '2016-01-08 13:32:15', '1', '1');
INSERT INTO `base_user_role` VALUES ('90', '3', '7', '2016-01-08 13:32:15', '1', '1');
INSERT INTO `base_user_role` VALUES ('91', '3', '8', '2016-01-08 13:32:15', '1', '1');
INSERT INTO `base_user_role` VALUES ('92', '3', '9', '2016-01-08 13:32:15', '1', '1');
INSERT INTO `base_user_role` VALUES ('115', '1', '2', '2016-01-08 14:25:12', '1', '1');
INSERT INTO `base_user_role` VALUES ('116', '1', '3', '2016-01-08 14:25:12', '1', '1');
INSERT INTO `base_user_role` VALUES ('117', '1', '4', '2016-01-08 14:25:12', '1', '1');
INSERT INTO `base_user_role` VALUES ('118', '1', '5', '2016-01-08 14:25:12', '1', '1');
INSERT INTO `base_user_role` VALUES ('119', '1', '6', '2016-01-08 14:25:12', '1', '1');
INSERT INTO `base_user_role` VALUES ('120', '1', '7', '2016-01-08 14:25:12', '1', '1');
INSERT INTO `base_user_role` VALUES ('121', '1', '8', '2016-01-08 14:25:12', '1', '1');
INSERT INTO `base_user_role` VALUES ('122', '1', '9', '2016-01-08 14:25:12', '1', '1');

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
-- Records of kpi_enactment_cultural
-- ----------------------------
INSERT INTO `kpi_enactment_cultural` VALUES ('56', '28', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('57', '29', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('58', '30', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('59', '31', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('60', '32', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('61', '33', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('62', '34', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('63', '35', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('64', '36', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('65', '37', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('66', '38', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('67', '39', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('68', '40', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('69', '41', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('70', '42', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('71', '43', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('72', '44', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('73', '45', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('74', '46', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('75', '47', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('76', '48', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('77', '49', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('78', '50', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('79', '51', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('80', '52', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('81', '53', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('82', '54', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('83', '55', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('84', '56', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('85', '57', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('86', '58', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('87', '59', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('88', '60', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('89', '61', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('90', '62', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('91', '63', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('92', '64', '0', '能力素质', null, null, null, '1');
INSERT INTO `kpi_enactment_cultural` VALUES ('93', '65', '0', '能力素质', '1', null, null, '1');

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
-- Records of kpi_enactment_self
-- ----------------------------
INSERT INTO `kpi_enactment_self` VALUES ('35', '47', '0', '', '', '100', null, null, null, null, '1');
INSERT INTO `kpi_enactment_self` VALUES ('36', '48', '0', '', '', '100', null, null, null, null, '1');
INSERT INTO `kpi_enactment_self` VALUES ('37', '49', '0', '', '', '100', null, null, null, null, '1');
INSERT INTO `kpi_enactment_self` VALUES ('38', '50', '0', '', '', '100', null, null, null, null, '1');
INSERT INTO `kpi_enactment_self` VALUES ('39', '51', '0', '', '', '100', null, null, null, null, '1');
INSERT INTO `kpi_enactment_self` VALUES ('40', '52', '0', '', '', '100', null, null, null, null, '1');
INSERT INTO `kpi_enactment_self` VALUES ('41', '53', '0', '', '', '100', null, null, null, null, '1');
INSERT INTO `kpi_enactment_self` VALUES ('42', '54', '0', '', '', '100', null, null, null, null, '1');
INSERT INTO `kpi_enactment_self` VALUES ('43', '55', '0', '', '', '100', null, null, null, null, '1');
INSERT INTO `kpi_enactment_self` VALUES ('44', '56', '0', '', '', '100', null, null, null, null, '1');
INSERT INTO `kpi_enactment_self` VALUES ('45', '57', '0', '', '', '100', null, null, null, null, '1');
INSERT INTO `kpi_enactment_self` VALUES ('46', '58', '0', '', '', '100', null, null, null, null, '1');
INSERT INTO `kpi_enactment_self` VALUES ('47', '59', '0', '', '', '100', null, null, null, null, '1');
INSERT INTO `kpi_enactment_self` VALUES ('48', '60', '0', '', '', '100', null, null, null, null, '1');
INSERT INTO `kpi_enactment_self` VALUES ('49', '61', '0', '', '', '100', null, null, null, null, '1');
INSERT INTO `kpi_enactment_self` VALUES ('50', '62', '0', '', '', '100', null, null, null, null, '1');
INSERT INTO `kpi_enactment_self` VALUES ('51', '63', '0', '', '', '100', null, null, null, null, '1');
INSERT INTO `kpi_enactment_self` VALUES ('52', '64', '0', '', '', '100', null, null, null, null, '1');
INSERT INTO `kpi_enactment_self` VALUES ('53', '65', '0', '1', '1', '100', null, null, null, null, '1');

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

-- ----------------------------
-- Records of kpi_main
-- ----------------------------
INSERT INTO `kpi_main` VALUES ('28', '30', null, '246', '2', '2016', '第一季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('29', '31', null, '248', '2', '2016', '第一季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('30', '32', null, '250', '2', '2016', '第一季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('31', '33', null, '251', '2', '2016', '第一季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('32', '34', null, '252', '2', '2016', '第一季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('33', '35', null, '253', '2', '2016', '第一季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('34', '36', null, '254', '2', '2016', '第一季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('35', '37', null, '255', '2', '2016', '第一季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('36', '38', null, '256', '2', '2016', '第一季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('37', '39', null, '257', '2', '2016', '第一季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('38', '40', null, '258', '2', '2016', '第一季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('39', '41', null, '259', '2', '2016', '第一季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('40', '42', null, '260', '2', '2016', '第一季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('41', '43', null, '261', '2', '2016', '第一季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('42', '44', null, '262', '2', '2016', '第一季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('43', '45', null, '263', '2', '2016', '第一季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('44', '46', null, '264', '2', '2016', '第一季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('45', '47', null, '265', '2', '2016', '第一季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('46', '48', null, '1', '2', '2016', '第一季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('47', '49', null, '246', '2', '2016', '第二季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('48', '50', null, '248', '2', '2016', '第二季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('49', '51', null, '250', '2', '2016', '第二季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('50', '52', null, '251', '2', '2016', '第二季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('51', '53', null, '252', '2', '2016', '第二季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('52', '54', null, '253', '2', '2016', '第二季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('53', '55', null, '254', '2', '2016', '第二季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('54', '56', null, '255', '2', '2016', '第二季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('55', '57', null, '256', '2', '2016', '第二季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('56', '58', null, '257', '2', '2016', '第二季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('57', '59', null, '258', '2', '2016', '第二季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('58', '60', null, '259', '2', '2016', '第二季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('59', '61', null, '260', '2', '2016', '第二季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('60', '62', null, '261', '2', '2016', '第二季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('61', '63', null, '262', '2', '2016', '第二季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('62', '64', null, '263', '2', '2016', '第二季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('63', '65', null, '264', '2', '2016', '第二季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('64', '66', null, '265', '2', '2016', '第二季度', '2', null, null, '1');
INSERT INTO `kpi_main` VALUES ('65', '67', null, '1', '2', '2016', '第二季度', '2', null, null, '1');
