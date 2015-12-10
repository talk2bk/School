-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	5.7.9-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `titles`
--

DROP TABLE IF EXISTS `titles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `titles` (
  `isbn` char(10) NOT NULL COMMENT 'ISBN number of the book',
  `title` varchar(500) NOT NULL COMMENT 'Title of the book',
  `editionNumber` int(11) NOT NULL COMMENT 'Edition number of the book',
  `copyright` char(4) NOT NULL COMMENT 'Copyright year of the book',
  `publisherID` int(11) NOT NULL COMMENT 'Publisher’s ID number. This value corresponds to an ID number in the publishers table.',
  `price` float NOT NULL COMMENT 'Suggested retail price of the book',
  PRIMARY KEY (`isbn`),
  KEY `publisherID_idx` (`publisherID`),
  CONSTRAINT `publisherID` FOREIGN KEY (`publisherID`) REFERENCES `publishers` (`publisherID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `titles`
--

/*!40000 ALTER TABLE `titles` DISABLE KEYS */;
INSERT INTO `titles` (`isbn`, `title`, `editionNumber`, `copyright`, `publisherID`, `price`) VALUES ('00001','JoJo\'s Bizarre Adventure Part 1',1,'1090',1,1),('00002','JoJo\'s Bizarre Adventure Part 2',1,'1100',1,2),('00003','JoJo\'s Bizarre Adventure Part 3',1,'1110',1,3),('00004','JoJo\'s Bizarre Adventure Part 4',1,'1120',1,4),('00005','JoJo\'s Bizarre Adventure Part 5',1,'1940',1,5),('00006','JoJo\'s Bizarre Adventure Part 6',2,'1849',1,2),('00007','JoJo\'s Bizarre Adventure Part 7',3,'1994',1,3),('00008','JoJo\'s Bizarre Adventure Part 8',3,'1998',1,4),('00009','Naruto',4,'1996',9,5),('00010','Bleach',4,'1945',10,6),('00011','One Piece',5,'2004',11,7),('00012','Boruto',6,'2006',13,8),('00013','Ballroom Y Youkoso',7,'2007',14,9),('00014','Eyeshield 21',8,'2015',15,10);
/*!40000 ALTER TABLE `titles` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
