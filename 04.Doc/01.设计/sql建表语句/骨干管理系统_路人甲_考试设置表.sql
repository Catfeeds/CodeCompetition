/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50560
Source Host           : localhost:3306
Source Database       : hw_it

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2018-12-11 17:28:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for examination_setup_info
-- ----------------------------
DROP TABLE IF EXISTS `examination_setup_info`;
CREATE TABLE `examination_setup_info` (
  `examination_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '考试ID',
  `series` varchar(20) NOT NULL COMMENT '所属系列',
  `bu` varchar(20) NOT NULL COMMENT '所属产品线',
  `training_type` varchar(20) DEFAULT NULL COMMENT '课程类别,公开课/定制/受限',
  `training_name` varchar(20) NOT NULL COMMENT '培训名称',
  `language` tinyint(1) DEFAULT NULL COMMENT '语言,1为中文,0为英文',
  `time_type` varchar(20) DEFAULT NULL COMMENT '考试时间形式,时间点/时间段/截止日期/不限',
  `examination_start_time` datetime DEFAULT NULL COMMENT '考试开始时间',
  `examination_end_time` datetime DEFAULT NULL COMMENT '考试结束时间',
  `examination_type` varchar(20) DEFAULT NULL COMMENT '考试类型,开卷/闭卷',
  `examination_style` varchar(20) DEFAULT NULL COMMENT '考试形式,在线/线下',
  `address` varchar(20) DEFAULT NULL COMMENT '考试地址',
  `standard` varchar(20) DEFAULT NULL COMMENT '合格标准',
  `publisher` varchar(20) DEFAULT NULL COMMENT '发布人/接口人',
  `notice` varchar(255) DEFAULT NULL COMMENT '考试须知',
  `examination_status` tinyint(1) DEFAULT NULL COMMENT '考试状态,1为已开始,2为进行中,3已结束',
  PRIMARY KEY (`examination_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of examination_setup_info
-- ----------------------------
INSERT INTO `examination_setup_info` VALUES ('1', '新员工', '2012', '公开课', '新员工培训', '1', '时间点', '2018-12-11 14:00:00', null, '开卷', '在线', 'www.baidu.com', '80分及格', '罗帆', '欢迎大家参加新员工培训', '1');
INSERT INTO `examination_setup_info` VALUES ('2', '新员工', '2012', '公开课', '新员工培训第二期', '1', '时间点', '2018-12-11 14:00:00', null, '开卷', '在线', 'www.baidu.com', '80分及格', '罗帆', '欢迎大家参加新员工培训考试', '1');
INSERT INTO `examination_setup_info` VALUES ('3', '质量安全', '2012', '公开课', '质量安全培训', '1', '时间段', '2018-12-11 14:00:00', '2018-12-15 00:00:00', '开卷', '在线', 'www.baidu.com', '80分及格', '罗帆', '欢迎大家参加质量安全培训考试', '1');
INSERT INTO `examination_setup_info` VALUES ('4', '将军学院', '无线', '选修课', '将军学院人员意识培养', '1', '时间段', '2018-12-11 14:00:00', '2018-12-10 21:19:23', '开卷', '在线', 'www.baidu.com', '80分及格', '光少', '欢迎光少莅临将军学院', '1');
INSERT INTO `examination_setup_info` VALUES ('5', '将军学院', '核心网', '选修课', '百人实施计划', '1', '时间段', '2018-12-11 14:00:00', '2018-12-10 21:19:23', '闭卷', '线下', null, '85分及格', '光少', '欢迎光少莅临将军学院', '1');
INSERT INTO `examination_setup_info` VALUES ('7', '将军学院', '核心网', '公开课', '百人实施计划', '1', '时间点', '2018-12-10 21:19:23', null, '闭卷', '线下', null, '80分及格', '光少', '欢迎光少莅临将军学院', '1');
