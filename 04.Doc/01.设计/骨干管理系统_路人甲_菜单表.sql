/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50560
Source Host           : localhost:3306
Source Database       : itsys

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2018-11-29 17:50:28
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
INSERT INTO `hrbp_menu` VALUES ('1', 'BackboneReport', '0', '100', '骨干报表');
INSERT INTO `hrbp_menu` VALUES ('2', 'System', '0', '200', '系统设置');
INSERT INTO `hrbp_menu` VALUES ('201', 'ProjectTeam', '2', '201', '项目组设置');
INSERT INTO `hrbp_menu` VALUES ('202', 'RelationTree', '2', '202', '关系树设置');
INSERT INTO `hrbp_menu` VALUES ('203', 'Training', '2', '203', '培训设置');
INSERT INTO `hrbp_menu` VALUES ('204', 'ExaminationAffairs', '2', '204', '考核事务设置');
INSERT INTO `hrbp_menu` VALUES ('205', 'ExaminationRules', '2', '205', '考核规则设置');
INSERT INTO `hrbp_menu` VALUES ('206', 'CourseOpen', '2', '206', '开班设置');
INSERT INTO `hrbp_menu` VALUES ('207', 'Examination', '2', '207', '考试设置');
INSERT INTO `hrbp_menu` VALUES ('208', 'Import', '2', '208', '成绩导入');
INSERT INTO `hrbp_menu` VALUES ('209', 'Role', '2', '209', '角色设置');
INSERT INTO `hrbp_menu` VALUES ('210', 'Login', '2', '210', '登录用户设置');
