CREATE DATABASE  IF NOT EXISTS `mydb`
CREATE TABLE IF NOT EXISTS `authors` (
  `authorID` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` char(20) NOT NULL COMMENT 'Author''s first name.',
  `lastName` char(20) NOT NULL COMMENT 'Author''s last name',
  PRIMARY KEY (`authorID`);
INSERT INTO `authors` VALUES (1,'Benjamin','Klunpaitoon'),(2,'Ben','Klunpaireal'),(3,'Beeeen','Klunpaitoooon'),
(4,'Cat','Wood'),(5,'Dog','Steel'),(6,'Hamster','Plastic'),
(7,'Evan','Rennie'),(8,'Evan','Remmie'),(9,'Joey','Richardson'),
(10,'Joey','Richarddad'),(11,'Nick','Lee'),(12,'Nick','Ree'),
(13,'Yanny','Zhang'),(14,'Yanhong','Zhang'),(15,'Deez','Walnuts');
CREATE TABLE IF NOT EXISTS `authorisbn` (
  `authorID` int(11) NOT NULL COMMENT 'The integer ID in this field must appear also in the authors table',
  `isbn` char(10) NOT NULL COMMENT 'the ISBN number for a book',
  KEY `isbn_idx` (`isbn`),
  KEY `authorID_idx` (`authorID`),
  CONSTRAINT `authorID` FOREIGN KEY (`authorID`) REFERENCES `authors` (`authorID`) ON DELETE CASCADE ON UPDATE NO ACTION
INSERT INTO `authorisbn` VALUES 
(1,'00001'),(1,'00002'),(2,'00003'),
(2,'00004'),(4,'00005'),(4,'00006'),
(5,'00007'),(6,'00008'),(7,'00009'),
(8,'00010'),(9,'00011'),(9,'00012'),
(10,'00013'),(12,'00014'),(15,'00015');
CREATE TABLE `publishers` (
  `publisherID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Publisher’s ID number. This autoincremented inte-ger is the table’s primary-key field.',
  `publisher-Name` char(100) NOT NULL COMMENT 'The name of the publisher.',
  PRIMARY KEY (`publisherID`)
INSERT INTO `publishers` VALUES 
(1,'Jonathan Joestar'),(2,'Joseph Joestar'),(3,'Jotaro Kujo'),
(4,'Josuke Higashikata'),(5,'Giorno Giovanna'),(6,'Jolyne Cujoh'),
(7,'Gyro Zepelli'),(8,'Johnny Joestar'),(9,'Funny Valentine'),
(10,'Josuke Higashikata'),(11,'Speedwagon'),(12,'Dio Brando'),
(13,'Diego Brando'),(14,'Koichi Hirose'),(15,'Rohan Kishibe');
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
INSERT INTO `titles` VALUES 
('00001','JoJo\'s Bizarre Adventure Part 1',1,'1090',1,1),
('00002','JoJo\'s Bizarre Adventure Part 2',1,'1100',1,2),
('00003','JoJo\'s Bizarre Adventure Part 3',1,'1110',1,3),
('00004','JoJo\'s Bizarre Adventure Part 4',1,'1120',1,4),
('00005','JoJo\'s Bizarre Adventure Part 5',1,'1940',1,5),
('00006','JoJo\'s Bizarre Adventure Part 6',2,'1849',1,2),
('00007','JoJo\'s Bizarre Adventure Part 7',3,'1994',1,3),
('00008','JoJo\'s Bizarre Adventure Part 8',3,'1998',1,4),
('00009','Naruto',4,'1996',9,5),
('00010','Bleach',4,'1945',10,6),
('00011','One Piece',5,'2004',11,7),
('00012','Boruto',6,'2006',13,8),
('00013','Ballroom Y Youkoso',7,'2007',14,9),
('00014','Eyeshield 21',8,'2015',15,10);
