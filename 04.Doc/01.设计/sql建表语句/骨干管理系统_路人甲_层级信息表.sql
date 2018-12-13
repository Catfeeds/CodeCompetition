/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50642
Source Host           : localhost:3306
Source Database       : hw_it

Target Server Type    : MYSQL
Target Server Version : 50642
File Encoding         : 65001

Date: 2018-12-13 15:11:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hrbp_level_info
-- ----------------------------
DROP TABLE IF EXISTS `hrbp_level_info`;
CREATE TABLE `hrbp_level_info` (
  `levelID` int(20) NOT NULL AUTO_INCREMENT,
  `levelName` varchar(255) DEFAULT NULL,
  `levelNode` varchar(255) DEFAULT NULL,
  `levelType` int(2) DEFAULT NULL,
  `levelInfo` varchar(255) DEFAULT NULL,
  `tableName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`levelID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
