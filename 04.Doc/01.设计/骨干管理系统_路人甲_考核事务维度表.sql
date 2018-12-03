/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50560
Source Host           : localhost:3306
Source Database       : itsys

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2018-12-03 17:10:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hrbp_affair_dimension
-- ----------------------------
DROP TABLE IF EXISTS `hrbp_affair_dimension`;
CREATE TABLE `hrbp_affair_dimension` (
  `affair_id` int(11) NOT NULL,
  `dimension_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hrbp_affair_dimension
-- ----------------------------
INSERT INTO `hrbp_affair_dimension` VALUES ('2', '2');
