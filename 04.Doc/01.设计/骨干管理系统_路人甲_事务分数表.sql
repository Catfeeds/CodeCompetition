/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : hw_it

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-12-07 19:06:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `transaction_table`
-- ----------------------------
DROP TABLE IF EXISTS `transaction_table`;
CREATE TABLE `transaction_table` (
  `employeeID` varchar(16) NOT NULL COMMENT '员工工号',
  `transactionName` varchar(200) DEFAULT NULL COMMENT '事务名',
  `dimensionName` varchar(200) DEFAULT NULL COMMENT '维度名',
  `dimensionScore` varchar(500) DEFAULT NULL COMMENT '维度分数'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of transaction_table
-- ----------------------------
INSERT INTO `transaction_table` VALUES ('123', '事务1', '维度1', '100');
INSERT INTO `transaction_table` VALUES ('123', '事务1', '维度2', '100');
INSERT INTO `transaction_table` VALUES ('123', '事务2', '维度1', '100');
INSERT INTO `transaction_table` VALUES ('123', '事务2', '维度2', '100');
