-- ----------------------------
-- 1、员工基本信息表
-- ----------------------------
drop table if exists sys_staffBaseInfo;
create table sys_staffBaseInfo (
  staffISST_num 	 integer(11) 	not null 			       comment '工号',
  hw_number	 		 varchar(32)	default null 			   comment '华为工号',
  staff_name 		 varchar(32) 	not null 				   comment '姓名',
  staffisst_account  varchar(32) 	not null 				   comment '软通域账号',
  staff_state 		 integer(8) 	default 1    			   comment '状态（1在职，2转出，3,离职）',
  create_time        datetime       default null               comment '创建时间',
  update_time        datetime       default null               comment '更新时间',
  into_date          varchar(32)    default null               comment '入项时间',
  out_date           varchar(32)    default null               comment '出项时间',
  primary key (staffISST_num)
) engine=innodb auto_increment=100 default charset=utf8 comment = '员工基本信息表';


drop table if exists sys_user;
create table sys_user (
  user_id 			int(11) 		not null auto_increment    comment '用户ID',
  dept_id 			int(11) 		default null			   comment '部门ID',
  login_name 		varchar(30) 	not null 				   comment '登录账号',
  user_name 		varchar(30) 	not null 				   comment '用户昵称',
  user_type 		varchar(2) 	    default '00' 		       comment '用户类型（00系统用户）',
  email  			varchar(50) 	default '' 				   comment '用户邮箱',
  phonenumber  		varchar(11) 	default '' 				   comment '手机号码',
  sex  		        char(1) 	    default '0' 			   comment '用户性别（0男 1女 2未知）',
  avatar            varchar(100) 	default '' 				   comment '头像路径',
  password 			varchar(50) 	default '' 				   comment '密码',
  salt 				varchar(20) 	default '' 				   comment '盐加密',
  status 			char(1) 		default '0' 			   comment '帐号状态（0正常 1停用）',
  del_flag			char(1) 		default '0' 			   comment '删除标志（0代表存在 2代表删除）',
  login_ip          varchar(20)     default ''                 comment '最后登陆IP',
  login_date        datetime                                   comment '最后登陆时间',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark 		    varchar(500) 	default '' 				   comment '备注',
  primary key (user_id)
) engine=innodb auto_increment=100 default charset=utf8 comment = '用户信息表';




-- ----------------------------
-- 2、角色信息表
-- ----------------------------
drop table if exists sys_role;
create table sys_role (
  role_id 			integer(11) 	not null auto_increment    comment '角色ID',
  role_name 		varchar(30) 	not null 				   comment '角色名称',
  role_key 		    varchar(100) 	not null 				   comment '角色权限字符串',
  role_sort         integer(4)      not null                   comment '显示顺序',
  data_scope        integer(4) 	    default 1				   comment '数据范围（1：全部数据权限 2：自定数据权限）',
  status 			integer(4) 		default 1 			       comment '角色状态（0停用 1正常）',
  create_by         varchar(64)     default null               comment '创建者',
  create_time 		datetime        default null               comment '创建时间',
  update_by 		varchar(64) 	default null	           comment '更新者',
  update_time 		datetime        default null               comment '更新时间',
  remark 			varchar(500) 	default null 			   comment '备注',
  primary key (role_id)
) engine=innodb auto_increment=100 default charset=utf8 comment = '角色信息表';


-- ----------------------------
-- 3、用户和角色关联表  用户N-1角色
-- ----------------------------
drop table if exists sys_staff_role;
create table sys_staff_role (
  staffISST_num 	integer(11) not null comment '用户ID',
  role_id 	        integer(11) not null comment '角色ID',
  primary key(staffISST_num, role_id)
) engine=innodb default charset=utf8 comment = '用户和角色关联表';


-- ----------------------------
-- 4、部门信息表
-- ----------------------------
drop table if exists sys_dept;
create table sys_dept (
  dept_id 			integer(11)     not null auto_increment    comment '部门id',
  parent_id 		integer(11)     default 0 			       comment '父部门id',
  dept_path 		varchar(30) 	default NULL 			   comment '部门路径',
  dept_name 		varchar(30) 	default NULL 			   comment '部门编号',
  dept_sort 		integer(4) 		default 0 			       comment '显示顺序',
  dept_status 		integer(4) 		default 1        		   comment '部门状态（0停用 1正常）',
  create_by         varchar(32)     default NULL               comment '创建者',
  create_time 	    datetime        default null               comment '创建时间',
  update_by         varchar(32)     default NULL               comment '更新者',
  update_time       datetime        default null               comment '更新时间',
  primary key (dept_id)
) engine=innodb auto_increment=200 default charset=utf8 comment = '部门信息表';


