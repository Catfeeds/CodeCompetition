/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50642
Source Host           : localhost:3306
Source Database       : hw_it

Target Server Type    : MYSQL
Target Server Version : 50642
File Encoding         : 65001

Date: 2018-12-07 11:00:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `time` datetime NOT NULL,
  `URL` varchar(255) NOT NULL,
  `IPAddress` varchar(255) NOT NULL,
  `account` varchar(255) NOT NULL,
  `method` varchar(255) DEFAULT NULL,
  `param` longtext,
  `message` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`time`,`URL`,`IPAddress`,`account`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
