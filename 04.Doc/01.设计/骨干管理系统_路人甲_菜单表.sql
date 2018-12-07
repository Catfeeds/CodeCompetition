/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50560
Source Host           : localhost:3306
Source Database       : itsys

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2018-12-03 17:11:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hrbp_menu
-- ----------------------------
DROP TABLE IF EXISTS `hrbp_menu`;
CREATE TABLE `hrbp_menu` (
  `menu_id` int(11) NOT NULL,
  `menu_name` varchar(50) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  `note` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hrbp_menu
-- ----------------------------
INSERT INTO `hrbp_menu` VALUES ('1', 'Login', '0', '100', '登录');
INSERT INTO `hrbp_menu` VALUES ('2', 'ReportShow', '0', '200', '报表展示');
INSERT INTO `hrbp_menu` VALUES ('3', 'StaffManagement', '0', '300', '人员管理');
INSERT INTO `hrbp_menu` VALUES ('4', 'ProjectManagement', '0', '400', '项目管理');
INSERT INTO `hrbp_menu` VALUES ('5', 'OrganizationStructure', '0', '500', '组织架构');
INSERT INTO `hrbp_menu` VALUES ('6', 'TrainingPlan', '0', '600', '培养计划');
INSERT INTO `hrbp_menu` VALUES ('7', 'System', '0', '700', '系统设置');
INSERT INTO `hrbp_menu` VALUES ('201', 'StoneReport', '2', '201', '骨干报表');
INSERT INTO `hrbp_menu` VALUES ('301', 'StaffInformation', '3', '301', '员工详情');
INSERT INTO `hrbp_menu` VALUES ('401', 'ProjectSetUp', '4', '401', '项目组设置');
INSERT INTO `hrbp_menu` VALUES ('501', 'BusinessSystem', '5', '501', '业务体系');
INSERT INTO `hrbp_menu` VALUES ('502', 'StaffSystem', '5', '502', '人力体系');
INSERT INTO `hrbp_menu` VALUES ('601', 'TrainSetUp', '6', '601', '培养设置');
INSERT INTO `hrbp_menu` VALUES ('602', 'ExaminationAffairsSetUp', '6', '602', '考核事务设置');
INSERT INTO `hrbp_menu` VALUES ('603', 'ExaminationRoleSetUp', '6', '603', '考核规则设置');
INSERT INTO `hrbp_menu` VALUES ('604', 'OpeningSetUp', '6', '604', '开班设置');
INSERT INTO `hrbp_menu` VALUES ('605', 'ExaminationSetUp', '6', '605', '考试设置');
INSERT INTO `hrbp_menu` VALUES ('606', 'ResultShow', '6', '606', '成绩展示');
INSERT INTO `hrbp_menu` VALUES ('701', 'RoleSetUp', '7', '701', '角色设置');
INSERT INTO `hrbp_menu` VALUES ('702', 'UserManagement', '7', '702', '登录用户管理');
INSERT INTO `hrbp_menu` VALUES ('703', 'BackUpRestore', '7', '703', '备份与还原');
INSERT INTO `hrbp_menu` VALUES ('704', 'LogManagement', '7', '704', '日志管理');