SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `mms_examination_rule_item`
-- ----------------------------
DROP TABLE IF EXISTS `mms_examination_rule_item`;
CREATE TABLE `mms_examination_rule_item` (
  `ruleId` int(8) NOT NULL COMMENT '规则Id',
  `itemId` int(8) NOT NULL COMMENT '规则项Id',
  `itemType` int(1) NOT NULL COMMENT '规则项类型(0:培训;1:事务)',
  `itemName` varchar(32) DEFAULT NULL COMMENT '规则项名称',
  PRIMARY KEY (`ruleId`,`itemId`,`itemType`),
  CONSTRAINT `ruleId` FOREIGN KEY (`ruleId`) REFERENCES `mms_examination_rule` (`ruleId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;