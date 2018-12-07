/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50642
Source Host           : localhost:3306
Source Database       : hw_it

Target Server Type    : MYSQL
Target Server Version : 50642
File Encoding         : 65001

Date: 2018-12-07 10:55:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for level_rla_per
-- ----------------------------
DROP TABLE IF EXISTS `level_rla_per`;
CREATE TABLE `level_rla_per` (
  `nodeID` int(50) NOT NULL AUTO_INCREMENT COMMENT '节点ID',
  `nodePath` varchar(500) DEFAULT NULL COMMENT '节点路径',
  `nodeName` varchar(100) DEFAULT NULL COMMENT '节点名称',
  `isLeafNode` tinyint(1) NOT NULL COMMENT '是否是叶子节点',
  `isRelationNode` tinyint(1) NOT NULL COMMENT '是否是最低层关系节点',
  `relationID` varchar(100) DEFAULT NULL COMMENT '关系ID',
  PRIMARY KEY (`nodeID`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
