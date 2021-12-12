-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: school
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `first_lastname` varchar(45) NOT NULL,
  `second_lastname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `picture` varchar(200) NOT NULL,
  `enrollment` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `level` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Amauri Update','Guzmán','Hernández','amalip@gmail.com','https://images.ole.com.ar/2021/10/22/fd6_DKecj_1200x630__1.jpg','019606','Password1234',1),(2,'Luis','Cuellar','Algo','luis@gmail.com','https://media.revistagq.com/photos/5f08145242f91a64e56904be/4:3/w_1440,h_1080,c_limit/avatar_aang.jpg','019605','Password123',1),(3,'Gina','Cruz','Algo','gina@gmail.com','https://static.wikia.nocookie.net/avatar/images/f/fa/Aang_en_el_Drag%C3%B3n_del_Jazm%C3%ADn.png/revision/latest/smart/width/250/height/250?cb=20150414231706&path-prefix=es','019604','Password123',1),(4,'Estudiante','One','Algo','stud1@gmail.com','https://static4.abc.es/media/play/2020/09/29/avatar-kE4H--1024x512@abc.jpeg','011111','Password123',2),(5,'Estudiante Modified','Two','Algo','stud2@gmail.com','https://phantom-marca.unidadeditorial.es/305edc092a3ad9919d4d3b71e123dbb7/assets/multimedia/imagenes/2020/05/28/15906513136141.jpg','022222','Password123',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-11 20:06:38
