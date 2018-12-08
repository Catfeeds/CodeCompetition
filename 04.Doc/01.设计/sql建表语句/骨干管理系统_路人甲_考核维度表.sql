/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50560
Source Host           : localhost:3306
Source Database       : itsys

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2018-12-04 21:13:52
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
  PRIMARY KEY (`dimension_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hrbp_examination_dimension
-- ----------------------------
INSERT INTO `hrbp_examination_dimension` VALUES ('1', '晓光评价', '2.50', '4.00', '晓光评价');
INSERT INTO `hrbp_examination_dimension` VALUES ('2', '晓光来评价', '2.00', '2.00', '晓光来评价');
INSERT INTO `hrbp_examination_dimension` VALUES ('4', '三号评价', '24.20', '6.12', '三号评价');
INSERT INTO `hrbp_examination_dimension` VALUES ('5', '二号评价', '3.20', '3.12', '二号评价');
INSERT INTO `hrbp_examination_dimension` VALUES ('6', '一号评价', '2.20', '2.12', '一号评价');
INSERT INTO `hrbp_examination_dimension` VALUES ('7', '二号评价', '3.20', '3.12', '二号评价');
INSERT INTO `hrbp_examination_dimension` VALUES ('8', '三号评价', '24.20', '6.12', '三号评价');
INSERT INTO `hrbp_examination_dimension` VALUES ('9', '一号评价', '2.20', '2.12', '一号评价');
INSERT INTO `hrbp_examination_dimension` VALUES ('10', '二号评价', '3.20', '3.12', '二号评价');
INSERT INTO `hrbp_examination_dimension` VALUES ('11', '三号评价', '24.20', '6.12', '三号评价');
