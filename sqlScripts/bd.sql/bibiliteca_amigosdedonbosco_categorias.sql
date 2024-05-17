-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bibiliteca_amigosdedonbosco
-- ------------------------------------------------------
-- Server version	5.7.44-log

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
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `curso_id` varchar(6) NOT NULL,
  `curso_name` varchar(255) NOT NULL,
  `carrera_id` varchar(6) NOT NULL,
  PRIMARY KEY (`curso_id`),
  KEY `FK_categorias_carreras` (`carrera_id`),
  CONSTRAINT `FK_categorias_carreras` FOREIGN KEY (`carrera_id`) REFERENCES `carreras` (`carrera_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES ('ADM001','Gestión Estratégica','ADM001'),('ADM002','Finanzas Corporativas','ADM001'),('CIV001','Introducción al Derecho','DER001'),('CIV002','Derecho de Familia','DER001'),('CLI001','Evaluación Psicológica','PSI001'),('CLI002','Terapia Cognitivo-Conductual','PSI001'),('COM001','Derecho Comercial Internacional','DER003'),('COM002','Contratos Comerciales','DER003'),('CON001','Contabilidad Avanzada','ADM002'),('CON002','Auditoría Contable','ADM002'),('EDU001','Psicología del Aprendizaje','PSI002'),('EDU002','Orientación Vocacional','PSI002'),('ENF001','Cuidados Intensivos','MED002'),('ENF002','Salud Pública','MED002'),('MAR001','Investigación de Mercados','ADM003'),('MAR002','Marketing Digital','ADM003'),('NUT001','Nutrición Clínica','MED003'),('NUT002','Nutrición Deportiva','MED003'),('ORG001','Liderazgo y Motivación','PSI003'),('ORG002','Gestión del Talento Humano','PSI003'),('PEN001','Derecho Penal I','DER002'),('PEN002','Criminología','DER002');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-17 17:36:33
