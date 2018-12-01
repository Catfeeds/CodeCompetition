/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50560
Source Host           : localhost:3306
Source Database       : itsys

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2018-11-29 17:50:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for login_information
-- ----------------------------
DROP TABLE IF EXISTS `login_information`;
CREATE TABLE `login_information` (
  `login_id` int(11) NOT NULL COMMENT '登陆id',
  `employee_ID` varchar(16) DEFAULT NULL COMMENT '软通工号',
  `employee_name` varchar(16) DEFAULT NULL COMMENT '员工姓名',
  `pass_word` varchar(32) DEFAULT NULL COMMENT '密码',
  `role_id` varchar(16) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

