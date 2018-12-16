/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : hw_it

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-12-14 17:00:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `mms_personal_style`
-- ----------------------------
DROP TABLE IF EXISTS `mms_personal_style`;
CREATE TABLE `mms_personal_style` (
  `employeeID` varchar(16) NOT NULL COMMENT '员工工号',
  `food` varchar(100) DEFAULT NULL COMMENT '喜欢的食物',
  `game` varchar(200) DEFAULT NULL COMMENT '喜欢的游戏',
  `motion` varchar(200) DEFAULT NULL COMMENT '喜欢的运动',
  `book` varchar(200) DEFAULT NULL COMMENT '喜欢的书籍',
  `movie` varchar(200) DEFAULT NULL COMMENT '喜欢的电影',
  `music` varchar(200) DEFAULT NULL COMMENT '喜欢的音乐',
  `oneSentence` varchar(200) DEFAULT NULL COMMENT '喜欢的一句话',
  `dislike` varchar(200) DEFAULT NULL COMMENT '不喜欢的',
  `photosOfLife` varchar(200) DEFAULT NULL COMMENT '生活照照片路径',
  `documentPhoto` varchar(200) DEFAULT NULL COMMENT '证件照信息',
  PRIMARY KEY (`employeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mms_personal_style
-- ----------------------------
INSERT INTO `mms_personal_style` VALUES ('10008611', '西兰花', '英雄联盟', '跑步', '人性的弱点', '机械先驱', '一万次悲伤', '先问自己', '不守时', '/hrbp/', '/hrbp/');
INSERT INTO `mms_personal_style` VALUES ('100178', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string');
