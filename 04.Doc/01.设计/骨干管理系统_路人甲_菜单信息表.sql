CREATE TABLE `isoftstone`.`hrbp_menu` (
	`menu_id` INT NOT NULL,
	`menu_name` VARCHAR ( 50 ) NOT NULL,
	`parent_id` INT,
	`order_num` INT,
	`note` VARCHAR ( 50 ),
PRIMARY KEY ( `menu_id` ) 
);

insert into `hrbp_menu` (`menu_id`, `note`, `menu_name`, `parent_id`, `order_num` ) values('1','骨干报表','BackboneReport','0','100');
insert into `hrbp_menu` (`menu_id`, `note`, `menu_name`, `parent_id`, `order_num` ) values('2','培训报表','TrainingReport','0','200');
insert into `hrbp_menu` (`menu_id`, `note`, `menu_name`, `parent_id`, `order_num` ) values('3','考试报表','ExaminationReport','0','300');
insert into `hrbp_menu` (`menu_id`, `note`, `menu_name`, `parent_id`, `order_num` ) values('4','系统设置','SystemSetting','0','400');
insert into `hrbp_menu` (`menu_id`, `note`, `menu_name`, `parent_id`, `order_num` ) values('101','整体情况','OverallSituation','1','101');
insert into `hrbp_menu` (`menu_id`, `note`, `menu_name`, `parent_id`, `order_num` ) values('102','人力模型','HumanModel','1','102');
insert into `hrbp_menu` (`menu_id`, `note`, `menu_name`, `parent_id`, `order_num` ) values('103','角色报表','RoleReport','1','103');
insert into `hrbp_menu` (`menu_id`, `note`, `menu_name`, `parent_id`, `order_num` ) values('401','项目组设置','ProjectTeam','4','401');
insert into `hrbp_menu` (`menu_id`, `note`, `menu_name`, `parent_id`, `order_num` ) values('402','课程设置','Courses','4','402');
insert into `hrbp_menu` (`menu_id`, `note`, `menu_name`, `parent_id`, `order_num` ) values('403','培训设置','Training','4','403');
insert into `hrbp_menu` (`menu_id`, `note`, `menu_name`, `parent_id`, `order_num` ) values('404','考试设置','Examination','4','404');
insert into `hrbp_menu` (`menu_id`, `note`, `menu_name`, `parent_id`, `order_num` ) values('405','导入数据','Import','4','405');
insert into `hrbp_menu` (`menu_id`, `note`, `menu_name`, `parent_id`, `order_num` ) values('406','权限设置','Permission','4','406');