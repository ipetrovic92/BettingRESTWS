/*
SQLyog Community v12.2.4 (64 bit)
MySQL - 5.7.13-log : Database - betting
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`betting` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `betting`;

/*Table structure for table `administrator` */

DROP TABLE IF EXISTS `administrator`;

CREATE TABLE `administrator` (
  `email` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `password` varchar(15) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `administrator` */

insert  into `administrator`(`email`,`first_name`,`last_name`,`password`) values 
('1','1','1','1'),
('ivan42382@gmail.com','Ivan','Petrovic','password');

/*Table structure for table `betting_user` */

DROP TABLE IF EXISTS `betting_user`;

CREATE TABLE `betting_user` (
  `email` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `date_of_birth` date NOT NULL,
  `password` varchar(50) NOT NULL,
  `balance` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `betting_user` */

insert  into `betting_user`(`email`,`first_name`,`last_name`,`date_of_birth`,`password`,`balance`) values 
('2','2','2','1992-05-28','2',88550),
('ipetrovic@s4hc.com','Ivan','Petrovic','1992-05-28','password',5000),
('lazar.piper@gmail.com','Lazar','Piper','1992-09-08','lazarpiper',5000);

/*Table structure for table `country` */

DROP TABLE IF EXISTS `country`;

CREATE TABLE `country` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `country` */

insert  into `country`(`id`,`name`) values 
(1,'Serbia'),
(2,'England'),
(3,'Germany'),
(4,'Spain'),
(5,'France'),
(6,'Italy');

/*Table structure for table `game` */

DROP TABLE IF EXISTS `game`;

CREATE TABLE `game` (
  `id` varchar(20) NOT NULL,
  `game_time` datetime NOT NULL,
  `home_odds` double NOT NULL,
  `away_odds` double NOT NULL,
  `draw_odds` double NOT NULL,
  `score` varchar(5) NOT NULL,
  `email_administrator` varchar(50) NOT NULL,
  `processing_status` int(11) NOT NULL DEFAULT '1',
  `home_team` int(11) NOT NULL,
  `away_team` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `email_administrator_fk` (`email_administrator`),
  KEY `home_team_fk` (`home_team`),
  KEY `away_team_fk` (`away_team`),
  KEY `processing_status_fk` (`processing_status`),
  CONSTRAINT `away_team_fk` FOREIGN KEY (`away_team`) REFERENCES `team` (`id`),
  CONSTRAINT `email_administrator_fk` FOREIGN KEY (`email_administrator`) REFERENCES `administrator` (`email`),
  CONSTRAINT `home_team_fk` FOREIGN KEY (`home_team`) REFERENCES `team` (`id`),
  CONSTRAINT `processing_status_fk` FOREIGN KEY (`processing_status`) REFERENCES `processing` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `game` */

insert  into `game`(`id`,`game_time`,`home_odds`,`away_odds`,`draw_odds`,`score`,`email_administrator`,`processing_status`,`home_team`,`away_team`) values 
('1','2016-09-30 21:00:00',1.73,4,5,'3:2','ivan42382@gmail.com',2,8,11),
('10','2016-10-02 17:30:00',9,5,1.4,'2:2','ivan42382@gmail.com',2,13,6),
('11','2016-10-02 18:00:00',3.75,3.3,2,'','ivan42382@gmail.com',2,85,87),
('12','2016-10-02 20:00:00',3,3.3,2.38,'','ivan42382@gmail.com',2,91,88),
('13','2016-10-03 12:30:00',9.5,4.75,1.36,'','ivan42382@gmail.com',2,98,79),
('14','2016-10-03 15:00:00',6,4,1.57,'','ivan42382@gmail.com',2,96,80),
('15','2016-10-03 15:00:00',2.4,3.1,3.2,'','ivan42382@gmail.com',2,83,81),
('16','2016-10-04 15:00:00',1.44,4.2,8.5,'','ivan42382@gmail.com',2,94,97),
('17','2016-10-04 15:00:00',1.73,3.6,5.25,'','ivan42382@gmail.com',2,82,95),
('18','2016-10-04 18:00:00',1.67,3.75,5.5,'','ivan42382@gmail.com',2,90,92),
('19','2016-10-04 18:00:00',2.25,3.1,3.5,'','ivan42382@gmail.com',2,86,89),
('2','2016-10-01 13:30:00',8.5,4.75,1.44,'','ivan42382@gmail.com',2,16,5),
('20','2016-10-04 20:00:00',2,3.5,3.8,'','ivan42382@gmail.com',2,84,93),
('3','2016-10-01 16:00:00',7,4.75,1.5,'','ivan42382@gmail.com',2,7,2),
('4','2016-10-01 16:00:00',2.4,3.25,3.3,'','ivan42382@gmail.com',2,19,12),
('5','2016-10-01 16:00:00',2.38,3.3,3.3,'','ivan42382@gmail.com',2,10,13),
('6','2016-10-01 16:00:00',2.15,3.5,3.6,'','ivan42382@gmail.com',2,12,9),
('7','2016-10-02 13:00:00',1.3,5.75,12,'','ivan42382@gmail.com',2,3,20),
('8','2016-10-02 15:15:00',2.25,3.4,3.5,'','ivan42382@gmail.com',2,15,18),
('9','2016-10-02 15:15:00',3.1,3.5,2.4,'','ivan42382@gmail.com',2,4,3);

/*Table structure for table `processing` */

DROP TABLE IF EXISTS `processing`;

CREATE TABLE `processing` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `processing_outcome` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `processing` */

insert  into `processing`(`id`,`processing_outcome`) values 
(1,'processed'),
(2,'unprocessed');

/*Table structure for table `status` */

DROP TABLE IF EXISTS `status`;

CREATE TABLE `status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `status` */

insert  into `status`(`id`,`status`) values 
(1,'win'),
(2,'lose'),
(3,'unknown');

/*Table structure for table `team` */

DROP TABLE IF EXISTS `team`;

CREATE TABLE `team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `country_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `country_fk` (`country_id`),
  CONSTRAINT `country_fk` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8;

/*Data for the table `team` */

insert  into `team`(`id`,`name`,`country_id`) values 
(1,'Manchester City',2),
(2,'Chelsea',2),
(3,'Manchester United',2),
(4,'Tottenham',2),
(5,'Liverpool',2),
(6,'Arsenal',2),
(7,'Hull City',2),
(8,'Everton',2),
(9,'Middlesbrough',2),
(10,'Watford',2),
(11,'Crystal Palace',2),
(12,'West Brom',2),
(13,'Burnley',2),
(14,'Bournemouth',2),
(15,'Leicester',2),
(16,'Swansea',2),
(17,'West Ham',2),
(18,'Southampton',2),
(19,'Sunderland',2),
(20,'Stoke City',2),
(21,'Monaco',5),
(22,'Bordeaux',5),
(23,'Guingamp',5),
(24,'Paris SG',5),
(25,'Nice',5),
(26,'Lyon',5),
(27,'Caen',5),
(28,'Bastia',5),
(29,'Metz',5),
(30,'Toulouse',5),
(31,'St Etienne',5),
(32,'Montpellier',5),
(33,'Marseille',5),
(34,'Rennes',5),
(35,'Lille',5),
(36,'Nantes',5),
(37,'Dijon',5),
(38,'Angers',5),
(39,'Nancy',5),
(40,'Lorient',5),
(41,'Bayern Munich',3),
(42,'Hertha Berlin',3),
(43,'Wolfsburg',3),
(44,'1. FC Koln',3),
(45,'RB Leipzig',3),
(46,'Bayer Leverkusen',3),
(47,'Freiburg',3),
(48,'Dortmund',3),
(49,'Eintracht Frankfurt',3),
(50,'B. Monchengladbach',3),
(51,'Darmstadt',3),
(52,'Hoffenheim',3),
(53,'Hamburger SV',3),
(54,'Ingolstadt',3),
(55,'Mainz',3),
(56,'Augsburg',3),
(57,'Schalke',3),
(58,'SV Werder Bremen',3),
(59,'Real Madrid',4),
(60,'Sevilla',4),
(61,'Gijon',4),
(62,'Las Palmas',4),
(63,'Atl. Madrid',4),
(64,'Villarreal',4),
(65,'Alaves',4),
(66,'Dep. La Coruna',4),
(67,'Leganes',4),
(68,'Real Sociedad',4),
(69,'Eibar',4),
(70,'Espanyol',4),
(71,'Malaga',4),
(72,'Betis',4),
(73,'Granada CF',4),
(74,'Osasuna',4),
(75,'Ath Bilbao',4),
(76,'Valencia',4),
(77,'Celta Vigo',4),
(78,'Barcelona',4),
(79,'Juventus',6),
(80,'Napoli',6),
(81,'Genoa',6),
(82,'Sampdoria',6),
(83,'Bologna',6),
(84,'AS Roma',6),
(85,'Pescara',6),
(86,'Torino',6),
(87,'Chievo',6),
(88,'Lazio',6),
(89,'Fiorentina',6),
(90,'AC Milan',6),
(91,'Udinese',6),
(92,'Sassuolo',6),
(93,'Inter',6),
(94,'Cagliari',6),
(95,'Palermo',6),
(96,'Atalanta',6),
(97,'Crotone',6),
(98,'Empoli',6),
(99,'FK Crvena zvezda',1),
(100,'Vojvodina',1),
(101,'Radnicki Nis',1),
(102,'Metalac',1),
(103,'Sp. Subotica',1),
(104,'Napredak',1),
(105,'Mladost',1),
(106,'FK Vozdovac',1),
(107,'Partizan',1),
(108,'Rad Beograd',1),
(109,'Novi Pazar',1),
(110,'Javor',1),
(111,'Radnik',1),
(112,'Borac',1),
(113,'Cukaricki',1),
(114,'Backa',1);

/*Table structure for table `ticket` */

DROP TABLE IF EXISTS `ticket`;

CREATE TABLE `ticket` (
  `id` varchar(50) NOT NULL,
  `email_user` varchar(50) NOT NULL,
  `payment_time` datetime NOT NULL,
  `stake` double NOT NULL,
  `total_odds` double NOT NULL,
  `potential_winnings` double NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`email_user`,`id`),
  KEY `status_fk` (`status`),
  CONSTRAINT `email_user_fk` FOREIGN KEY (`email_user`) REFERENCES `betting_user` (`email`),
  CONSTRAINT `status_fk` FOREIGN KEY (`status`) REFERENCES `status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ticket` */

insert  into `ticket`(`id`,`email_user`,`payment_time`,`stake`,`total_odds`,`potential_winnings`,`status`) values 
('1','2','2016-10-01 01:09:32',30,93.258,2797.74,3),
('2','2','2016-10-01 01:09:53',30,15.57,467.1,3),
('3','2','2016-10-01 01:10:12',300,29.4965,8848.95,3),
('4','2','2016-10-01 01:10:36',300,96.1875,28856.25,3);

/*Table structure for table `ticket_match` */

DROP TABLE IF EXISTS `ticket_match`;

CREATE TABLE `ticket_match` (
  `ticket_id` varchar(50) NOT NULL,
  `email_user` varchar(50) NOT NULL,
  `match_id` varchar(50) NOT NULL,
  `result_prediction` int(11) NOT NULL,
  `odd` double NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`email_user`,`ticket_id`,`match_id`),
  KEY `match_id_fk2` (`ticket_id`),
  KEY `status_fk2` (`status`),
  KEY `match_fk2` (`match_id`),
  CONSTRAINT `match_fk2` FOREIGN KEY (`match_id`) REFERENCES `game` (`id`),
  CONSTRAINT `status_fk2` FOREIGN KEY (`status`) REFERENCES `status` (`id`),
  CONSTRAINT `ticket_fks` FOREIGN KEY (`email_user`, `ticket_id`) REFERENCES `ticket` (`email_user`, `id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ticket_match` */

insert  into `ticket_match`(`ticket_id`,`email_user`,`match_id`,`result_prediction`,`odd`,`status`) values 
('1','2','10',1,9,3),
('1','2','11',0,2,3),
('1','2','12',2,3.3,3),
('1','2','14',0,1.57,3),
('2','2','10',1,9,3),
('2','2','17',1,1.73,3),
('3','2','17',1,1.73,3),
('3','2','18',0,5.5,3),
('3','2','19',2,3.1,3),
('4','2','10',1,9,3),
('4','2','13',2,4.75,3),
('4','2','19',1,2.25,3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
