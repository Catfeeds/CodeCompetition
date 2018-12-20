SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mms_relation_po
-- ----------------------------
DROP TABLE IF EXISTS `mms_relation_po`;
CREATE TABLE `mms_relation_po` (
  `nodeID` int(20) NOT NULL AUTO_INCREMENT,
  `nodeName` varchar(255) DEFAULT NULL,
  `nodePath` longtext,
  `nodeType` varchar(10) DEFAULT NULL,
  `isTeamNode` tinyint(1) DEFAULT NULL,
  `teamID` int(20) DEFAULT NULL,
  PRIMARY KEY (`nodeID`),
  KEY `mms_project_manage` (`teamID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;