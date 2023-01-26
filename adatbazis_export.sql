-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jan 23, 2023 at 02:37 PM
-- Server version: 8.0.31
-- PHP Version: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rent`
--

-- --------------------------------------------------------

--
-- Table structure for table `auto`
--

DROP TABLE IF EXISTS `auto`;
CREATE TABLE IF NOT EXISTS `auto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf16;

--
-- Dumping data for table `auto`
--

INSERT INTO `auto` (`id`, `name`) VALUES
(1, 'ford'),
(2, 'dodge'),
(3, 'delorean'),
(4, 'lincoln'),
(5, 'chevrolet');

-- --------------------------------------------------------

--
-- Table structure for table `kapcsolat`
--

DROP TABLE IF EXISTS `kapcsolat`;
CREATE TABLE IF NOT EXISTS `kapcsolat` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `message` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf16;

-- --------------------------------------------------------

--
-- Table structure for table `megrendelo`
--

DROP TABLE IF EXISTS `megrendelo`;
CREATE TABLE IF NOT EXISTS `megrendelo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `licenseNumber` varchar(255) DEFAULT NULL,
  `creditCard` varchar(255) DEFAULT NULL,
  `creditCardExp` varchar(255) DEFAULT NULL,
  `cvv` int DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `licenseTime` tinyint DEFAULT NULL,
  `insure` tinyint DEFAULT NULL,
  `carid` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `carid` (`carid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `megrendelo`
--
ALTER TABLE `megrendelo`
  ADD CONSTRAINT `megrendelo_ibfk_1` FOREIGN KEY (`carid`) REFERENCES `auto` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
