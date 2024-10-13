create database shopping;

use shopping;

create table roles(
id int(11) auto_increment,
role_name varchar(50),
primary key(id)
); 

create table users(
id int auto_increment,
name varchar(50),
first_name varchar(50),
last_name varchar(50),
user_name varchar(50),
password varchar(20),
email varchar(50),
phone_numbet int(20),
status int(11),
address varchar(50),
note varchar(255),
role_id int(11),
primary key(id),
foreign key(role_id) references roles(id)
);

create table product_types(
id int auto_increment,
name varchar(100),
primary key(id)
);

create table reviews(
id int auto_increment,
review varchar(255),
primary key(id)
);

create table products(
id int auto_increment,
color varchar(50),
description varchar(255),
price double,
discount double,
size varchar(50),
img varchar(255),
status varchar(255),
product_type_id int(11),
review_id int,
primary key(id),
foreign key(product_type_id) references product_types(id),
foreign key(review_id) references reviews(id)
);

create table user_product(
id int auto_increment,
user_id int not null,
product_id int not null,
primary key(id),
foreign key(user_id) references users(id),
foreign key(product_id) references products(id)
);