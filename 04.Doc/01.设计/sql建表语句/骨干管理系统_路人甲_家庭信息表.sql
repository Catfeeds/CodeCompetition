/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : hw_it

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-12-14 17:00:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `mms_family_information`
-- ----------------------------
DROP TABLE IF EXISTS `mms_family_information`;
CREATE TABLE `mms_family_information` (
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
-- Records of mms_family_information
-- ----------------------------
INSERT INTO `mms_family_information` VALUES ('	10008612', '2011/11/1', 's士大夫', '陕西', '共和党', '188', '80', '西安', '西安', '谁', '陕西', '110', '在职', '1个女孩', '不行', '1个小时', '否', '无', '无');
INSERT INTO `mms_family_information` VALUES ('10008611', '2011/11/1', 's士大夫', '陕西', '共和党', '122', '33', '西安', '西安', '黑', '陕西', '110', '在职', '1个女孩', '学', '1个小时', '是', '2018/10/10', '无');
