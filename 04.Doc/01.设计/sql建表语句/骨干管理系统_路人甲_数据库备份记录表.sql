DROP TABLE IF EXISTS `mms_data_backup_info`;
CREATE TABLE `mms_data_backup_info` (
  `backup_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `employee_ID` varchar(16) DEFAULT NULL COMMENT '软通工号',
  `employee_name` varchar(16) DEFAULT NULL COMMENT '员工姓名',
  `backup_time` datetime DEFAULT NULL COMMENT '备份时间',
  `data_name` text DEFAULT NULL COMMENT '备份名称',
  `reason_remark` text DEFAULT NULL COMMENT '备份原由',
  PRIMARY KEY (backup_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;