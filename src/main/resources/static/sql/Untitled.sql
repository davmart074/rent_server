CREATE TABLE `megrendelo` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `email` varchar(255),
  `phone` varchar(255),
  `licenseNumber` varchar(255),
  `creditCard` int,
  `creditCardExp` varchar(255),
  `cvv` int,
  `startDate` datetime,
  `endDate` datetime,
  `licenseTime` tinyint,
  `insure` tinyint,
  `carid` int
)
ENGINE=InnoDB;
CREATE TABLE `auto` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255)
)
ENGINE=InnoDB;
CREATE TABLE `kapcsolat` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `email` varchar(255),
  `phone` varchar(255),
  `message` text
)
ENGINE=InnoDB;

ALTER TABLE `megrendelo` ADD FOREIGN KEY (`carid`) REFERENCES `auto` (`id`);
