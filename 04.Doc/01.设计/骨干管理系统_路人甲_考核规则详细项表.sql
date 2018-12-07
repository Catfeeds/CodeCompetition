/*
Navicat MySQL Data Transfer

Source Server         : wxg
Source Server Version : 50723
Source Host           : 127.0.0.1:3306
Source Database       : hw_it

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-12-08 00:04:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hrbp_examination_rule_item
-- ----------------------------
DROP TABLE IF EXISTS `hrbp_examination_rule_item`;
CREATE TABLE `hrbp_examination_rule_item` (
  `ruleId` int(8) NOT NULL COMMENT '规则Id',
  `ItemId` int(8) NOT NULL COMMENT '规则项Id',
  `ItemType` int(1) NOT NULL COMMENT '规则项类型(0:培训;1:事务)',
  `ItemName` varchar(32) DEFAULT NULL COMMENT '规则项名称',
  `credit` int(4) DEFAULT NULL COMMENT '学分',
  `pass` int(4) DEFAULT NULL COMMENT '学分及格线',
  PRIMARY KEY (`ruleId`,`ItemId`,`ItemType`),
  KEY `ItemId` (`ItemId`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
