-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: QLGV_2024
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
-- Table structure for table `GIANGVIEN`
--

DROP TABLE IF EXISTS `GIANGVIEN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `GIANGVIEN` (
  `MAGIANGVIEN` char(10) NOT NULL,
  `TENGIANGVIEN` varchar(100) DEFAULT NULL,
  `GIOITINH` char(1) DEFAULT NULL,
  `MAKHOA` char(10) DEFAULT NULL,
  PRIMARY KEY (`MAGIANGVIEN`),
  KEY `MAKHOA` (`MAKHOA`),
  CONSTRAINT `GIANGVIEN_ibfk_1` FOREIGN KEY (`MAKHOA`) REFERENCES `KHOA` (`MAKHOA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GIANGVIEN`
--

LOCK TABLES `GIANGVIEN` WRITE;
/*!40000 ALTER TABLE `GIANGVIEN` DISABLE KEYS */;
INSERT INTO `GIANGVIEN` VALUES ('GV_034','Nguyễn B','0','HTTT'),('GV_1','Huỳnh Văn A','1','1');
/*!40000 ALTER TABLE `GIANGVIEN` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `KHOA`
--

DROP TABLE IF EXISTS `KHOA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `KHOA` (
  `MAKHOA` char(10) NOT NULL,
  `TENKHOA` varchar(100) DEFAULT NULL,
  `MATRUONG` char(10) DEFAULT NULL,
  PRIMARY KEY (`MAKHOA`),
  KEY `MATRUONG` (`MATRUONG`),
  CONSTRAINT `KHOA_ibfk_1` FOREIGN KEY (`MATRUONG`) REFERENCES `TRUONG` (`MATRUONG`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `KHOA`
--

LOCK TABLES `KHOA` WRITE;
/*!40000 ALTER TABLE `KHOA` DISABLE KEYS */;
INSERT INTO `KHOA` VALUES ('1','CNPM','UIT'),('HTTT','Hệ thống thông tin','UIT');
/*!40000 ALTER TABLE `KHOA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TRUONG`
--

DROP TABLE IF EXISTS `TRUONG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TRUONG` (
  `MATRUONG` char(10) NOT NULL,
  `TENTRUONG` varchar(100) DEFAULT NULL,
  `DIACHI` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`MATRUONG`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TRUONG`
--

LOCK TABLES `TRUONG` WRITE;
/*!40000 ALTER TABLE `TRUONG` DISABLE KEYS */;
INSERT INTO `TRUONG` VALUES ('UIT','ĐH CNTT','Bình Dương');
/*!40000 ALTER TABLE `TRUONG` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-25 16:58:49
