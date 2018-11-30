/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50642
Source Host           : localhost:3306
Source Database       : hw_it

Target Server Type    : MYSQL
Target Server Version : 50642
File Encoding         : 65001

Date: 2018-11-28 09:27:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_level_rla
-- ----------------------------
DROP TABLE IF EXISTS `sys_level_rla`;
CREATE TABLE `sys_level_rla` (
  `nodeID` int(50) NOT NULL AUTO_INCREMENT COMMENT '节点ID',
  `nodePath` varchar(500) DEFAULT NULL COMMENT '节点路径',
  `nodeName` varchar(100) DEFAULT NULL COMMENT '节点名称',
  `isLeafNode` tinyint(1) NOT NULL COMMENT '是否是叶子节点',
  `isProjectNode` tinyint(1) NOT NULL COMMENT '是否是项目组节点',
  `ProjectTeamID` int(10) DEFAULT NULL COMMENT '项目组ID',
  PRIMARY KEY (`nodeID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
