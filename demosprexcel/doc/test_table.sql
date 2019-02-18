-- we don't know how to generate schema demosprexcel (class Schema) :(
create table user_student
(
  id bigint auto_increment
    primary key,
  name varchar(20) not null,
  num varchar(20) null comment '学号',
  sex varchar(1) not null,
  age varchar(10) not null
)
;

create table user_teacher
(
  id bigint auto_increment
    primary key,
  teacher_name varchar(20) not null,
  teacher_num varchar(10) not null,
  teacher_sex varchar(1) not null
)
;

