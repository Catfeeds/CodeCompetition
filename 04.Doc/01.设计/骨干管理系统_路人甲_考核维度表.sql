/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50560
Source Host           : localhost:3306
Source Database       : itsys

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2018-12-03 17:10:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hrbp_examination_dimension
-- ----------------------------
DROP TABLE IF EXISTS `hrbp_examination_dimension`;
CREATE TABLE `hrbp_examination_dimension` (
  `dimension_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '维度Id',
  `dimension_name` varchar(16) NOT NULL COMMENT '考核维度名',
  `mark` decimal(8,2) NOT NULL COMMENT '分数',
  `coefficient` decimal(8,2) NOT NULL COMMENT '权重',
  `explanation` varchar(16) DEFAULT NULL COMMENT '考核说明',
  `dimensionsum` varchar(16) NOT NULL COMMENT '维度总分数',
  PRIMARY KEY (`dimension_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hrbp_examination_dimension
-- ----------------------------
INSERT INTO `hrbp_examination_dimension` VALUES ('1', '晓光评价', '2.50', '4.00', '晓光评价', '10');
INSERT INTO `hrbp_examination_dimension` VALUES ('2', '晓光来评价', '2.00', '2.00', '晓光来评价', '4');
INSERT INTO `hrbp_examination_dimension` VALUES ('3', '啦啦啦啦', '2.00', '3.00', null, '6');
