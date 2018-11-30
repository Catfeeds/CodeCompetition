/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50560
Source Host           : localhost:3306
Source Database       : itsys

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2018-11-29 17:50:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hrbp_role
-- ----------------------------
DROP TABLE IF EXISTS `hrbp_role`;
CREATE TABLE `hrbp_role` (
  `role_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) DEFAULT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) DEFAULT NULL COMMENT '显示顺序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of hrbp_role
-- ----------------------------
INSERT INTO `hrbp_role` VALUES ('1', '管理员', 'admin', '1', '2018-11-25 14:09:40', '系统设置', null);
INSERT INTO `hrbp_role` VALUES ('2', '骨干员工', 'main', '2', '2018-11-25 14:09:40', '系统维护', null);
INSERT INTO `hrbp_role` VALUES ('3', '信息录入员', 'pm', '3', '2018-11-25 14:09:40', '负责成绩导入', null);
INSERT INTO `hrbp_role` VALUES ('4', 'guess', 'guess', '4', '2018-11-28 17:23:14', '查看报表', null);