-- ----------------------------
-- 5、角色和部门关联表  角色1-N部门
-- ----------------------------
drop table if exists sys_role_dept;
create table sys_role_dept (
  role_id 	integer(11) not null comment '角色ID',
  dept_id 	integer(11) not null comment '部门ID',
  primary key(role_id, dept_id)
) engine=innodb default charset=utf8 comment = '角色和部门关联表';


-- ----------------------------
-- 6、用户与部门关联表  用户1-N部门
-- ----------------------------
drop table if exists sys_dept_staff;
create table sys_dept_staff
(
	staffISST_num integer(11) not null comment '用户ID',
	dept_id       integer(11) not null comment '部门ID',
	primary key (staffISST_num, dept_id)
) engine=innodb default charset=utf8 comment = '用户与部门关联表';

-- ----------------------------
-- 7、菜单权限表
-- ----------------------------
drop table if exists sys_menu;
create table sys_menu (
  menu_id 			integer(16) 	not null auto_increment    comment '菜单ID',
  menu_name 		varchar(50) 	not null 				   comment '菜单名称',
  menu_enname 		varchar(50) 	not null 				   comment '菜单英文名称',
  parent_id 		integer(16) 	default 0 			       comment '父菜单ID',
  order_num 		integer(8) 		default 0 			       comment '显示顺序',
  menuUrl 			varchar(200) 	default null			   comment '请求地址',
  menu_type 		varchar(16) 	default null 			   comment '菜单类型（M目录 C菜单 F按钮）',
  visible 			integer(8) 		default 1 				   comment '菜单状态（0隐藏 1显示）',
  perms 			varchar(100) 	default null 			   comment '权限标识',
  icon 				varchar(100) 	default null 			   comment '菜单图标',
  create_by         varchar(64)     default null               comment '创建者',
  create_time 		datetime        default null               comment '创建时间',
  update_by 		varchar(64) 	default null			   comment '更新者',
  update_time 		datetime        default null               comment '更新时间',
  remark 			varchar(500) 	default null 			   comment '备注',
  primary key (menu_id)
) engine=innodb auto_increment=2000 default charset=utf8 comment = '菜单权限表';



-- ----------------------------
-- 8、角色和菜单关联表  角色1-N菜单
-- ----------------------------
drop table if exists sys_role_menu;
create table sys_role_menu (
  role_id 	integer(11) not null comment '角色ID',
  menu_id 	integer(11) not null comment '菜单ID',
  primary key(role_id, menu_id)
) engine=innodb default charset=utf8 comment = '角色和菜单关联表';


-- ----------------------------
-- 9、字典类型表
-- ----------------------------
drop table if exists sys_dict_type;
create table sys_dict_type
(
	dict_id          integer(11) 	 not null auto_increment    comment '字典主键',
	dict_name        varchar(100)    default null               comment '字典名称',
	dict_type        varchar(100)    default null               comment '字典类型',
    status 			 integer(8) 	 default 1			        comment '状态（0停用 1正常）',
    create_by        varchar(64)     default null               comment '创建者',
    create_time      datetime        default null               comment '创建时间',
    update_by        varchar(64) 	 default null		        comment '更新者',
	update_time      datetime        default null               comment '更新时间',
    remark 	         varchar(500) 	 default null 				comment '备注',
	primary key (dict_id),
	unique (dict_type)
) engine=innodb auto_increment=100 default charset=utf8 comment = '字典类型表';


-- ----------------------------
-- 10、字典数据表
-- ----------------------------
drop table if exists sys_dict_data;
create table sys_dict_data
(
	dict_code        integer(16) 	 not null auto_increment    comment '字典编码',
	dict_sort        integer(16)     default 0                  comment '字典排序',
	dict_label       varchar(100)    default null               comment '字典标签',
	dict_num         varchar(100)    default null               comment '字典编码',
	dict_value       varchar(100)    default null               comment '字典键值',
	dict_type        varchar(100)    default null               comment '字典类型',
	is_default       varchar(16)     default 'N'                comment '是否默认（Y是 N否）',
	status 			 integer(8)      default 1			        comment '状态（0停用 1正常）',
	create_by        varchar(64)     default null               comment '创建者',
	create_time      datetime        default null               comment '创建时间',
	update_by        varchar(64) 	 default null	            comment '更新者',
	update_time      datetime        default null               comment '更新时间',
    remark 	         varchar(500) 	 default null 				comment '备注',
	primary key (dict_code)
) engine=innodb auto_increment=100 default charset=utf8 comment = '字典数据表';