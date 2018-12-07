/*
Navicat MySQL Data Transfer

Source Server         : wxg
Source Server Version : 50723
Source Host           : 127.0.0.1:3306
Source Database       : hw_it

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-12-07 20:08:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hrbp_examination_rule
-- ----------------------------
DROP TABLE IF EXISTS `hrbp_examination_rule`;
CREATE TABLE `hrbp_examination_rule` (
  `ruleId` int(11) NOT NULL AUTO_INCREMENT COMMENT '规则Id',
  `roleId` int(11) DEFAULT NULL COMMENT '角色Id',
  `level` varchar(16) DEFAULT NULL COMMENT '岗位级别',
  `creatorId` int(8) DEFAULT NULL COMMENT '创建者工号',
  `creatorName` varchar(32) DEFAULT NULL COMMENT '创建者姓名',
  `createTime` date DEFAULT NULL COMMENT '创建时间',
  `updateStaffId` int(8) DEFAULT NULL COMMENT '更新人员工号',
  `updateStaffName` varchar(32) DEFAULT NULL COMMENT '更新人员姓名',
  `updateTime` date DEFAULT NULL COMMENT '更新时间',
  `trainingIds` int(8) DEFAULT NULL COMMENT '培训Id，以 | 分隔开',
  `trainingCredits` int(4) DEFAULT NULL COMMENT '培训对应学分，以 | 分隔开',
  `traningPass` int(4) DEFAULT NULL COMMENT '培训及格分',
  `affairIds` int(8) DEFAULT NULL COMMENT '事务Id，以 | 分隔开',
  `affairCredits` int(4) DEFAULT NULL COMMENT '事务对应学分，以 | 分隔开',
  `affairPass` int(4) DEFAULT NULL COMMENT '事务及格分',
  `creditPass` int(4) DEFAULT NULL COMMENT '学分及格分',
  PRIMARY KEY (`ruleId`),
  KEY `ROLE_ID` (`roleId`),
  CONSTRAINT `ROLE_ID` FOREIGN KEY (`roleId`) REFERENCES `hrbp_special_role` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
