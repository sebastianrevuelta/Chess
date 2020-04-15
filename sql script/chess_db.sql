drop database if exists chess;
create database chess;
use chess;

DROP TABLE IF EXISTS `user`;
CREATE TABLE user (
	`username` varchar(60) NOT NULL,
    `password` varchar(60) NOT NULL,
    PRIMARY KEY (`username`)
);

INSERT INTO `user` (username,password) values ("sebastian.revuelta@kiuwan.com","thisismypassword");
INSERT INTO `user` (username,password) values ("eduardo.boronat@kiuwan.com","thepasswordofedu");
INSERT INTO `user` (username,password) values ("chess.king@kiuwan.com","passwordking");
INSERT INTO `user` (username,password) values ("chess.queen@kiuwan.com","passwordqueen");
