-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: QUANLYNHAPHANG
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
-- Table structure for table `CT_NHAP`
--

DROP TABLE IF EXISTS `CT_NHAP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CT_NHAP` (
  `MAPN` int NOT NULL,
  `MASP` int NOT NULL,
  `SL` int DEFAULT NULL,
  PRIMARY KEY (`MAPN`,`MASP`),
  KEY `MASP` (`MASP`),
  CONSTRAINT `CT_NHAP_ibfk_1` FOREIGN KEY (`MAPN`) REFERENCES `PHIEUNHAP` (`MAPN`),
  CONSTRAINT `CT_NHAP_ibfk_2` FOREIGN KEY (`MASP`) REFERENCES `SANPHAM` (`MASP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CT_NHAP`
--

LOCK TABLES `CT_NHAP` WRITE;
/*!40000 ALTER TABLE `CT_NHAP` DISABLE KEYS */;
INSERT INTO `CT_NHAP` VALUES (4,1,2);
/*!40000 ALTER TABLE `CT_NHAP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NHACC`
--

DROP TABLE IF EXISTS `NHACC`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `NHACC` (
  `MANCC` int NOT NULL AUTO_INCREMENT,
  `TENNCC` varchar(255) NOT NULL,
  `DIACHI` varchar(255) DEFAULT NULL,
  `DTHOAI` varchar(15) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`MANCC`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NHACC`
--

LOCK TABLES `NHACC` WRITE;
/*!40000 ALTER TABLE `NHACC` DISABLE KEYS */;
INSERT INTO `NHACC` VALUES (1,'Hao hao','UIT','0931231','uit@gmail.com'),(2,'test','13','09312312','ok@gmail.com');
/*!40000 ALTER TABLE `NHACC` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PHIEUNHAP`
--

DROP TABLE IF EXISTS `PHIEUNHAP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PHIEUNHAP` (
  `MAPN` int NOT NULL AUTO_INCREMENT,
  `NGAYLAP` date DEFAULT NULL,
  `GHICHU` text,
  `TONGTIEN` decimal(15,2) DEFAULT NULL,
  PRIMARY KEY (`MAPN`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PHIEUNHAP`
--

LOCK TABLES `PHIEUNHAP` WRITE;
/*!40000 ALTER TABLE `PHIEUNHAP` DISABLE KEYS */;
INSERT INTO `PHIEUNHAP` VALUES (1,'2024-06-19','OK',40000.00),(2,'2024-06-26','NGON',40000.00),(3,'2024-06-26','OK',40000.00),(4,'2024-06-19','ok',40000.00);
/*!40000 ALTER TABLE `PHIEUNHAP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SANPHAM`
--

DROP TABLE IF EXISTS `SANPHAM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SANPHAM` (
  `MASP` int NOT NULL AUTO_INCREMENT,
  `TENSP` varchar(255) NOT NULL,
  `SOLUONG` int DEFAULT NULL,
  `DONGIA` decimal(10,2) DEFAULT NULL,
  `MANCC` int DEFAULT NULL,
  PRIMARY KEY (`MASP`),
  KEY `MANCC` (`MANCC`),
  CONSTRAINT `SANPHAM_ibfk_1` FOREIGN KEY (`MANCC`) REFERENCES `NHACC` (`MANCC`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SANPHAM`
--

LOCK TABLES `SANPHAM` WRITE;
/*!40000 ALTER TABLE `SANPHAM` DISABLE KEYS */;
INSERT INTO `SANPHAM` VALUES (1,'Mì gói siêu ngon',0,20000.00,1),(2,'Mì gói gấu đỏ',0,15000.00,1);
/*!40000 ALTER TABLE `SANPHAM` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-25  0:21:42
