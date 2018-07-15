CREATE DATABASE movies_db;
USE movies_db;

CREATE TABLE movies (
id smallint unsigned not null auto_increment,
title varchar(250) not null,
imdb_id varchar(50) not null,
release_year smallint unsigned not null,
poster varchar(1000) not null,
constraint pk_movies_id primary key (id) );

select * from movies

--delete from MOVIES