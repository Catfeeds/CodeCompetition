/*
Navicat MySQL Data Transfer

Source Server         : wxg
Source Server Version : 50723
Source Host           : 127.0.0.1:3306
Source Database       : hw_it

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-12-16 23:33:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mms_data_auth
-- ----------------------------
DROP TABLE IF EXISTS `mms_data_auth`;
CREATE TABLE `mms_data_auth` (
  `postId` int(4) NOT NULL AUTO_INCREMENT COMMENT '岗位Id',
  `postName` varchar(16) DEFAULT NULL COMMENT '岗位名称',
  `authType` int(1) NOT NULL COMMENT '权限类型:0:人力树;1:关系树',
  `employeeId` varchar(16) DEFAULT NULL COMMENT '员工Id',
  `authContent` varchar(128) DEFAULT NULL COMMENT '权限细节内容',
  `creatorId` varchar(16) DEFAULT NULL,
  `creatorName` varchar(32) DEFAULT NULL,
  `createTime` date DEFAULT NULL,
  `updateStaffId` varchar(16) DEFAULT NULL,
  `updateStaffName` varchar(32) DEFAULT NULL,
  `updateTime` date DEFAULT NULL,
  PRIMARY KEY (`postId`,`authType`),
  KEY `empolyeeId` (`employeeId`),
  CONSTRAINT `empolyeeId` FOREIGN KEY (`employeeId`) REFERENCES `base_staff_info` (`employeeID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
