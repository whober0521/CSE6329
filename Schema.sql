CREATE SCHEMA `facility_maintenance` ;

CREATE TABLE `facility_maintenance`.`users` (
  `username` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `role` VARCHAR(1) NOT NULL,
  `utaid` VARCHAR(10) NOT NULL,
  `fname` VARCHAR(40) NOT NULL,
  `lname` VARCHAR(20) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `phone` VARCHAR(10) NOT NULL,
  `address` VARCHAR(200) NOT NULL,
  `city` VARCHAR(50) NOT NULL,
  `state` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`username`));