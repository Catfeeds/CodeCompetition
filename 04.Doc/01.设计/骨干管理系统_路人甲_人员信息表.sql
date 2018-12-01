/*
Navicat MySQL Data Transfer

Source Server         : my
Source Server Version : 50629
Source Host           : localhost:3306
Source Database       : test_zjm

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2018-11-27 23:43:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_staff_info
-- ----------------------------
DROP TABLE IF EXISTS `base_staff_info`;
CREATE TABLE `base_staff_info` (
  `employeeID` varchar(16) NOT NULL COMMENT '软通工号',
  `employeeName` varchar(64) DEFAULT NULL COMMENT '员工姓名',
  `idNumber` varchar(64) DEFAULT NULL COMMENT '身份证号。敏感信息，需要加密',
  `isoftStatus` varchar(8) DEFAULT NULL COMMENT '软通状态。eg：在职',
  `personCategory` varchar(32) DEFAULT NULL COMMENT '人员类别。eg：实施人员/职能支撑人员',
  `ifBackBone` varchar(4) DEFAULT NULL COMMENT '是否骨干。eg:是/否',
  `ifOffShore` varchar(4) DEFAULT NULL COMMENT '是否在岸。eg:是/否',
  `workPlace` varchar(64) DEFAULT NULL COMMENT '办公实际场地，如环普3F',
  `workStationNumber` varchar(32) DEFAULT NULL COMMENT '工位编号',
  `cooperationMode` varchar(2) DEFAULT NULL COMMENT '合作模式。eg：FP/TM',
  `positionRole` varchar(16) DEFAULT NULL COMMENT '岗位角色。eg：QA/测试/开发/资料/其他',
  `skill` varchar(128) DEFAULT NULL COMMENT '技能。eg：C/C++、Java、Web前端、测试、其他',
  `sillDirection` varchar(64) DEFAULT NULL COMMENT '技能方向。eg：安卓、测试、前端、视频检测',
  `directLeaderName` varchar(64) DEFAULT NULL COMMENT '直接汇报上级姓名',
  `directLeaderId` varchar(16) DEFAULT NULL COMMENT '直接汇报上级工号',
  `employmentDate` varchar(20) DEFAULT NULL COMMENT '入职日期',
  `departureDate` varchar(20) DEFAULT NULL COMMENT '离职日期',
  `generalPosition` varchar(64) DEFAULT NULL COMMENT '通用软通职位。eg：主管工程师(四级)-HW',
  `currentPositionDate` varchar(20) DEFAULT NULL COMMENT '现职位生效时间。eg：2018/5/1 0:00:00',
  `costCenter` varchar(64) DEFAULT NULL COMMENT '成本中心',
  `costCenterLeaderName` varchar(64) DEFAULT NULL COMMENT '成本中心负责人',
  `bu` varchar(64) DEFAULT NULL COMMENT 'BU。 eg：2012',
  `du` varchar(64) DEFAULT NULL COMMENT 'DU。eg：中央软件院',
  `pdu` varchar(64) DEFAULT NULL COMMENT 'PDU。eg：GENEX',
  `workPlaceArea` varchar(64) DEFAULT NULL COMMENT '工作地文本。eg:是/否',
  `sex` varchar(4) DEFAULT NULL COMMENT '性别。eg：男/女',
  `graduationSchool` varchar(32) DEFAULT NULL COMMENT '毕业学校',
  `graduationTime` varchar(20) DEFAULT NULL COMMENT '毕业时间',
  `ifUnderGraduates` varchar(4) DEFAULT NULL COMMENT '是否统招。eg:是/否',
  `education` varchar(16) DEFAULT NULL COMMENT '学历',
  `degree` varchar(16) DEFAULT NULL COMMENT '学位',
  `if211` varchar(4) DEFAULT NULL COMMENT '是否211。eg:是/否',
  `ifGraduate` varchar(4) DEFAULT NULL COMMENT '是否应届入职',
  `graduationMajor` varchar(64) DEFAULT NULL COMMENT '专业名称',
  `ifAbvoveTwoInstitutions` varchar(4) DEFAULT NULL COMMENT '是否二本及以上。eg:是/否',
  `educationMode` varchar(16) DEFAULT NULL COMMENT '教育模式',
  `isoftEmai` varchar(64) DEFAULT NULL COMMENT '软通邮件名称，如zhangsan@isoftstone.com',
  `telephone` varchar(16) DEFAULT NULL COMMENT '手机号码',
  `domainUserName` varchar(32) DEFAULT NULL COMMENT '域用户名，isoftEmai字段中@前的字符串，如zhangsan@isoftstone.com的域用户名为zhangsan',
  `nation` varchar(32) DEFAULT NULL COMMENT '民族，如汉族',
  `nativePlace` varchar(64) DEFAULT NULL COMMENT '籍贯',
  `political` varchar(64) DEFAULT NULL COMMENT '政治面貌，如党员',
  `projectTeamId` int(11) DEFAULT NULL COMMENT '项目组ID。和项目群表关联',
  `currentStatus` varchar(2) DEFAULT '0' COMMENT '当前人员状态。eg：-1：已离职；0：在资源池；1：在项目中。 默认为在资源池，只有经过出入项操作才会改变状态',
  PRIMARY KEY (`employeeID`),
  KEY `employeeID` (`employeeID`),
  KEY `employeeName` (`employeeName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

