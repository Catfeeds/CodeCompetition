/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50642
Source Host           : localhost:3306
Source Database       : hw_it

Target Server Type    : MYSQL
Target Server Version : 50642
File Encoding         : 65001

Date: 2018-12-13 15:11:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hrbp_level_team
-- ----------------------------
DROP TABLE IF EXISTS `hrbp_level_team`;
CREATE TABLE `hrbp_level_team` (
  `nodeID` int(20) NOT NULL AUTO_INCREMENT,
  `nodeName` varchar(255) DEFAULT NULL,
  `nodePath` longtext,
  `nodeType` varchar(10) DEFAULT NULL,
  `isTeamNode` tinyint(1) DEFAULT NULL,
  `teamID` int(20) DEFAULT NULL,
  PRIMARY KEY (`nodeID`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
