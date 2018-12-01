-- 创建项目组配置信息表

CREATE TABLE `hrbp_projectteam` (
  `team_id` varchar(20) NOT NULL,
  `team_name` varchar(20) DEFAULT NULL,
  `staff_id` varchar(20) NOT NULL,
  `staff_name` varchar(20) DEFAULT NULL,
  `dep_tree` varchar(255) DEFAULT NULL,
  `parent_id` varchar(20) DEFAULT NULL,
  `level` int(255) DEFAULT NULL,
  `position` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8




-- 创建出入项目组记录表
CREATE TABLE `hrbp_projectteamrecord` (
  `team_id` varchar(20) NOT NULL,
  `team_name` varchar(20) DEFAULT NULL,
  `staff_id` varchar(20) NOT NULL,
  `staff_name` varchar(20) DEFAULT NULL,
  `dep_tree` varchar(255) DEFAULT NULL,
  `parent_id` varchar(20) DEFAULT NULL,
  `level` int(255) DEFAULT NULL,
  `position` varchar(20) DEFAULT NULL,
  `change_time` datetime DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8

