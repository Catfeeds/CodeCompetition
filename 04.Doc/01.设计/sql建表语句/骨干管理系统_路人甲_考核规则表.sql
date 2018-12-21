SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mms_examination_rule
-- ----------------------------
DROP TABLE IF EXISTS `mms_examination_rule`;
CREATE TABLE `mms_examination_rule` (
  `ruleId` int(8) NOT NULL AUTO_INCREMENT COMMENT '规则Id',
  `roleId` int(8) DEFAULT NULL COMMENT '角色Id',
  `system` varchar(16) DEFAULT NULL COMMENT '体系',
  `roleLevel` varchar(16) DEFAULT NULL COMMENT '岗位级别',
  `creatorId` int(8) DEFAULT NULL COMMENT '创建者工号',
  `creatorName` varchar(32) DEFAULT NULL COMMENT '创建者姓名',
  `createTime` date DEFAULT NULL COMMENT '创建时间',
  `updateStaffId` int(8) DEFAULT NULL COMMENT '更新人员工号',
  `updateStaffName` varchar(32) DEFAULT NULL COMMENT '更新人员姓名',
  `updateTime` date DEFAULT NULL COMMENT '更新时间',
  `passScore` int(4) DEFAULT NULL COMMENT '通过分数',
  `passRule` int(1) DEFAULT '0' COMMENT '0:> 1:≥ 2:< 3:≤',
  `excellentScore` int(4) DEFAULT NULL,
  `excellentRule` int(1) DEFAULT '0' COMMENT '0:> 1:≥ 2:< 3:≤',
  PRIMARY KEY (`ruleId`),
  KEY `ROLE_ID` (`roleId`),
  CONSTRAINT `ROLE_ID` FOREIGN KEY (`roleId`) REFERENCES `mms_special_role` (`roleId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
