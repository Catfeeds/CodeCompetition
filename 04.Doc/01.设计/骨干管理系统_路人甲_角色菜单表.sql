/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50560
Source Host           : localhost:3306
Source Database       : itsys

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2018-11-29 17:50:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hrbp_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `hrbp_role_menu`;
CREATE TABLE `hrbp_role_menu` (
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hrbp_role_menu
-- ----------------------------
INSERT INTO `hrbp_role_menu` VALUES ('1', '1');
INSERT INTO `hrbp_role_menu` VALUES ('1', '2');
INSERT INTO `hrbp_role_menu` VALUES ('1', '201');
INSERT INTO `hrbp_role_menu` VALUES ('1', '202');
INSERT INTO `hrbp_role_menu` VALUES ('1', '203');
INSERT INTO `hrbp_role_menu` VALUES ('1', '204');
INSERT INTO `hrbp_role_menu` VALUES ('1', '205');
INSERT INTO `hrbp_role_menu` VALUES ('1', '206');
INSERT INTO `hrbp_role_menu` VALUES ('1', '207');
INSERT INTO `hrbp_role_menu` VALUES ('1', '209');
INSERT INTO `hrbp_role_menu` VALUES ('1', '208');
INSERT INTO `hrbp_role_menu` VALUES ('1', '210');
INSERT INTO `hrbp_role_menu` VALUES ('2', '1');
INSERT INTO `hrbp_role_menu` VALUES ('2', '2');
INSERT INTO `hrbp_role_menu` VALUES ('2', '201');
INSERT INTO `hrbp_role_menu` VALUES ('2', '202');
INSERT INTO `hrbp_role_menu` VALUES ('2', '203');
INSERT INTO `hrbp_role_menu` VALUES ('2', '204');
INSERT INTO `hrbp_role_menu` VALUES ('2', '205');
INSERT INTO `hrbp_role_menu` VALUES ('2', '206');
INSERT INTO `hrbp_role_menu` VALUES ('2', '207');
INSERT INTO `hrbp_role_menu` VALUES ('2', '208');
INSERT INTO `hrbp_role_menu` VALUES ('3', '2');
INSERT INTO `hrbp_role_menu` VALUES ('3', '208');
INSERT INTO `hrbp_role_menu` VALUES ('4', '1');
