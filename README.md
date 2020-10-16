# demo
 An login web based on Spring Boot and used MyBait to connect MySQL database

Before use this, you need to create a MySQL database which contains two tables.
create table informations(
    account varchar(20) not null,
    firstName varchar(20) not null,
    name varchar(20) not null,
    gender varchar(20) not null,
    nickName varchar(20) not null,
    phoneNumber varchar(20) not null,
    email varchar(40) not null,
    text text
);

create table users(
   account varchar(20) not null,
   password varchar(40) not null
)

