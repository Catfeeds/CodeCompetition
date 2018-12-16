SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mms_special_role
-- ----------------------------
DROP TABLE IF EXISTS `mms_special_role`;
CREATE TABLE `mms_special_role` (
  `roleId` int(4) NOT NULL AUTO_INCREMENT COMMENT '角色Id',
  `roleName` varchar(8) DEFAULT NULL COMMENT '角色名称',
  `system` varchar(16) DEFAULT NULL COMMENT '所属体系',
  `creatorId` varchar(8) DEFAULT NULL COMMENT '创建者工号',
  `creatorName` varchar(32) DEFAULT NULL COMMENT '创建者姓名',
  `createTime` date DEFAULT NULL COMMENT '创建时间',
  `updateStaffId` varchar(8) DEFAULT NULL COMMENT '更新人员工号',
  `updateStaffName` varchar(32) DEFAULT NULL COMMENT '更新人员姓名',
  `updateTime` date DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`roleId`),
  KEY `roleNameSearch` (`roleName`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
