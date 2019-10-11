CREATE SCHEMA `facility_maintenance` ;

CREATE TABLE `facility_maintenance`.`users` (
  `username` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `role` VARCHAR(16) NOT NULL,
  `utaid` VARCHAR(10) NOT NULL,
  `fname` VARCHAR(40) NOT NULL,
  `lname` VARCHAR(20) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `phone` VARCHAR(10) NOT NULL,
  `address` VARCHAR(200) NOT NULL,
  `city` VARCHAR(50) NOT NULL,
  `state` VARCHAR(20) NOT NULL,
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
  `assigntime` VARCHAR(45) NULL,
  `estimate` VARCHAR(10) NULL,
  `repairdate` DATE NULL,
  `starttime` VARCHAR(45) NULL,
  `endtime` VARCHAR(45) NULL,
  PRIMARY KEY (`idx`));

CREATE TABLE `facility_maintenance`.`facilitymaster` (
  `id` VARCHAR(4) NOT NULL,
  `name` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `facility_maintenance`.`facilitydetail` (
  `master` VARCHAR(4) NOT NULL,
  `id` INT NOT NULL,
  `interval` VARCHAR(10) NOT NULL,
  `duration` VARCHAR(6) NOT NULL,
  `venue` VARCHAR(8) NOT NULL,
  PRIMARY KEY (`master`, `id`));

INSERT INTO `facility_maintenance`.`facilitymaster` (`id`, `name`) VALUES ('MR', 'Multipurpose rooms');
INSERT INTO `facility_maintenance`.`facilitymaster` (`id`, `name`) VALUES ('IBBC', 'Indoor basketball courts');
INSERT INTO `facility_maintenance`.`facilitymaster` (`id`, `name`) VALUES ('IVBC', 'Volleyball courts');
INSERT INTO `facility_maintenance`.`facilitymaster` (`id`, `name`) VALUES ('SCG', 'Indoor soccer gymnasium');
INSERT INTO `facility_maintenance`.`facilitymaster` (`id`, `name`) VALUES ('RBC', 'Racquetball courts');
INSERT INTO `facility_maintenance`.`facilitymaster` (`id`, `name`) VALUES ('BMC', 'Badminton courts');
INSERT INTO `facility_maintenance`.`facilitymaster` (`id`, `name`) VALUES ('TT', 'Table Tennis');
INSERT INTO `facility_maintenance`.`facilitymaster` (`id`, `name`) VALUES ('CR', 'Conference rooms');
INSERT INTO `facility_maintenance`.`facilitymaster` (`id`, `name`) VALUES ('OVBC', 'Outdoor Volleyball Courts');
INSERT INTO `facility_maintenance`.`facilitymaster` (`id`, `name`) VALUES ('OBBC', 'Outdoor Basketball Courts');

INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('MR', 1, '1 hour', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('MR', 2, '1 hour', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('MR', 3, '1 hour', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('MR', 4, '1 hour', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('IBBC', 1, '1 hour', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('IBBC', 2, '1 hour', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('IBBC', 3, '1 hour', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('IBBC', 4, '1 hour', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('IBBC', 5, '1 hour', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('IVBC', 1, '1 hour', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('IVBC', 2, '1 hour', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('IVBC', 3, '1 hour', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('IVBC', 4, '1 hour', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('IVBC', 5, '1 hour', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('IVBC', 6, '1 hour', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('IVBC', 7, '1 hour', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('IVBC', 8, '1 hour', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('IVBC', 9, '1 hour', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('SCG', 1, '2 hours', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('RBC', 1, '30 minutes', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('RBC', 2, '30 minutes', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('RBC', 3, '30 minutes', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('RBC', 4, '30 minutes', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('RBC', 5, '30 minutes', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('BMC', 1, '30 minutes', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('BMC', 2, '30 minutes', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('BMC', 3, '30 minutes', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('BMC', 4, '30 minutes', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('BMC', 5, '30 minutes', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('BMC', 6, '30 minutes', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('BMC', 7, '30 minutes', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('BMC', 8, '30 minutes', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('BMC', 9, '30 minutes', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('BMC', 10, '30 minutes', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('TT', 1, '30 minutes', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('CR', 1, '1 hour', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('CR', 2, '1 hour', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('CR', 3, '1 hour', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('CR', 4, '1 hour', '1 day', 'Indoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('CR', 5, '1 hour', '1 day', 'Indoor');

INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('OVBC', 1, '2 hours', '7 days', 'Outdoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('OVBC', 2, '2 hours', '7 days', 'Outdoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('OBBC', 1, '2 hours', '7 days', 'Outdoor');
INSERT INTO `facility_maintenance`.`facilitydetail` (`master`, `id`, `interval`, `duration`, `venue`) VALUES ('OBBC', 2, '2 hours', '7 days', 'Outdoor');