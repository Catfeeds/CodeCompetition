/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50560
Source Host           : localhost:3306
Source Database       : hw_it

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2018-12-12 10:33:10
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
  `explanation` varchar(240) DEFAULT NULL COMMENT '考核说明',
  PRIMARY KEY (`dimension_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hrbp_examination_dimension
-- ----------------------------
INSERT INTO `hrbp_examination_dimension` VALUES ('14', '考勤', '10.00', '人员考勤');
INSERT INTO `hrbp_examination_dimension` VALUES ('15', '汇报情况', '20.00', '是否及时汇报学习状态');
INSERT INTO `hrbp_examination_dimension` VALUES ('16', '调皮', '30.00', null);
INSERT INTO `hrbp_examination_dimension` VALUES ('17', '理事', '22.00', null);
INSERT INTO `hrbp_examination_dimension` VALUES ('18', '就是这样', '30.00', null);
