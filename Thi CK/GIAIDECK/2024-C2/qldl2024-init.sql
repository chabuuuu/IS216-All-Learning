-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: QLDL_2024
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `CHITIET`
--

DROP TABLE IF EXISTS `CHITIET`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CHITIET` (
  `MATOUR` int NOT NULL,
  `MADDL` int NOT NULL,
  PRIMARY KEY (`MATOUR`,`MADDL`),
  KEY `MADDL` (`MADDL`),
  CONSTRAINT `CHITIET_ibfk_1` FOREIGN KEY (`MATOUR`) REFERENCES `TOUR` (`MATOUR`),
  CONSTRAINT `CHITIET_ibfk_2` FOREIGN KEY (`MADDL`) REFERENCES `DIEMDL` (`MADDL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `DIEMDL`
--

DROP TABLE IF EXISTS `DIEMDL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DIEMDL` (
  `MADDL` int NOT NULL AUTO_INCREMENT,
  `TENDDL` varchar(100) DEFAULT NULL,
  `MATTP` int DEFAULT NULL,
  `DACTRUNG` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`MADDL`),
  KEY `MATTP` (`MATTP`),
  CONSTRAINT `DIEMDL_ibfk_1` FOREIGN KEY (`MATTP`) REFERENCES `TINHTP` (`MATTP`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TINHTP`
--

DROP TABLE IF EXISTS `TINHTP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TINHTP` (
  `MATTP` int NOT NULL AUTO_INCREMENT,
  `TENTTP` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`MATTP`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TOUR`
--

DROP TABLE IF EXISTS `TOUR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TOUR` (
  `MATOUR` int NOT NULL AUTO_INCREMENT,
  `TENTOUR` varchar(100) DEFAULT NULL,
  `NGAYKHOIHANH` date DEFAULT NULL,
  `SONGAY` int DEFAULT NULL,
  `SODEM` int DEFAULT NULL,
  `GIA` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`MATOUR`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-25 18:10:56
