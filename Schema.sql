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
  `facility` VARCHAR(5) NOT NULL,
  `description` VARCHAR(500) NULL,
  `urgency` VARCHAR(10) NULL,
  `reporter` VARCHAR(20) NULL,
  `reportdate` DATE NOT NULL,
  `reporttime` VARCHAR(45) NOT NULL,
  `repairer` VARCHAR(20) NULL,
  `assigndate` DATE NULL,
  PRIMARY KEY (`idx`));

CREATE TABLE `facility_maintenance`.`facilities` (
  `id` VARCHAR(4) NOT NULL,
  `name` VARCHAR(25) NOT NULL,
  `amount` INT NOT NULL,
  PRIMARY KEY (`id`));

INSERT INTO `facility_maintenance`.`facilities` (`id`, `name`, `amount`) VALUES ('MR', 'Multipurpose rooms', 4);
INSERT INTO `facility_maintenance`.`facilities` (`id`, `name`, `amount`) VALUES ('IBBC', 'Indoor basketball courts', 5);
INSERT INTO `facility_maintenance`.`facilities` (`id`, `name`, `amount`) VALUES ('IVBC', 'Volleyball courts', 9);
INSERT INTO `facility_maintenance`.`facilities` (`id`, `name`, `amount`) VALUES ('SCG', 'Indoor soccer gymnasium', 1);
INSERT INTO `facility_maintenance`.`facilities` (`id`, `name`, `amount`) VALUES ('RBC', 'Racquetball courts', 5);
INSERT INTO `facility_maintenance`.`facilities` (`id`, `name`, `amount`) VALUES ('BMC', 'Badminton courts', 10);
INSERT INTO `facility_maintenance`.`facilities` (`id`, `name`, `amount`) VALUES ('TT', 'Table Tennis', 1);
INSERT INTO `facility_maintenance`.`facilities` (`id`, `name`, `amount`) VALUES ('CR', 'Conference rooms', 5);
INSERT INTO `facility_maintenance`.`facilities` (`id`, `name`, `amount`) VALUES ('OVBC', 'Outdoor Volleyball Courts', 2);
INSERT INTO `facility_maintenance`.`facilities` (`id`, `name`, `amount`) VALUES ('OBBC', 'Outdoor Basketball Courts', 2);
