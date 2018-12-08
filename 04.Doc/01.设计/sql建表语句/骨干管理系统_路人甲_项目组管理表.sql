/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50642
Source Host           : localhost:3306
Source Database       : hw_it

Target Server Type    : MYSQL
Target Server Version : 50642
File Encoding         : 65001

Date: 2018-12-07 10:57:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for project_level
-- ----------------------------
DROP TABLE IF EXISTS `project_level`;
CREATE TABLE `project_level` (
  `nodeID` int(10) NOT NULL AUTO_INCREMENT COMMENT '节点ID',
  `nodePath` varchar(255) DEFAULT NULL COMMENT '节点路径',
  `nodeName` varchar(255) DEFAULT NULL COMMENT '节点名称',
  `isLeafNode` tinyint(1) NOT NULL COMMENT '是否是叶子节点',
  `isProject` tinyint(1) NOT NULL COMMENT '是否是项目组',
  `projectID` varchar(255) DEFAULT NULL COMMENT '项目组ID',
  `projectName` varchar(255) DEFAULT NULL COMMENT '项目组名称',
  `pmID` varchar(255) DEFAULT NULL COMMENT 'PMID',
  `pmName` varchar(255) DEFAULT NULL COMMENT 'PM名字',
  PRIMARY KEY (`nodeID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
