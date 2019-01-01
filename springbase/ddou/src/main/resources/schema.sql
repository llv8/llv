--source /path/to/schema.sql

create database ddou charset=utf8;

use ddou;

create table customer(
	id int not null primary key auto_increment,
	name varchar(40) not null,
	phone varchar(40) not null,
	email varchar(80) not null,
	password varchar(40)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

