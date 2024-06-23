-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: qlkb
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
-- Table structure for table `BACSI`
--

DROP TABLE IF EXISTS `BACSI`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BACSI` (
  `MABS` char(4) NOT NULL,
  `TENBS` varchar(20) NOT NULL,
  PRIMARY KEY (`MABS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BACSI`
--

LOCK TABLES `BACSI` WRITE;
/*!40000 ALTER TABLE `BACSI` DISABLE KEYS */;
INSERT INTO `BACSI` VALUES ('1','Duy Hiếu');
/*!40000 ALTER TABLE `BACSI` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BENHNHAN`
--

DROP TABLE IF EXISTS `BENHNHAN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BENHNHAN` (
  `MABN` char(4) NOT NULL,
  `TENBN` varchar(20) NOT NULL,
  `NGSINH` date NOT NULL,
  `DCHI` varchar(50) DEFAULT NULL,
  `DTHOAI` varchar(10) DEFAULT NULL,
  `GIOITINH` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`MABN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BENHNHAN`
--

LOCK TABLES `BENHNHAN` WRITE;
/*!40000 ALTER TABLE `BENHNHAN` DISABLE KEYS */;
INSERT INTO `BENHNHAN` VALUES ('BN1','Thinh','2024-06-04','UIT','09213123',1);
/*!40000 ALTER TABLE `BENHNHAN` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DICHVU`
--

DROP TABLE IF EXISTS `DICHVU`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DICHVU` (
  `MADV` char(6) NOT NULL,
  `TENDV` varchar(50) NOT NULL,
  `DONGIA` bigint NOT NULL,
  PRIMARY KEY (`MADV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DICHVU`
--

LOCK TABLES `DICHVU` WRITE;
/*!40000 ALTER TABLE `DICHVU` DISABLE KEYS */;
INSERT INTO `DICHVU` VALUES ('1','SỦA DÙM',20);
/*!40000 ALTER TABLE `DICHVU` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `KHAMBENH`
--

DROP TABLE IF EXISTS `KHAMBENH`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `KHAMBENH` (
  `MAKB` int NOT NULL AUTO_INCREMENT,
  `MABN` char(4) NOT NULL,
  `MABS` char(4) NOT NULL,
  `NGAYKHAM` date NOT NULL,
  `YEUCAUKHAM` varchar(50) DEFAULT NULL,
  `KETLUAN` varchar(100) DEFAULT NULL,
  `THANHTOAN` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`MAKB`),
  KEY `MABN` (`MABN`),
  KEY `MABS` (`MABS`),
  CONSTRAINT `KHAMBENH_ibfk_1` FOREIGN KEY (`MABN`) REFERENCES `BENHNHAN` (`MABN`),
  CONSTRAINT `KHAMBENH_ibfk_2` FOREIGN KEY (`MABS`) REFERENCES `BACSI` (`MABS`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `KHAMBENH`
--

LOCK TABLES `KHAMBENH` WRITE;
/*!40000 ALTER TABLE `KHAMBENH` DISABLE KEYS */;
INSERT INTO `KHAMBENH` VALUES (1,'BN1','1','2024-06-26','Ói',NULL,1);
/*!40000 ALTER TABLE `KHAMBENH` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `THUPHI`
--

DROP TABLE IF EXISTS `THUPHI`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `THUPHI` (
  `MAKB` int NOT NULL,
  `MADV` char(6) NOT NULL,
  `SOLUONG` int NOT NULL,
  `THANHTIEN` bigint NOT NULL,
  PRIMARY KEY (`MAKB`,`MADV`),
  KEY `MADV` (`MADV`),
  CONSTRAINT `THUPHI_ibfk_1` FOREIGN KEY (`MAKB`) REFERENCES `KHAMBENH` (`MAKB`),
  CONSTRAINT `THUPHI_ibfk_2` FOREIGN KEY (`MADV`) REFERENCES `DICHVU` (`MADV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `THUPHI`
--

LOCK TABLES `THUPHI` WRITE;
/*!40000 ALTER TABLE `THUPHI` DISABLE KEYS */;
INSERT INTO `THUPHI` VALUES (1,'1',1,20);
/*!40000 ALTER TABLE `THUPHI` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-23 23:26:42
