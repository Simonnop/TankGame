CREATE DATABASE TankGame;

USE TankGame;

CREATE TABLE `user` (
`username` VARCHAR(12) NOT NULL  ,
`score` INT,
`type` VARCHAR(12) NOT NULL ,
primary key (username,type)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


INSERT INTO USER VALUES('zi',6,'普通');
INSERT INTO USER VALUES('su',6,'普通');
INSERT INTO USER VALUES('zh',6,'普通');