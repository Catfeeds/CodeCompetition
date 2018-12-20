SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mms_project_manage
-- ----------------------------
DROP TABLE IF EXISTS `mms_project_manage`;
CREATE TABLE `mms_project_manage` (
  `projectID` int(20) NOT NULL AUTO_INCREMENT,
  `projectName` varchar(255) DEFAULT NULL,
  `pmID` varchar(255) DEFAULT NULL,
  `pmName` varchar(255) DEFAULT NULL,
  `BG` varchar(255) DEFAULT NULL,
  `BD` varchar(255) DEFAULT NULL,
  `BU` varchar(255) DEFAULT NULL,
  `CU` varchar(255) DEFAULT NULL,
  `SPM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`projectID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
