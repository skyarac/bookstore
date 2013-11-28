/*
SQLyog Enterprise Trial - MySQL GUI v6.04
Host - 5.0.45-community-nt : Database - bookstore
*********************************************************************
Server version : 5.0.45-community-nt
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

create database if not exists `bookstore`;

USE `bookstore`;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

/*Table structure for table `bookinfo` */

DROP TABLE IF EXISTS `bookinfo`;

CREATE TABLE `bookinfo` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `bookName` varchar(128) NOT NULL default '',
  `isbn` varchar(64) NOT NULL default '',
  `writer` varchar(45) NOT NULL default '',
  `publisher` varchar(64) NOT NULL default '',
  `intro` text,
  `price` decimal(5,2) NOT NULL default '0.00',
  `remaining` smallint(5) unsigned zerofill NOT NULL default '00000',
  `picture` varchar(32) default NULL,
  `date` datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `bookinfo` */

insert  into `bookinfo`(`id`,`bookName`,`isbn`,`writer`,`publisher`,`intro`,`price`,`remaining`,`picture`,`date`) values (1,'c++','111-222-333','美国人','清华大学出版社','书主要介绍ACE(ADAPTIVECommunication Environment)是用于构建高性能网络化应用和下一代中间的开放源码工具包，已在世界各地的许多软件项目中得到了广泛应用。','123.00',12846,'1186042677562.gif','2007-08-02 16:17:57'),(2,'Java程序设计','111-222-333','liuyu','北京大学出版社','本书赢得了全球程序员的广泛赞誉，即使是最晦涩的概念，在Bruce Eckel具有亲和力的文字和小而直接的编程示例面前也会化解于无形。从Java的基础语法到最高级特性（深入的面向对象概念、多线程、自动项目构建、单元测试和调试等）本书都能指导你轻松掌握','32.00',12345,'1185329925296.jpg','2007-07-28 20:39:27'),(3,'Delphi','111-222-333','啊威奇.可林','教育出版社','书分为基本概念，操作系统，桌面秘笈，编写游戏，软件开发5大篇。主要内容包括：RAD编程工具的优劣，VCL基本概念，使用控制面板，定时器，桌面世界，背景主题工具实战，编写屏幕保护——自己的计划表，编写足球赛游戏，坦克大决战游戏，Fancy软件编写手则','42.00',12346,'1185329781812.jpg','2007-07-28 20:39:03');

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `bookid` int(10) unsigned NOT NULL default '0',
  `userid` int(10) unsigned NOT NULL default '0',
  `number` tinyint(3) unsigned NOT NULL default '0',
  `address` varchar(128) NOT NULL default '',
  `postcode` varchar(6) NOT NULL default '',
  `orderdate` datetime NOT NULL default '0000-00-00 00:00:00',
  `status` tinyint(3) unsigned zerofill NOT NULL default '000',
  PRIMARY KEY  (`id`),
  KEY `FK_order_1` (`bookid`),
  KEY `FK_order_2` (`userid`),
  CONSTRAINT `FK_order_1` FOREIGN KEY (`bookid`) REFERENCES `bookinfo` (`id`),
  CONSTRAINT `FK_order_2` FOREIGN KEY (`userid`) REFERENCES `userinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `order` */

/*Table structure for table `rebate` */

DROP TABLE IF EXISTS `rebate`;

CREATE TABLE `rebate` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `level` decimal(10,2) unsigned zerofill NOT NULL default '00000000.00',
  `rebateRate` decimal(2,1) unsigned zerofill NOT NULL default '0.0',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `rebate` */

/*Table structure for table `remark` */

DROP TABLE IF EXISTS `remark`;

CREATE TABLE `remark` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `bookid` int(10) unsigned NOT NULL default '0',
  `userid` int(10) unsigned NOT NULL default '0',
  `grade` tinyint(3) unsigned NOT NULL default '0',
  `remark` text NOT NULL,
  `date` datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`id`),
  KEY `FK_remark_1` (`bookid`),
  KEY `FK_remark_2` (`userid`),
  CONSTRAINT `FK_remark_1` FOREIGN KEY (`bookid`) REFERENCES `bookinfo` (`id`),
  CONSTRAINT `FK_remark_2` FOREIGN KEY (`userid`) REFERENCES `userinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `remark` */

/*Table structure for table `userinfo` */

DROP TABLE IF EXISTS `userinfo`;

CREATE TABLE `userinfo` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `username` varchar(15) NOT NULL default '',
  `pwd` varchar(32) NOT NULL default '',
  `email` varchar(32) NOT NULL default '',
  `address` varchar(100) default NULL,
  `postcode` varchar(6) default NULL,
  `level` decimal(10,2) unsigned zerofill default '00000000.00',
  `power` tinyint(3) unsigned zerofill NOT NULL default '000',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `userinfo` */

insert  into `userinfo`(`id`,`username`,`pwd`,`email`,`address`,`postcode`,`level`,`power`) values (2,'bbb','123','123@1.2','421','243151','00000000.00',001),(3,'ccc','ccc','a@b.c','bj','123456','00000000.00',002),(4,'ddd','ddd','a@b.c','bj','123456','00000000.00',001),(5,'eee','eee','eee@b.c','eee','123456','00000000.00',000),(6,'pp','pp','pp2@sina.com','fdfdf','4545','00000000.00',000);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
