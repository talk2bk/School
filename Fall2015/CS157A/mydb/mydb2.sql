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
-- Temporary view structure for view `all_publishers`
--

DROP TABLE IF EXISTS `all_publishers`;
/*!50001 DROP VIEW IF EXISTS `all_publishers`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `all_publishers` AS SELECT 
 1 AS `publisher-Name`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `authorisbn`
--

DROP TABLE IF EXISTS `authorisbn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authorisbn` (
  `authorID` int(11) NOT NULL COMMENT 'The integer ID in this field must appear also in the authors table',
  `isbn` char(10) NOT NULL COMMENT 'the ISBN number for a book',
  KEY `isbn_idx` (`isbn`),
  KEY `authorID_idx` (`authorID`),
  CONSTRAINT `authorID` FOREIGN KEY (`authorID`) REFERENCES `authors` (`authorID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorisbn`
--

/*!40000 ALTER TABLE `authorisbn` DISABLE KEYS */;
INSERT INTO `authorisbn` (`authorID`, `isbn`) VALUES (1,'00001'),(1,'00002'),(2,'00003'),(2,'00004'),(4,'00005'),(4,'00006'),(5,'00007'),(6,'00008'),(7,'00009'),(8,'00010'),(9,'00011'),(9,'00012'),(10,'00013'),(12,'00014'),(15,'00015');
/*!40000 ALTER TABLE `authorisbn` ENABLE KEYS */;

--
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authors` (
  `authorID` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` char(20) NOT NULL COMMENT 'Author''s first name.',
  `lastName` char(20) NOT NULL COMMENT 'Author''s last name',
  PRIMARY KEY (`authorID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` (`authorID`, `firstName`, `lastName`) VALUES (1,'Benjamin','Klunpaitoon'),(2,'Ben','Klunpaireal'),(3,'Beeeen','Klunpaitoooon'),(4,'Cat','Wood'),(5,'Dog','Steel'),(6,'Hamster','Plastic'),(7,'Evan','Rennie'),(8,'Evan','Remmie'),(9,'Joey','Richardson'),(10,'Joey','Richarddad'),(11,'Nick','Lee'),(12,'Nick','Ree'),(13,'Yanny','Zhang'),(14,'Yanhong','Zhang'),(15,'Deez','Walnuts');
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;

--
-- Temporary view structure for view `authors_by_lastname`
--

DROP TABLE IF EXISTS `authors_by_lastname`;
/*!50001 DROP VIEW IF EXISTS `authors_by_lastname`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `authors_by_lastname` AS SELECT 
 1 AS `lastname`,
 1 AS `firstname`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `publishers`
--

DROP TABLE IF EXISTS `publishers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publishers` (
  `publisherID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Publisher’s ID number. This autoincremented inte-ger is the table’s primary-key field.',
  `publisher-Name` char(100) NOT NULL COMMENT 'The name of the publisher.',
  PRIMARY KEY (`publisherID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publishers`
--

/*!40000 ALTER TABLE `publishers` DISABLE KEYS */;
INSERT INTO `publishers` (`publisherID`, `publisher-Name`) VALUES (1,'Jonathan Joestar'),(2,'Joseph Joestar'),(3,'Jotaro Kujo'),(4,'Josuke Higashikata'),(5,'Giorno Giovanna'),(6,'Jolyne Cujoh'),(7,'Gyro Zepelli'),(8,'Johnny Joestar'),(9,'Funny Valentine'),(10,'Josuke Higashikata'),(11,'Speedwagon'),(12,'Dio Brando'),(13,'Diego Brando'),(14,'Koichi Hirose'),(15,'Rohan Kishibe');
/*!40000 ALTER TABLE `publishers` ENABLE KEYS */;

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

--
-- Temporary view structure for view `titles_by_publisher 1`
--

DROP TABLE IF EXISTS `titles_by_publisher 1`;
/*!50001 DROP VIEW IF EXISTS `titles_by_publisher 1`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `titles_by_publisher 1` AS SELECT 
 1 AS `title`,
 1 AS `copyright`,
 1 AS `isbn`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping routines for database 'mydb'
--
/*!50003 DROP PROCEDURE IF EXISTS `edit/update_existing_author` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `edit/update_existing_author`()
BEGIN

UPDATE authors
SET lastname = 'newLastname'
WHERE firstname = 'new';

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `edit/update_existing_publisher` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `edit/update_existing_publisher`()
BEGIN

UPDATE publishers
SET `publisher-Name` = 'updatedName'
WHERE publisherID = 16;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_new_author` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_new_author`()
BEGIN

INSERT INTO authors(firstname, lastname)
VALUES('new', 'duder');

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `newbook_for_author` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `newbook_for_author`()
BEGIN

INSERT INTO authorISBN(authorID, isbn)
VALUES(1, '00015');

INSERT INTO titles(isbn, title, editionNumber, copyright, publisherID, price)
VALUES('00015', 'newTitle', 1, 2015, 1, 0);

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `new_procedure` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `new_procedure`()
BEGIN

INSERT INTO publishers(`publisher-Name`)
VALUES ('newPublisher');

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `new_publishers` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `new_publishers`()
BEGIN

INSERT INTO publishers(`publisher-Name`)
VALUES ('newPublisher');

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `all_publishers`
--

/*!50001 DROP VIEW IF EXISTS `all_publishers`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `all_publishers` AS select `publishers`.`publisher-Name` AS `publisher-Name` from `publishers` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `authors_by_lastname`
--

/*!50001 DROP VIEW IF EXISTS `authors_by_lastname`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `authors_by_lastname` AS select `authors`.`lastName` AS `lastname`,`authors`.`firstName` AS `firstname` from `authors` order by `authors`.`lastName` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `titles_by_publisher 1`
--

/*!50001 DROP VIEW IF EXISTS `titles_by_publisher 1`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `titles_by_publisher 1` AS select `titles`.`title` AS `title`,`titles`.`copyright` AS `copyright`,`titles`.`isbn` AS `isbn` from `titles` where (`titles`.`publisherID` = 1) order by `titles`.`title` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
