/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.6.24 : Database - kaka_app
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`kaka_app` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `kaka_app`;

/*Table structure for table `t_department` */

DROP TABLE IF EXISTS `t_department`;

CREATE TABLE `t_department` (
  `departmentid` varchar(32) NOT NULL,
  `departmentname` varchar(25) DEFAULT NULL,
  `siteid` varchar(32) DEFAULT NULL,
  `details` text,
  PRIMARY KEY (`departmentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_department` */

insert  into `t_department`(`departmentid`,`departmentname`,`siteid`,`details`) values ('da82428590e5459b96eea0d414f1d0a6','可视化与移动产品研发部','02716c8de1fc4c3381c8ef7ae1fd37bb','这个是部门描述');

/*Table structure for table `t_job` */

DROP TABLE IF EXISTS `t_job`;

CREATE TABLE `t_job` (
  `jobid` varchar(32) NOT NULL,
  `jobname` varchar(25) DEFAULT NULL,
  `siteid` varchar(32) DEFAULT NULL,
  `details` text,
  PRIMARY KEY (`jobid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_job` */

insert  into `t_job`(`jobid`,`jobname`,`siteid`,`details`) values ('f2849e1b84ce4a7487613b9785d5dea0','后端研发','','员工描述'),('f2849e1b84ce4a7487613b9785d5dea1','前端',NULL,'员工描述');

/*Table structure for table `t_site` */

DROP TABLE IF EXISTS `t_site`;

CREATE TABLE `t_site` (
  `siteid` varchar(32) NOT NULL,
  `sitename` varchar(25) DEFAULT NULL,
  `sitecode` varchar(32) DEFAULT NULL,
  `createtime` varchar(25) DEFAULT NULL,
  `details` text,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`siteid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_site` */

insert  into `t_site`(`siteid`,`sitename`,`sitecode`,`createtime`,`details`,`status`) values ('02716c8de1fc4c3381c8ef7ae1fd37bb','四川电视台','SCTV','2018-02-28 16:12:25','可视化与移动产品研发部，主要专注大数据挖掘，及移动产品的设计研发',0);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `userid` varchar(32) NOT NULL,
  `headimg` text,
  `username` varchar(25) DEFAULT NULL,
  `phone` varchar(25) DEFAULT NULL,
  `pwd` varchar(32) DEFAULT NULL,
  `registtime` varchar(25) DEFAULT NULL,
  `lastlogintime` varchar(25) DEFAULT NULL,
  `devicetype` varchar(25) DEFAULT NULL,
  `appversion` varchar(32) DEFAULT NULL,
  `devicemodel` varchar(32) DEFAULT NULL,
  `deviceId` varchar(32) DEFAULT NULL,
  `token` varchar(300) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `emil` varchar(64) DEFAULT NULL,
  `loginnumber` bigint(20) DEFAULT NULL,
  `loginstatus` int(11) DEFAULT NULL,
  `disksize` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`userid`,`headimg`,`username`,`phone`,`pwd`,`registtime`,`lastlogintime`,`devicetype`,`appversion`,`devicemodel`,`deviceId`,`token`,`status`,`emil`,`loginnumber`,`loginstatus`,`disksize`) values ('2a2c94a9f30b4ce698fe886477af94e7','http://172.16.145.51:8088/MHQ/reception/static/imgs/clue/2.jpg','谭帅','13088094976','616823670','2018-02-28 15:40:43','2018-03-01 17:50:15','ios','1.0.0','Apple 7','uudb20817jliyuyt','MmEyYzk0YTlmMzBiNGNlNjk4ZmU4ODY0NzdhZjk0ZTcmMTUxOTg5NzgxNTk4NyZ1dWRiMjA4MTdqbGl5dXl0',1,'616823670@qq.com',50,1,10240),('b8ccbb7ccf4f486ab209ef8723195fef',NULL,NULL,'18380483924','6c5410b4585649bbe5668987ee6613f5','2018-03-01 18:19:58','2018-03-01 18:19:58','ios','1.0.0','Apple 6s','this phoneid',NULL,0,NULL,1,1,NULL);

/*Table structure for table `t_user_site` */

DROP TABLE IF EXISTS `t_user_site`;

CREATE TABLE `t_user_site` (
  `userid` varchar(32) DEFAULT NULL,
  `siteid` varchar(32) DEFAULT NULL,
  `jobid` varchar(32) DEFAULT NULL,
  `departmentid` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user_site` */

insert  into `t_user_site`(`userid`,`siteid`,`jobid`,`departmentid`) values ('2a2c94a9f30b4ce698fe886477af94e7','02716c8de1fc4c3381c8ef7ae1fd37bb','f2849e1b84ce4a7487613b9785d5dea1','da82428590e5459b96eea0d414f1d0a6');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
