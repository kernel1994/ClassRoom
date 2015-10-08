开发lib
	c3p0 mchange	数据连接池
	dbutils	数据持久化
	jstl standard 
	mysql 数据库
	
lib下载链接：http://pan.baidu.com/s/1jGztxIm 密码：g1jq

数据库创建

create database classroom character set utf8 collate utf8_general_ci;
use classroom;

----创建用户信息类的表
create table user(
	id varchar(40) primary key,
	nick varchar(100) not null,
	password varchar(40) not null,
	name varchar(100) not null,
	email varchar(100) not null,
	gender varchar(10),
	bitthday date,
	address varchar(255),
	description varchar(255)
);

create table role
(
	id varchar(40) primary key,
	name varchar(100) not null unique,
	description varchar(255)
);

--用户user和角色role关系，可能出现用户需要找到具体角色，或者出现找出一个角色下的所有用户
create table user_role
(
	user_id varchar(40),
	role_id varchar(40),
	primary key(user_id,role_id),
	constraint user_id_FK foreign key(user_id) references user(id),
	constraint role_id_FK foreign key(role_id) references role(id)
);

--权限属于某个角色role,而不属于个人person
create table privilege
(
	id varchar(40) primary key,
	name varchar(100) not null unique,
	description varchar(255),
	role_id varchar(40),
	constraint role_id_FK2 foreign key(role_id) references role(id)
);

对应代码
class User{
	private String id;
	private String nick;
	private String email;
	private String password;
	private String name;
	private String gender;
	private Date bitthday;
	private String address;
	private String description;
	private Role role; /* 角色，一个账号在系统中的角色类型 */
}

class Role{
	private String id;
	private String name;
	private String description;
	//person存储个人详细信息
	private Set<Privilege> privilleges = new HashSet<Privilege>();
}

class Privilege{
	private String id;
	private String name;
	private String description;
}
