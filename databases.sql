-- MySQL dump 10.16  Distrib 10.1.41-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: db_users
-- ------------------------------------------------------
-- Server version	10.1.41-MariaDB-0+deb9u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(50) NOT NULL,
  `user` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `token_id_uindex` (`id`),
  UNIQUE KEY `token_value_uindex` (`value`),
  KEY `token_users_idUser_fk` (`user`),
  CONSTRAINT `token_users_idUser_fk` FOREIGN KEY (`user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (15,'vaov78tBs9',2),(16,'7axipa9DJF',2);
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(35) NOT NULL,
  `name` varchar(35) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `role` varchar(15) NOT NULL,
  `hash_password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_idUser_uindex` (`id`),
  UNIQUE KEY `users_login_uindex` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'login','ninatest','744444','USER','$2a$10$KWD5rN6F2pcOyJO4qb5bPe1lFFDO1Ma7q81oOgYiOwTdmOxq/7Wd2'),(2,'nina@mail.com','nina Drozdova','8-918-839-64-84','USER','$2a$10$a//wyIUM1vzh1VhGx3alru6DJJzW42etP0C6v/WNMgWmsgZQD22dW'),(3,'eto to','update is work','77','USER','$2a$10$wNcI5MjRqlvhcegFZaTqCuTTAkyU5J.nCjWWVq1urtyen1YQJ7YCy'),(4,'s@gmail.ru','nina','562-85-99','USER','$2a$10$pO9BWSLGUKRDyZoHsIcsfuSCwPKVDucrPBXLVJ4AqCJ794NVvCV2S'),(5,'eto','update is work','8888888','USER','$2a$10$L1smbsStrT9izctuGJ5y4e27SPKgBaEG1YT2nZ3vfeko.BugrTEuO'),(6,'Nikita','nik','562-85-99','ADMIN','$2a$10$0dmcBb2bL2fXKR6zYq8Qju5R7SYrSKSWHb9SUIaKAQWi89tHGRcgK'),(7,'nnnn','nikita','8-800-535-35-35','USER','$2a$10$UUDKWpjbeHudy/135OPPO.1bdPQspJ3IRY9TW0VI3UQ.pkP70tEOq'),(8,'testTest','test','4552','USER','$2a$10$ML6/yjFf.z6KSvcn2UzhIerixawDhfKT1Wp3spY54158u9.M7Dzpi'),(9,'nina1','test','4552','USER','$2a$10$7VeGNUJmCbufv1OiVPmeA.oSFgDU28t.dW7nlKgpSO9.UObfX6Ro6'),(10,'nina2','test','4552','USER','$2a$10$JHCbAd1a.tAPNTF3sOzo0e6reNKp/F9YPnYmuOMnQOZ3Ds5qe2vmi'),(11,'nina3','test','4552','USER','$2a$10$nXlV11cWHEDVL/jBPGKwTuPDL5QdSQH2mFoU7sXFbjOhpB4hEOVT.'),(12,'nina4','test','4552','USER','$2a$10$NWeq8NbC8aR70iMkOsNLjeWjXTrK2vZnDQrTVMlXgInmvzlCA8Thi'),(13,'nina5','test','4552','USER','$2a$10$BB.SHkivwYUjSdtFcq90HOPK8FLFUw8ST1WPkAwfOk.T3vVCwrCRK'),(14,'nina6','test','4552','USER','$2a$10$dq3RxIXYRXJkxr4HyWkQretFowX32fKbadAY.6ojfrDOwTdC9wtvu'),(15,'nina7','test','4552','USER','$2a$10$vx4wo0h/KKMnWurLaw1UzucsAvj1fAAfpAXH2xquc.F2DQQbSRMZ6'),(16,'nina8','test','4552','USER','$2a$10$En9cDyG.H7w.mT0pQIlNJ.PcGY3AvMK.m7kLoQ9fCETeyWfzm1YR.'),(17,'nina9','test','4552','USER','$2a$10$q2GDgmbvZIlxSNdoNCgxDOOODzACvLKeTCXK3vtCX3bU4bSW8kmqa'),(18,'nina10','test','4552','USER','$2a$10$uqnB03HsarfPh59ntZphi.Kmf29UNDiBaDPufLFJs9Xytd/ViKafa'),(19,'nina11','test','4552','USER','$2a$10$XbmoGjy7eLzsU0r1JulmLOEx145ev/wjGvvOeINbHwpm5tZ/AMXx6'),(20,'nina12','test','4552','USER','$2a$10$JNcF9OUZsZTiGoOBBL4nju10zDiyW8e5ASgtrV8ruvNBpvV8ZRbCC'),(21,'nina13','test','4552','USER','$2a$10$LodJmsSfToWQZBmwoMoF3uc6WgOSSHDC0P47XNFViBtiaIcevPH7C'),(22,'nina14','test','4552','USER','$2a$10$1nuW8Jh7eFiEQN/egR7Qdudywc6p9X9PnX8yRrzcXzj1TKRXf2Hkm'),(23,'nina15','test','4552','USER','$2a$10$nrYsd5DLKfPJPgcE33wNC.3PNjMk1M7bWGCUehq4XYOUzrc3AnQW6');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-22 19:44:55
