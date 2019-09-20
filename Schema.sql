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

CREATE TABLE `facility_maintenance`.`mars` (
  `idx` INT NOT NULL AUTO_INCREMENT,
  `facilitytype` VARCHAR(45) NULL,
  `facilityname` VARCHAR(45) NOT NULL,
  `urgency` VARCHAR(10) NOT NULL,
  `description` VARCHAR(500) NULL,
  `reporter` VARCHAR(20) NULL,
  `reportdate` DATE NOT NULL,
  `reporttime` VARCHAR(45) NOT NULL,
  `repairer` VARCHAR(20) NULL,
  PRIMARY KEY (`idx`));
