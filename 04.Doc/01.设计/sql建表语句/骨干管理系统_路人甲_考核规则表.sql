SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `mms_examination_rule`
-- ----------------------------
DROP TABLE IF EXISTS `mms_examination_rule`;
CREATE TABLE `mms_examination_rule` (
  `ruleId` int(8) NOT NULL AUTO_INCREMENT COMMENT '规则Id',
  `roleId` int(8) DEFAULT NULL COMMENT '角色Id',
  `roleLevel` varchar(16) DEFAULT NULL COMMENT '岗位级别',
  `creatorId` int(8) DEFAULT NULL COMMENT '创建者工号',
  `creatorName` varchar(32) DEFAULT NULL COMMENT '创建者姓名',
  `createTime` date DEFAULT NULL COMMENT '创建时间',
  `updateStaffId` int(8) DEFAULT NULL COMMENT '更新人员工号',
  `updateStaffName` varchar(32) DEFAULT NULL COMMENT '更新人员姓名',
  `updateTime` date DEFAULT NULL COMMENT '更新时间',
  `passScore` int(4) DEFAULT NULL COMMENT '通过分数',
  PRIMARY KEY (`ruleId`),
  KEY `ROLE_ID` (`roleId`),
  CONSTRAINT `ROLE_ID` FOREIGN KEY (`roleId`) REFERENCES `mms_special_role` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;