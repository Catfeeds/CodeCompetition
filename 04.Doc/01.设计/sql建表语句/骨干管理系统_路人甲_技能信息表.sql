/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : hw_it

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-11-30 16:15:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `technical_information`
-- ----------------------------
DROP TABLE IF EXISTS `technical_information`;
CREATE TABLE `technical_information` (
  `employeeID` varchar(16) NOT NULL COMMENT '软通工号',
  `technologyPlatform` varchar(200) DEFAULT NULL COMMENT '技术平台',
  `technologyGrade` varchar(200) DEFAULT NULL COMMENT '技术等级',
  `areasOfExpertise` varchar(200) DEFAULT NULL COMMENT '擅长领域',
  `technicalName` varchar(200) DEFAULT NULL  COMMENT '技能名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of technical_information
-- ----------------------------
INSERT INTO `technical_information` VALUES ('1', '1', '1', '1111');
INSERT INTO `technical_information` VALUES ('100178', 'string', 'string', 'string');
INSERT INTO `technical_information` VALUES ('111111', 'string', 'string', 'string');
