SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mms_relation_info
-- ----------------------------
DROP TABLE IF EXISTS `mms_relation_info`;
CREATE TABLE `mms_relation_info` (
  `relationID` int(20) NOT NULL,
  `levelIndex` int(10) NOT NULL,
  `levelIndexID` varchar(255) DEFAULT NULL,
  `levelIndexName` varchar(255) DEFAULT NULL,
  `operationAllow` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`relationID`,`levelIndex`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
