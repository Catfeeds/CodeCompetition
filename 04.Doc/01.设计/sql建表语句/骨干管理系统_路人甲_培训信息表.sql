DROP TABLE IF EXISTS `training_info`;

CREATE TABLE `training_info` (
  `training_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '培训ID',
  `training_name` varchar(20) NOT NULL COMMENT '培训名称',
  `series` varchar(20) DEFAULT NULL COMMENT '所属系列',
  `sort` varchar(20) DEFAULT NULL COMMENT '所属体系',
  `type` varchar(20) DEFAULT NULL COMMENT '所属类别',
  `class_type` varchar(20) DEFAULT NULL COMMENT '课程类别',
  `bu` varchar(20) DEFAULT NULL COMMENT '所属产品线',
  `creator_id` varchar(20) DEFAULT NULL COMMENT '创建人工号',
  `creator_name` varchar(20) DEFAULT NULL COMMENT '创建人名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater_id` varchar(20) DEFAULT NULL COMMENT '最后更新人工号',
  `updater_name` varchar(20) DEFAULT NULL COMMENT '最后更新人名称',
  `training_des` varchar(255) DEFAULT NULL COMMENT '培训简介',
  `training_duration` varchar(20) DEFAULT NULL COMMENT '培训时长',
  `status` int(11) DEFAULT '0' COMMENT '是否删除  0表示正常  1表示已删除',
  PRIMARY KEY (`training_id`,`training_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8

