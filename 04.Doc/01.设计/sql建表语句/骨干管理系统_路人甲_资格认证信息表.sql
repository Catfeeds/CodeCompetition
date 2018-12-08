/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : hw_it

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-11-30 16:16:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `company_qualification`
-- ----------------------------
DROP TABLE IF EXISTS `company_qualification`;
CREATE TABLE `company_qualification` (
  `qualificationName` varchar(64) DEFAULT NULL COMMENT '资质名称',
  `qualificationType` varchar(64) DEFAULT NULL COMMENT '资质类型',
  `certificationLevel` varchar(64) DEFAULT NULL COMMENT '认证等级',
  `passDate` varchar(64) DEFAULT NULL COMMENT '通过日期',
  `certificateNum` varchar(64) NOT NULL COMMENT '证书编号',
  `employeeID` varchar(16) DEFAULT NULL COMMENT '员工工号',
  PRIMARY KEY (`certificateNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of company_qualification
-- ----------------------------
INSERT INTO `company_qualification` VALUES ('string', 'string', 'string', 'string', '111', '1');
INSERT INTO `company_qualification` VALUES ('qualificationName', 'qualificationType', 'certificationLevel', 'passDate', 'qualificationID', 'employeeID');
INSERT INTO `company_qualification` VALUES ('string', 'string', 'string', 'string', 'string', '100178');
