/*
Navicat MySQL Data Transfer

Source Server         : wxg
Source Server Version : 50723
Source Host           : 127.0.0.1:3306
Source Database       : hw_it

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-12-08 00:37:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hrbp_examination_rule
-- ----------------------------
DROP TABLE IF EXISTS `hrbp_examination_rule`;
CREATE TABLE `hrbp_examination_rule` (
  `ruleId` int(8) NOT NULL AUTO_INCREMENT COMMENT '规则Id',
  `roleId` int(8) DEFAULT NULL COMMENT '角色Id',
  `ruleLevel` varchar(16) DEFAULT NULL COMMENT '岗位级别',
  `creatorId` int(8) DEFAULT NULL COMMENT '创建者工号',
  `creatorName` varchar(32) DEFAULT NULL COMMENT '创建者姓名',
  `createTime` date DEFAULT NULL COMMENT '创建时间',
  `updateStaffId` int(8) DEFAULT NULL COMMENT '更新人员工号',
  `updateStaffName` varchar(32) DEFAULT NULL COMMENT '更新人员姓名',
  `updateTime` date DEFAULT NULL COMMENT '更新时间',
  `passCredit` int(4) DEFAULT NULL COMMENT '通过学分',
  PRIMARY KEY (`ruleId`),
  KEY `ROLE_ID` (`roleId`),
  CONSTRAINT `ROLE_ID` FOREIGN KEY (`roleId`) REFERENCES `hrbp_special_role` (`roleId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8;
