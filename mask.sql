CREATE DATABASE  IF NOT EXISTS `mask` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mask`;
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: mask
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Table structure for table `t_appointment`
--

DROP TABLE IF EXISTS `t_appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_appointment` (
  `ap_id` int(11) NOT NULL AUTO_INCREMENT,
  `ap_name` varchar(45) DEFAULT NULL,
  `ap_begin` varchar(45) DEFAULT NULL,
  `ap_end` varchar(45) DEFAULT NULL,
  `ap_over` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ap_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_appointment`
--

LOCK TABLES `t_appointment` WRITE;
/*!40000 ALTER TABLE `t_appointment` DISABLE KEYS */;
INSERT INTO `t_appointment` VALUES (1,NULL,'2020-03-15','2020-03-15',1),(2,NULL,'2020-03-15 18:18:55','2020-03-15 18:26:55',1);
/*!40000 ALTER TABLE `t_appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_record`
--

DROP TABLE IF EXISTS `t_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_record` (
  `re_id` int(11) NOT NULL AUTO_INCREMENT,
  `re_identify` varchar(45) DEFAULT NULL,
  `re_name` varchar(45) DEFAULT NULL,
  `re_phone` varchar(45) DEFAULT NULL,
  `re_amount` int(11) DEFAULT NULL,
  `re_serial` varchar(45) DEFAULT NULL,
  `re_ap_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`re_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_record`
--

LOCK TABLES `t_record` WRITE;
/*!40000 ALTER TABLE `t_record` DISABLE KEYS */;
INSERT INTO `t_record` VALUES (1,'35xxxxxxxxxxxxxxxx','aaa','130xxxxxxxx',10,'8dd6f20d36b8c6a8196fe1d82149e6c7',2),(2,'352xxxxxxxxxxxxxxx','bbb','130xxxxxxxx',10,'3ca3df1f81744f9cbc55a6c5d1169bf5',2),(3,'352xxxxxxxxxxxxxxx','ccc','130xxxxxxxx',10,'6c2ce54ead2f89681b47b3472b7fcc90',2),(4,'352xxxxxxxxxxxxxxx','ddd','130xxxxxxxx',10,'f5416e858bcae95d8a6d8f4ebf01f51a',2);
/*!40000 ALTER TABLE `t_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_success`
--

DROP TABLE IF EXISTS `t_success`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_success` (
  `su_id` int(11) NOT NULL AUTO_INCREMENT,
  `su_identify` varchar(45) DEFAULT NULL,
  `su_serial` varchar(45) DEFAULT NULL,
  `su_ap_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`su_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_success`
--

LOCK TABLES `t_success` WRITE;
/*!40000 ALTER TABLE `t_success` DISABLE KEYS */;
INSERT INTO `t_success` VALUES (1,'35xxxxxxxxxxxxxxxx','8dd6f20d36b8c6a8196fe1d82149e6c7',2),(2,'352xxxxxxxxxxxxxxx','3ca3df1f81744f9cbc55a6c5d1169bf5',2),(3,'352xxxxxxxxxxxxxxx','6c2ce54ead2f89681b47b3472b7fcc90',2);
/*!40000 ALTER TABLE `t_success` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-15 19:35:30
