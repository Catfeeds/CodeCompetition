DROP TABLE IF EXISTS `mms_project_team`;
DROP TABLE IF EXISTS `mms_project_team_record`;
DROP TABLE IF EXISTS `mms_po_role_info`;
DROP TABLE IF EXISTS `mms_po_staff_info`;
DROP TABLE IF EXISTS `mms_project_information`;
DROP TABLE IF EXISTS `mms_team_related_po`;


-- 项目组人员配置表
CREATE TABLE `mms_project_team` (
  `team_id` varchar(20) NOT NULL COMMENT '项目组ID',
  `team_name` varchar(20) DEFAULT NULL COMMENT '项目组名称',
  `staff_id` varchar(20) NOT NULL COMMENT '软通工号',
  `staff_name` varchar(20) DEFAULT NULL COMMENT '员工姓名',
  `dep_tree` varchar(255) DEFAULT NULL COMMENT '部门信息树',
  `parent_id` varchar(20) DEFAULT NULL COMMENT '上级工号',
  `level` int(255) DEFAULT NULL,
  `position` varchar(20) DEFAULT NULL COMMENT '组内角色'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

-- 出入项目组记录表
CREATE TABLE `mms_project_team_record` (
  `team_id` varchar(20) NOT NULL COMMENT '项目组ID',
  `team_name` varchar(20) DEFAULT NULL COMMENT '项目组名称',
  `staff_id` varchar(20) NOT NULL COMMENT '软通工号',
  `staff_name` varchar(20) DEFAULT NULL COMMENT '员工姓名',
  `dep_tree` varchar(255) DEFAULT NULL COMMENT '所属部门树',
  `parent_id` varchar(20) DEFAULT NULL COMMENT '上级工号',
  `level` int(255) DEFAULT NULL,
  `position` varchar(20) DEFAULT NULL COMMENT '组内角色',
  `change_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` varchar(20) DEFAULT NULL COMMENT '出入项目组状态：entry进入项目组  out离开项目组'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

-- 项目角色表
CREATE TABLE `mms_po_role_info` (
  `po_role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '项目角色ID',
  `po_role_name` varchar(20) NOT NULL COMMENT '项目角色名称',
  PRIMARY KEY (`po_role_id`,`po_role_name`),
  UNIQUE KEY `po_role_id` (`po_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8
;

-- 项目角色配置表
CREATE TABLE `mms_po_staff_info` (
  `project_id` varchar(30) NOT NULL COMMENT 'po号(合同ID)',
  `project_name` varchar(50) DEFAULT NULL COMMENT '合同名称',
  `staff_id` varchar(30) NOT NULL COMMENT '软通工号',
  `team_id` varchar(30) NOT NULL COMMENT '项目组ID',
  `po_role_id` varchar(30) DEFAULT NULL COMMENT '项目角色ID',
  `enter_time` datetime DEFAULT NULL COMMENT '授予角色时间',
  PRIMARY KEY (`project_id`,`staff_id`,`team_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

-- 合同信息表
CREATE TABLE `mms_project_information` (
  `projectId` varchar(20) NOT NULL COMMENT 'PO号(合同ID)',
  `projectName` varchar(50) DEFAULT NULL COMMENT '合同名称',
  `startTime` date DEFAULT NULL COMMENT '立项时间',
  `endTime` date DEFAULT NULL COMMENT '结项时间',
  `bu` varchar(20) DEFAULT NULL,
  `du` varchar(20) DEFAULT NULL,
  `pdu` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`projectId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

-- 项目组关联合同表
CREATE TABLE `mms_team_related_po` (
  `team_id` VARCHAR(30) NOT NULL COMMENT '项目组ID',
  `project_id` VARCHAR(30) NOT NULL COMMENT 'PO号(合同ID)'
) ENGINE=INNODB DEFAULT CHARSET=utf8
;


