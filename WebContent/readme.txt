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
	birthday date,
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
	description varchar(255)

);

--权限privilege和角色role关系，定义为多对多
create table role_privilege
(
	privilege_id varchar(40),
	role_id varchar(40),
	primary key(privilege_id,role_id),
	constraint role_id_FK2 foreign key(role_id) references role(id),
	constraint privilege_id_id_FK foreign key(privilege_id) references privilege(id)
);



--资源表   -----upload_id上传者
create table resource
(
	id varchar(40) primary key,
	name varchar(100) not null,
	uri varchar(255) not null,
	uploadtime date not null,
	description varchar(255),
	
	uploader_id varchar(40),  
	constraint upload_id_FK foreign key(upload_id) references user(id)
);
--作业表	--name eg.第一次英语作业 --description 老师对作业补充
create table task
(
	id varchar(40) primary key,
	name varchar(100) not null,
	description varchar(255),
	
	course_id varchar(40) ,
	resource_id varchar(40),    
	constraint resource_id_FK2 foreign key(resource_id) references resource(id),
	constraint course_id_FK2 foreign key(course_id) references course(id)
);
--用户user与作业task之间的关系
create table user_task (
  user_id varchar(40) not null,
  task_id varchar(40) not null,
  score int DEFAULT 0,
  score2 int default 0,
  primary key(user_id,task_id),
  constraint user_id_FK3 foreign key(user_id) references user(id),
  constraint task_id_FK3 foreign key(task_id) references task(id)
)
--课表course各表设计
create table course
(
	id varchar(40) primary key,
	name varchar(100) not null,
	limitperson int,
	description varchar(255),
	teacher_id varchar(40),
	constraint teacher_id_FK foreign key(teacher_id) references user(id)
);
--学生课表表student_course  -- score学生该课程最后总成绩
create table student_course
(
	course_id varchar(40),
	student_id varchar(40),
 	score int, 
	primary key(course_id,student_id),

	constraint course_id_FK foreign key(course_id) references course(id),
	constraint student_id_FK foreign key(student_id) references user(id)
);

--课件表
create table courseware
(
	id varchar(40) primary key,
	name varchar(100) not null,
	description varchar(255),
	resource_id varchar(40),
	course_id varchar(40),
	constraint resource_id_FK foreign key(resource_id) references resource(id),
	constraint course_id_FK3 foreign key(course_id) references course(id)
);

--评论表
create table review
(
	id varchar(40) primary key,
	content varchar(255),
	time date not null,

	user_id varchar(40),
	constraint user_id_FK4 foreign key(user_id) references user(id)
);


create table courseware_review
(
	courseware_id varchar(40),
	review_id varchar(40),
	
	primary key(courseware_id,review_id),
	
	constraint courseware_id_FK foreign key(courseware_id) references courseware(id),
	constraint review_id_FK2 foreign key(review_id) references review(id)
);


create table task_review
(
	task_id varchar(40),
	review_id varchar(40),
	
	primary key(task_id,review_id),
	
	constraint task_id_FK foreign key(task_id) references task(id),
	constraint review_id_FK foreign key(review_id) references review(id)
);


