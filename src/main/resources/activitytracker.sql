/*
SQLyog Enterprise - MySQL GUI v7.02 
MySQL - 5.0.67-community-nt : Database - activitytracker
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`activitytracker` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `activitytracker`;

/*Table structure for table `sessions` */

DROP TABLE IF EXISTS `sessions`;

CREATE TABLE `sessions` (
  `username` varchar(50) NOT NULL,
  `token` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `sessions` */

insert  into `sessions`(`username`,`token`) values ('madaan@gmail.com','bWFkYWFuQGdtYWlsLmNvbTIwMTkwNjI2MDEwNTQ3'),('gaurav@gmail.com','Z2F1cmF2QGdtYWlsLmNvbTIwMTkwNjI3MDA1NTAz'),('vaibhav@gmail.com','dmFpYmhhdkBnbWFpbC5jb20yMDE5MDYyNzIyNDYxNQ==');

/*Table structure for table `tasks` */

DROP TABLE IF EXISTS `tasks`;

CREATE TABLE `tasks` (
  `task_id` int(10) NOT NULL auto_increment,
  `task_description` varchar(200) NOT NULL,
  `task_date` date NOT NULL,
  `is_completed` tinyint(1) NOT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY  (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `tasks` */

insert  into `tasks`(`task_id`,`task_description`,`task_date`,`is_completed`,`username`) values (1,'First task','2019-06-26',0,'vaibhav@gmail.com'),(2,'Second task','2019-06-29',0,'gaurav@gmail.com'),(3,'Third task','2019-06-30',0,'vaibhav@gmail.com'),(4,'Fourth task','2019-07-05',0,'vaibhav@gmail.com'),(5,'Fifth task','2019-06-22',0,'vaibhav@gmail.com'),(6,'Sixth task','2019-06-27',0,'vaibhav@gmail.com'),(7,'Seventh task','2019-06-28',0,'vaibhav@gmail.com');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`username`,`password`,`name`) values ('gaurav@gmail.com','MTIzNDU=','Gaurav Sharma'),('madaan@gmail.com','MTIzNDU=','Anmol Madaan'),('vaibhav@gmail.com','MTIzNDU=','Vaibhav Vijay');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
