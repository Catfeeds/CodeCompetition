/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50560
Source Host           : localhost:3306
Source Database       : itsys

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2018-12-03 17:10:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hrbp_examination_affair
-- ----------------------------
DROP TABLE IF EXISTS `hrbp_examination_affair`;
CREATE TABLE `hrbp_examination_affair` (
  `affair_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '考核事务ID',
  `affair_name` varchar(16) NOT NULL COMMENT '事务名称',
  `system` varchar(16) NOT NULL COMMENT '所属体系',
  `series` varchar(16) NOT NULL COMMENT '所属系列',
  `du` varchar(16) NOT NULL COMMENT '所属产业线',
  `create_by` varchar(10) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(10) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`affair_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hrbp_examination_affair
-- ----------------------------
INSERT INTO `hrbp_examination_affair` VALUES ('2', 'header评价', '管理', 'sep计划', '2012实验室', 'lf', 'lf', '2018-12-03 10:46:42');
INSERT INTO `hrbp_examination_affair` VALUES ('4', '考核事务', '管理', '网络安全', '2012实验室', '晓光', 'luofan', '2018-12-03 16:40:44');
