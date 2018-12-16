/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : hw_it

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-12-14 16:59:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `mms_technical_information`
-- ----------------------------
DROP TABLE IF EXISTS `mms_technical_information`;
CREATE TABLE `mms_technical_information` (
  `employeeID` varchar(16) NOT NULL COMMENT '软通工号',
  `technologyPlatform` varchar(200) DEFAULT NULL COMMENT '技术平台',
  `technologyGrade` varchar(200) DEFAULT NULL COMMENT '技术等级',
  `areasOfExpertise` varchar(200) DEFAULT NULL COMMENT '擅长领域',
  `technicalName` varchar(200) DEFAULT NULL COMMENT '技能名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mms_technical_information
-- ----------------------------
INSERT INTO `mms_technical_information` VALUES ('	10008611', '软通', '2', 'java,C++', 'Spring');
