/*
Navicat MySQL Data Transfer

Source Server         : wxg
Source Server Version : 50723
Source Host           : 127.0.0.1:3306
Source Database       : hw_it

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-12-08 00:53:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hrbp_examination_rule_item
-- ----------------------------
DROP TABLE IF EXISTS `hrbp_examination_rule_item`;
CREATE TABLE `hrbp_examination_rule_item` (
  `ruleId` int(8) NOT NULL COMMENT '规则Id',
  `itemId` int(8) NOT NULL COMMENT '规则项Id',
  `itemType` int(1) NOT NULL COMMENT '规则项类型(0:培训;1:事务)',
  `itemName` varchar(32) DEFAULT NULL COMMENT '规则项名称',
  `credit` int(4) DEFAULT NULL COMMENT '学分',
  `pass` int(4) DEFAULT NULL COMMENT '学分及格线',
  PRIMARY KEY (`ruleId`,`itemId`,`itemType`),
  CONSTRAINT `ruleId` FOREIGN KEY (`ruleId`) REFERENCES `hrbp_examination_rule` (`ruleId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
