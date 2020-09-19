drop database if exists chess;
create database chess;
use chess;

DROP TABLE IF EXISTS `user`;
CREATE TABLE user (
	`username` varchar(60) NOT NULL,
    `password` varchar(60) NOT NULL,
    PRIMARY KEY (`username`)
);

INSERT INTO `user` (username,password) values ("admin","admin");
INSERT INTO `user` (username,password) values ("fischer","fischer");
INSERT INTO `user` (username,password) values ("capablanca","capablanca");
INSERT INTO `user` (username,password) values ("kasparov","kasparov");
INSERT INTO `user` (username,password) values ("carlsen","carlsen");
INSERT INTO `user` (username,password) values ("revuelta","revuelta");

