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
  `data_scope` int(4) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限）',
  `status` int(4) DEFAULT '1' COMMENT '角色状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of hrbp_role
-- ----------------------------
INSERT INTO `hrbp_role` VALUES ('1', '管理员', 'admin', '1', '1', '1', 'admin', '2018-11-25 14:09:40', null, null, '系统设置', null);
INSERT INTO `hrbp_role` VALUES ('2', '骨干员工', 'main', '1', '2', '1', 'admin', '2018-11-25 14:09:40', null, null, '系统维护', null);
INSERT INTO `hrbp_role` VALUES ('3', '信息录入员', 'pm', '1', '3', '1', 'admin', '2018-11-25 14:09:40', null, null, '负责成绩导入', null);
INSERT INTO `hrbp_role` VALUES ('4', 'guess', 'guess', '1', '4', '0', 'admin', '2018-11-28 17:23:14', null, null, '查看报表', null);
