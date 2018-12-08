/*
Navicat MySQL Data Transfer

Source Server         : my
Source Server Version : 50629
Source Host           : localhost:3306
Source Database       : test_zjm

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2018-11-29 12:06:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bu_du_pdu
-- ----------------------------
DROP TABLE IF EXISTS `bu_du_pdu`;
CREATE TABLE `bu_du_pdu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bu` varchar(64) DEFAULT NULL COMMENT 'BU，如2012',
  `du` varchar(128) DEFAULT NULL COMMENT 'DU，如中央软件院（2012-中央软件院）',
  `pdu` varchar(128) DEFAULT NULL COMMENT 'PDU，eg：GENEX产品部（2012-中央软件院-GENEX产品部）',
  `region` varchar(64) DEFAULT NULL COMMENT '地域，如西安、南京、深圳、成都',
  `deliveryDep` varchar(128) DEFAULT NULL COMMENT '交付部，如交付二部',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;

