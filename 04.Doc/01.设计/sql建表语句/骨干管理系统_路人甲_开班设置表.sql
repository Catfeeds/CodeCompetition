/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50560
Source Host           : localhost:3306
Source Database       : hw_it

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2018-12-12 14:43:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for opening_setup_info
-- ----------------------------
DROP TABLE IF EXISTS `opening_setup_info`;
CREATE TABLE `opening_setup_info` (
  `opening_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '开班ID',
  `system` varchar(20) NOT NULL COMMENT '所属体系',
  `bu` varchar(20) NOT NULL COMMENT '所属产品线',
  `series` varchar(20) NOT NULL COMMENT '所属系列',
  `training_name` varchar(20) NOT NULL COMMENT '培训名称',
  `opening_name` varchar(20) NOT NULL COMMENT '开班系列名称',
  `lecturer` varchar(20) NOT NULL COMMENT '讲师',
  `training_time` datetime DEFAULT NULL COMMENT '培训时间',
  `training_type` varchar(20) DEFAULT NULL COMMENT '所属类别,公开课/定制/受限',
  `language` tinyint(1) DEFAULT NULL COMMENT '语言,1为中文,0为英文',
  `class_type` varchar(20) DEFAULT NULL COMMENT '开班形式,面授/在线',
  `address` varchar(20) DEFAULT NULL COMMENT '开班地点',
  `download` varchar(255) DEFAULT NULL COMMENT '课件下载地址',
  `online` varchar(255) DEFAULT NULL COMMENT '在线培训地址',
  `galleryful` int(11) DEFAULT NULL COMMENT '可容纳人数',
  `isexam` tinyint(1) DEFAULT NULL COMMENT '是否考试,1为需要,0为不需要',
  `publisher` varchar(20) DEFAULT NULL COMMENT '发布人',
  `telephone` varchar(20) DEFAULT NULL COMMENT '咨询电话',
  `enroll_method` tinyint(1) DEFAULT NULL COMMENT '报名方式,1为线上,0为线下',
  `notice` varchar(255) DEFAULT NULL COMMENT '报名须知',
  PRIMARY KEY (`opening_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of opening_setup_info
-- ----------------------------
INSERT INTO `opening_setup_info` VALUES ('14', '管理', '2012', '百人计划', '百人安全意识培训', '百人安全意识培训', '罗帆', '2018-12-10 17:47:16', '公开课', '1', '面授', '4E01', '', '', '80', '1', '罗帆', '12345678901', '1', 'all');
INSERT INTO `opening_setup_info` VALUES ('15', '管理', '无线网络', '质量安全', '质量安全培训', '质量安全培训第一期', 'zhaoye', '2018-12-10 21:19:23', '公开课', '1', '在线', null, 'www.baidu.com', 'www.baidu.com', '80', '1', 'lixiang', '1242526733', '1', '非一般的体验');
