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

-- ----------------------------
-- Records of bu_du_pdu
-- ----------------------------
INSERT INTO `bu_du_pdu` VALUES ('1', '2012', '中央软件院', '业务支撑工具部', '西安', '中软院交付三部');
INSERT INTO `bu_du_pdu` VALUES ('2', '2012', '中央软件院', '业务支撑工具部', '深圳', '中软院交付二部');
INSERT INTO `bu_du_pdu` VALUES ('3', '2012', '中央软件院', '欧拉部', '杭州', '中软院交付六部');
INSERT INTO `bu_du_pdu` VALUES ('4', '2012', '中央软件院', 'GENEX产品部', '西安', '中软院交付五部');
INSERT INTO `bu_du_pdu` VALUES ('5', '2012', '中央软件院', 'SEQ产品部', '西安', '中软院交付一部');
INSERT INTO `bu_du_pdu` VALUES ('6', '2012', '中央软件院', 'SEP产品部', '西安', '中软院交付一部');
INSERT INTO `bu_du_pdu` VALUES ('7', '2012', '中央软件院', '黎曼实验室', '西安', '中软院交付五部');
INSERT INTO `bu_du_pdu` VALUES ('8', '2012', '中央软件院', '罗素部', '西安', '中软院交付一部');
INSERT INTO `bu_du_pdu` VALUES ('9', '2012', '中央软件院', '高斯部', '西安', '中软院交付一部');
INSERT INTO `bu_du_pdu` VALUES ('10', '2012', '研发能力中心', 'UCD中心', '南京', '研发能力中心交付五部');
INSERT INTO `bu_du_pdu` VALUES ('11', '2012', '研发能力中心', 'UCD中心', '深圳', '研发能力中心交付五部');
INSERT INTO `bu_du_pdu` VALUES ('12', '2012', '研发能力中心', '公司全球化部', '深圳', '研发能力中心交付二部');
INSERT INTO `bu_du_pdu` VALUES ('13', '2012', '研发能力中心', '公司全球化部', '东莞', '研发能力中心交付二部');
INSERT INTO `bu_du_pdu` VALUES ('14', '2012', '研发能力中心', '公司资料部', '深圳', '研发能力中心交付二部');
INSERT INTO `bu_du_pdu` VALUES ('15', '2012', '研发能力中心', '公司资料部', '西安', '研发能力中心交付二部');
INSERT INTO `bu_du_pdu` VALUES ('16', '2012', '研发能力中心', '测试与装备部', '深圳', '研发能力中心交付一部');
INSERT INTO `bu_du_pdu` VALUES ('17', '2012', '研发能力中心', '测试与装备部', '东莞', '研发能力中心交付一部');
INSERT INTO `bu_du_pdu` VALUES ('18', '2012', '研发能力中心', '测试与装备部', '成都', '研发能力中心交付一部');
INSERT INTO `bu_du_pdu` VALUES ('19', '2012', '研发能力中心', '测试与装备部', '西安', '研发能力中心交付一部');
INSERT INTO `bu_du_pdu` VALUES ('20', '2012', '研发能力中心', '安全工具开发部', '杭州', '研发能力中心交付三部');
INSERT INTO `bu_du_pdu` VALUES ('21', '2012', '研发能力中心', '安全工具开发部', '西安', '研发能力中心交付三部');
INSERT INTO `bu_du_pdu` VALUES ('22', '2012', '研发能力中心', '应用场景实验室', '深圳', '研发能力中心交付二部');
INSERT INTO `bu_du_pdu` VALUES ('23', '2012', '中央研究院', '数据中心实验室', null, null);
INSERT INTO `bu_du_pdu` VALUES ('24', '2012', '中央硬件工程院', '全球认证检测中心', '深圳', '研发能力中心交付二部');
INSERT INTO `bu_du_pdu` VALUES ('25', '2012', '中央硬件工程院', '加速器集成与验证部', '西安', '中软院交付三部');
INSERT INTO `bu_du_pdu` VALUES ('26', '2012', '中央软件院', '行业使能平台工具开发部', '深圳', '中软院交付七部');
INSERT INTO `bu_du_pdu` VALUES ('27', '2012', '中央软件院', '架设部', '深圳', '中软院交付七部');
INSERT INTO `bu_du_pdu` VALUES ('28', '2012', '中央软件院', '服务工具解决方案部', '西安', '中软院交付一部');
INSERT INTO `bu_du_pdu` VALUES ('29', '2012', '中央软件院', 'MVOSS技术开发部', '苏州', '中软院交付五部');
INSERT INTO `bu_du_pdu` VALUES ('30', '2012', '研发能力中心', '研发工具与测试平台部', '武汉', '研发能力中心交付一部');
INSERT INTO `bu_du_pdu` VALUES ('31', '2012', '研发能力中心', '研发工具与测试平台部', '深圳', '研发能力中心交付一部');
INSERT INTO `bu_du_pdu` VALUES ('32', '2012', '中央软件院', '欧拉部', '深圳', '中软院交付七部');
INSERT INTO `bu_du_pdu` VALUES ('33', '2012', '研发能力中心', '云安全实验室', '南京', '研发能力中心交付三部');
INSERT INTO `bu_du_pdu` VALUES ('34', '流程与IT', 'AI使能部', '行销工具开发部', '深圳', null);
INSERT INTO `bu_du_pdu` VALUES ('35', '2012', '中央硬件工程院', '处理器技术部', '南京', '中软院交付六部');
INSERT INTO `bu_du_pdu` VALUES ('36', '2012', '中央硬件工程院', '处理器技术部', '杭州', '中软院交付六部');
INSERT INTO `bu_du_pdu` VALUES ('37', '2012', '中央硬件工程院', '处理器技术部', '深圳', '中软院交付二部');
INSERT INTO `bu_du_pdu` VALUES ('38', '2012', '中央硬件工程院', '处理器技术部', '西安', '中软院交付三部');
INSERT INTO `bu_du_pdu` VALUES ('39', '2012', '中央硬件工程院', '结构平台技术部', '深圳', '研发能力中心交付二部');
INSERT INTO `bu_du_pdu` VALUES ('40', '2012', '中央硬件工程院', '整机工程部', '深圳', '研发能力中心交付二部');
INSERT INTO `bu_du_pdu` VALUES ('41', '2012', '中央硬件工程院', '整机装备部', '杭州', '中软院交付六部');
INSERT INTO `bu_du_pdu` VALUES ('42', '2012', '中央硬件工程院', '中央硬件部', null, null);
INSERT INTO `bu_du_pdu` VALUES ('43', '2012', '中央硬件工程院', '加速器技术部', '杭州', '中软院交付六部');
INSERT INTO `bu_du_pdu` VALUES ('44', '2012', '研发能力中心', '研发工具与测试平台部', '深圳', '研发能力中心交付一部');
INSERT INTO `bu_du_pdu` VALUES ('45', '2012', '中央研究院', '孵化中心', '西安', '中软院交付三部');
INSERT INTO `bu_du_pdu` VALUES ('46', '2012', '中央研究院', '诺亚方舟实验室', '深圳', '研发能力中心交付二部');
INSERT INTO `bu_du_pdu` VALUES ('47', '2012', '中央研究院', '网络技术实验室', '南京', '中软院交付六部');
INSERT INTO `bu_du_pdu` VALUES ('48', '2012', '中央研究院', '车联网实验室', '杭州', '中软院交付六部');
INSERT INTO `bu_du_pdu` VALUES ('49', '2012', '中央研究院', '基础开源软件技术开发部', '深圳', '中软院交付七部');
INSERT INTO `bu_du_pdu` VALUES ('50', '2012', '中央软件院', '分布式与并行软件实验室', '杭州', '中软院交付六部');
INSERT INTO `bu_du_pdu` VALUES ('51', '2012', '中央硬件工程院', '整机工程部', '杭州', '中软院交付六部');
INSERT INTO `bu_du_pdu` VALUES ('52', '2012', '中央研究院', '孵化中心', '杭州', '中软院交付六部');
INSERT INTO `bu_du_pdu` VALUES ('53', '2012', '中央研究院', '诺亚方舟实验室', '杭州', '中软院交付六部');
INSERT INTO `bu_du_pdu` VALUES ('54', '2012', '中央研究院', '车联网实验室', '南京', '中软院交付六部');
INSERT INTO `bu_du_pdu` VALUES ('55', '2012', '中央硬件工程院', '媒体工程部', '南京', '中软院交付六部');
INSERT INTO `bu_du_pdu` VALUES ('56', '2012', '中央硬件工程院', '媒体工程部', '杭州', '中软院交付六部');
INSERT INTO `bu_du_pdu` VALUES ('57', '2012', '研发能力中心', '公司可服务性部', '西安', '研发能力中心交付二部');
INSERT INTO `bu_du_pdu` VALUES ('58', '2012', '研发能力中心', '研发工具与测试平台部', '武汉', '研发能力中心交付一部');
INSERT INTO `bu_du_pdu` VALUES ('59', '2012', '中央软件院', '大数据产品部', '南京', '中软院交付六部');
INSERT INTO `bu_du_pdu` VALUES ('60', '2012', '中央软件院', '网络技术实验室', '南京', null);
INSERT INTO `bu_du_pdu` VALUES ('61', '2012', '研发能力中心', '公司可靠性部', '成都', '研发能力中心交付二部');
INSERT INTO `bu_du_pdu` VALUES ('62', '2012', '研发能力中心', '研发工具与测试平台部', '深圳', '研发能力中心交付一部');
INSERT INTO `bu_du_pdu` VALUES ('63', '2012', '研发能力中心', '测试与装备产品一部', '深圳', '研发能力中心交付一部');
INSERT INTO `bu_du_pdu` VALUES ('64', '2012', '研发能力中心', '测试与装备产品二部', '深圳', '研发能力中心交付一部');
INSERT INTO `bu_du_pdu` VALUES ('65', '2012', '研发能力中心', '硬件装备与平台部', '深圳', '研发能力中心交付一部');
INSERT INTO `bu_du_pdu` VALUES ('66', '2012', '中央软件院', 'ICT业务设计与运维产品部', '西安', '中软院交付一部');
INSERT INTO `bu_du_pdu` VALUES ('67', '2012', '中央软件院', 'ICT业务设计与运维产品部', '深圳', '中软院交付一部');
INSERT INTO `bu_du_pdu` VALUES ('68', '2012', '中央软件院', '大数据产品部', '南京', '中软院交付一部');
INSERT INTO `bu_du_pdu` VALUES ('69', '2012', '中央软件院', '编译器与编程语言实验室', '西安', '中软院交付一部');
INSERT INTO `bu_du_pdu` VALUES ('70', '2012', '研发能力中心', '测试与装备产品一部', '武汉', '研发能力中心交付一部');
INSERT INTO `bu_du_pdu` VALUES ('71', '2012', '研发能力中心', '硬件装备与平台部', '东莞', '研发能力中心交付一部');
INSERT INTO `bu_du_pdu` VALUES ('72', '2012', '研发能力中心', '测试与装备产品二部', '东莞', '研发能力中心交付一部');
INSERT INTO `bu_du_pdu` VALUES ('73', '2012', '研发能力中心', '测试与装备产品一部', '东莞', '研发能力中心交付一部');
INSERT INTO `bu_du_pdu` VALUES ('74', '2012', '研发能力中心', '研发工具与测试平台部', '东莞', '研发能力中心交付一部');
INSERT INTO `bu_du_pdu` VALUES ('75', '2012', '研发能力中心', '研发工具与测试平台部', '西安', '研发能力中心交付一部');
INSERT INTO `bu_du_pdu` VALUES ('76', '2012', '研发能力中心', '研发工具与测试平台部', '成都', '研发能力中心交付一部');
INSERT INTO `bu_du_pdu` VALUES ('77', '2012', '研发能力中心', '研发工具与测试平台部', '武汉', '研发能力中心交付一部');
INSERT INTO `bu_du_pdu` VALUES ('78', '2012', '研发能力中心', '安全工具开发部', '南京', '研发能力中心交付三部');
INSERT INTO `bu_du_pdu` VALUES ('79', '2012', '中央软件院', '基础开源软件技术开发部', '深圳', '中软院交付七部');
