/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : hw_it

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-12-07 19:07:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `course_table`
-- ----------------------------
DROP TABLE IF EXISTS `course_table`;
CREATE TABLE `course_table` (
  `employeeID` varchar(16) NOT NULL COMMENT '员工工号',
  `courseDimension` varchar(500) DEFAULT NULL COMMENT '课程维度',
  `courseName` varchar(500) DEFAULT NULL COMMENT '课程名',
  `courseScore` varchar(500) DEFAULT NULL COMMENT '课程成绩'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_table
-- ----------------------------
INSERT INTO `course_table` VALUES ('123', '考试1', '课程1', '111');
INSERT INTO `course_table` VALUES ('123', '考试2', '课程2', '11');
INSERT INTO `course_table` VALUES ('123', '考试3', '课程3', '11');
INSERT INTO `course_table` VALUES ('100178', '考斯1', '课程1', '123');
