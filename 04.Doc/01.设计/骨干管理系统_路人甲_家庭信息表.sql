/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : hw_it

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-11-30 16:16:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `family_information`
-- ----------------------------
DROP TABLE IF EXISTS `family_information`;
CREATE TABLE `family_information` (
  `employeeID` varchar(16) NOT NULL COMMENT '软通工号',
  `birthdayDate` varchar(64) DEFAULT NULL COMMENT '出生年月',
  `nationality` varchar(16) DEFAULT NULL COMMENT '民族',
  `birthplace` varchar(16) DEFAULT NULL COMMENT '籍贯',
  `isPartyMember` varchar(16) DEFAULT NULL COMMENT '是否党员  0 ：是 1：否',
  `heigh` varchar(16) DEFAULT NULL COMMENT '身高',
  `weight` varchar(16) DEFAULT NULL COMMENT '体重',
  `currentHomeAddress` varchar(200) DEFAULT NULL COMMENT '现居住地址',
  `emergencyContact` varchar(16) DEFAULT NULL COMMENT '紧急联系人',
  `emergencyContactRelation` varchar(16) DEFAULT NULL COMMENT '紧急联系人关系',
  `emergencyContactAdress` varchar(200) DEFAULT NULL COMMENT '紧急联系人住址',
  `emergencyContactPhone` varchar(64) DEFAULT NULL COMMENT '紧急联系人电话',
  `maritalStatus` varchar(16) DEFAULT NULL COMMENT '婚姻状态 是 ：0  否 ：1',
  `childInformation` varchar(200) DEFAULT NULL COMMENT '子女信息',
  `wayToWork` varchar(64) DEFAULT NULL COMMENT '上班方式',
  `workingHour` varchar(16) DEFAULT NULL COMMENT '上班路上时长',
  `isbuyHouse` varchar(16) DEFAULT NULL COMMENT '是否买房',
  `dayOfBuyHouse` varchar(64) DEFAULT NULL COMMENT '买房日期',
  `propertyAddress` varchar(200) DEFAULT NULL COMMENT '房产地址',
  PRIMARY KEY (`employeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of family_information
-- ----------------------------
INSERT INTO `family_information` VALUES ('1', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string');
INSERT INTO `family_information` VALUES ('100178', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string');
