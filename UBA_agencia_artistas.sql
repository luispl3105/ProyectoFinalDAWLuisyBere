CREATE DATABASE  IF NOT EXISTS `UBA_agencia_artistas` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `UBA_agencia_artistas`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: UBA_agencia_artistas
-- ------------------------------------------------------
-- Server version	9.1.0

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
-- Table structure for table `EXPOSICION`
--

DROP TABLE IF EXISTS `EXPOSICION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `EXPOSICION` (
  `EXP_ID` int NOT NULL AUTO_INCREMENT,
  `EXP_NOM` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `EXP_INICIO` date DEFAULT NULL,
  `EXP_FIN` date DEFAULT NULL,
  `GAL_ID` int DEFAULT NULL,
  PRIMARY KEY (`EXP_ID`),
  KEY `GAL_ID` (`GAL_ID`),
  CONSTRAINT `EXPOSICION_ibfk_1` FOREIGN KEY (`GAL_ID`) REFERENCES `GALERIA` (`GAL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EXPOSICION`
--

LOCK TABLES `EXPOSICION` WRITE;
/*!40000 ALTER TABLE `EXPOSICION` DISABLE KEYS */;
INSERT INTO `EXPOSICION` VALUES (1,'Exposición de Arte Contemporáneo','2023-01-01','2023-02-01',1);
/*!40000 ALTER TABLE `EXPOSICION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GALERIA`
--

DROP TABLE IF EXISTS `GALERIA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `GALERIA` (
  `GAL_ID` int NOT NULL AUTO_INCREMENT,
  `GAL_NOM` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `GAL_DIRECCION` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `GAL_TELEFONO` varchar(15) COLLATE utf8mb4_bin DEFAULT NULL,
  `GAL_EMAIL` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `GAL_SITIO_WEB` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`GAL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GALERIA`
--

LOCK TABLES `GALERIA` WRITE;
/*!40000 ALTER TABLE `GALERIA` DISABLE KEYS */;
INSERT INTO `GALERIA` VALUES (1,'Galería de Arte Moderno','123 Calle Falsa, Ciudad','987654321','contacto@galeria.com','www.galeria.com');
/*!40000 ALTER TABLE `GALERIA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PINTOR`
--

DROP TABLE IF EXISTS `PINTOR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PINTOR` (
  `PINTOR_ID` int NOT NULL AUTO_INCREMENT,
  `PINTOR_NOM` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `PINTOR_CIU_ORIGEN` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `PINTOR_FECH_NACIMIENTO` date DEFAULT NULL,
  `PINTOR_TELEFONO` varchar(15) COLLATE utf8mb4_bin DEFAULT NULL,
  `PINTOR_CORR_ELECTRONICO` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`PINTOR_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PINTOR`
--

LOCK TABLES `PINTOR` WRITE;
/*!40000 ALTER TABLE `PINTOR` DISABLE KEYS */;
INSERT INTO `PINTOR` VALUES (1,'Pablo Picasso','Málaga','1881-10-25','123456789','picasso@art.com'),(2,'Vincent van Gogh','Zundert','1853-03-30','987654321','vangogh@art.com'),(3,'Leonardo da Vinci','Vinci','1452-04-15','123123123','davinci@art.com'),(4,'Claude Monet','Paris','1840-11-14','321321321','monet@art.com'),(5,'Salvador Dalí','Figueres','1904-05-11','456456456','dali@art.com'),(6,'Frida Kahlo','Coyoacán','1907-07-06','789789789','kahlo@art.com'),(7,'Rembrandt','Leiden','1606-07-15','654654654','rembrandt@art.com'),(8,'Michelangelo','Caprese','1475-03-06','987987987','michelangelo@art.com'),(9,'Jackson Pollock','Cody','1912-01-28','321654987','pollock@art.com'),(10,'Georgia O\'Keeffe','Sun Prairie','1887-11-15','654987321','okeeffe@art.com'),(11,'Henri Matisse','Le Cateau-Cambrésis','1869-12-31','789123456','matisse@art.com');
/*!40000 ALTER TABLE `PINTOR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PINTURA`
--

DROP TABLE IF EXISTS `PINTURA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PINTURA` (
  `PIN_ID` int NOT NULL AUTO_INCREMENT,
  `PIN_NOM` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `PIN_FECH_CREACION` date DEFAULT NULL,
  `PIN_VALOR_ESTIMADO` decimal(15,2) DEFAULT NULL,
  `PINTOR_ID` int DEFAULT NULL,
  `TEC_ID` int DEFAULT NULL,
  PRIMARY KEY (`PIN_ID`),
  KEY `PINTOR_ID` (`PINTOR_ID`),
  KEY `TEC_ID` (`TEC_ID`),
  CONSTRAINT `PINTURA_ibfk_1` FOREIGN KEY (`PINTOR_ID`) REFERENCES `PINTOR` (`PINTOR_ID`),
  CONSTRAINT `PINTURA_ibfk_2` FOREIGN KEY (`TEC_ID`) REFERENCES `TECNICA` (`TEC_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PINTURA`
--

LOCK TABLES `PINTURA` WRITE;
/*!40000 ALTER TABLE `PINTURA` DISABLE KEYS */;
INSERT INTO `PINTURA` VALUES (1,'Mona Lisaa','1503-01-01',870000.00,1,1),(2,'Starry Night','1889-06-01',10000.00,2,2),(3,'The Persistence of Memory','1931-04-01',60000.00,3,3),(4,'The Scream','1893-01-01',120000.00,4,4),(5,'Girl with a Pearl Earring','1665-01-01',70000.00,5,5);
/*!40000 ALTER TABLE `PINTURA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TECNICA`
--

DROP TABLE IF EXISTS `TECNICA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TECNICA` (
  `TEC_ID` int NOT NULL AUTO_INCREMENT,
  `TEC_NOM` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`TEC_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TECNICA`
--

LOCK TABLES `TECNICA` WRITE;
/*!40000 ALTER TABLE `TECNICA` DISABLE KEYS */;
INSERT INTO `TECNICA` VALUES (1,'óleo'),(2,'Acuarela'),(3,'Tinta'),(4,'Lápiz'),(5,'Acrílico'),(6,'Pastel'),(7,'Carbón'),(8,'Fresco'),(9,'Témpera'),(10,'Grisalla'),(11,'Esmalte');
/*!40000 ALTER TABLE `TECNICA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USUARIOS`
--

DROP TABLE IF EXISTS `USUARIOS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `USUARIOS` (
  `USU_ID` int NOT NULL AUTO_INCREMENT,
  `USU_USERNAME` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`USU_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USUARIOS`
--

LOCK TABLES `USUARIOS` WRITE;
/*!40000 ALTER TABLE `USUARIOS` DISABLE KEYS */;
INSERT INTO `USUARIOS` VALUES (1,'admin','49eb5c4b494de38845d29aa95dda103b');
/*!40000 ALTER TABLE `USUARIOS` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-02 11:39:54
