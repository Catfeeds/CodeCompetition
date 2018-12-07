/*
Navicat MySQL Data Transfer

Source Server         : wxg
Source Server Version : 50723
Source Host           : 127.0.0.1:3306
Source Database       : hw_it

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-12-07 00:16:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hrbp_system_series
-- ----------------------------
DROP TABLE IF EXISTS `hrbp_system_series`;
CREATE TABLE `hrbp_system_series` (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '体系Id',
  `systemName` varchar(32) DEFAULT NULL COMMENT '体系名称',
  `seriesName` varchar(32) DEFAULT NULL COMMENT '系列名称',
  PRIMARY KEY (`id`),
  KEY `systemName` (`systemName`) USING HASH COMMENT '体系名称',
  KEY `seriesName` (`seriesName`) USING HASH COMMENT '系列名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
