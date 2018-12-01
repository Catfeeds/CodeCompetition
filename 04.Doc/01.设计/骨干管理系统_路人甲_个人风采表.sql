/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : hw_it

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-11-30 16:16:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `personal_style`
-- ----------------------------
DROP TABLE IF EXISTS `personal_style`;
CREATE TABLE `personal_style` (
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
-- Records of personal_style
-- ----------------------------
INSERT INTO `personal_style` VALUES ('1', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', null);
INSERT INTO `personal_style` VALUES ('100178', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', 'string', null);
