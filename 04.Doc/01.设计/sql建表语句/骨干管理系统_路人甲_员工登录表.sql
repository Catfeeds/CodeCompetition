/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50560
Source Host           : localhost:3306
Source Database       : hw_it

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2018-12-13 16:12:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for login_information
-- ----------------------------
DROP TABLE IF EXISTS `login_information`;
CREATE TABLE `login_information` (
  `employee_ID` varchar(16) NOT NULL DEFAULT '' COMMENT '软通工号',
  `employee_name` varchar(16) DEFAULT NULL COMMENT '员工姓名',
  `pass_word` varchar(32) DEFAULT NULL COMMENT '密码',
  `role_id` int(10) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`employee_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of login_information
-- ----------------------------
INSERT INTO `login_information` VALUES ('10008611', '张1博', 'e10adc3949ba59abbe56e057f20f883e', '1');
INSERT INTO `login_information` VALUES ('10008619', '杨1丽', 'e10adc3949ba59abbe56e057f20f883e', '2');
INSERT INTO `login_information` VALUES ('10008629', '陈D卿', 'e10adc3949ba59abbe56e057f20f883e', '3');
