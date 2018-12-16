/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : hw_it

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-12-14 16:58:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `mms_company_qualification`
-- ----------------------------
DROP TABLE IF EXISTS `mms_company_qualification`;
CREATE TABLE `mms_company_qualification` (
  `qualificationName` varchar(64) DEFAULT NULL COMMENT '资质名称',
  `qualificationType` varchar(64) DEFAULT NULL COMMENT '资质类型',
  `certificationLevel` varchar(64) DEFAULT NULL COMMENT '认证等级',
  `passDate` varchar(64) DEFAULT NULL COMMENT '通过日期',
  `certificateNum` int(64) NOT NULL COMMENT '证书编号',
  `employeeID` varchar(16) DEFAULT NULL COMMENT '员工工号',
  PRIMARY KEY (`certificateNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mms_company_qualification
-- ----------------------------
INSERT INTO `mms_company_qualification` VALUES ('100178', '1', '1', '1', '1', '	10008611');
INSERT INTO `mms_company_qualification` VALUES ('计算机初级', '省级', '2', null, '2', '10008611');
INSERT INTO `mms_company_qualification` VALUES ('100178', '12', '12', '12', '12', '	10008611');
