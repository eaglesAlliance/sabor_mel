-- MySQL dump 10.13  Distrib 5.7.13, for Linux (x86_64)
--
-- Host: localhost    Database: sabor_mel
-- ------------------------------------------------------
-- Server version	5.7.13-0ubuntu0.16.04.2

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
-- Table structure for table `Bairro`
--

DROP TABLE IF EXISTS `Bairro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Bairro` (
  `idBairro` bigint(20) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `cidade` bigint(20) NOT NULL,
  PRIMARY KEY (`idBairro`),
  KEY `FK60r3aysiw339apo88005c6d16` (`cidade`),
  CONSTRAINT `FK60r3aysiw339apo88005c6d16` FOREIGN KEY (`cidade`) REFERENCES `Cidade` (`idCidade`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bairro`
--

LOCK TABLES `Bairro` WRITE;
/*!40000 ALTER TABLE `Bairro` DISABLE KEYS */;
INSERT INTO `Bairro` VALUES (42,'Pontal Santa Marina',41);
/*!40000 ALTER TABLE `Bairro` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `delete_cidade` 
AFTER DELETE ON `Bairro` FOR EACH ROW
BEGIN
	DECLARE numRows INT;
        SET numRows = (SELECT COUNT(*) FROM Bairro WHERE cidade = OLD.cidade);
        IF (numRows < 1) THEN
			BEGIN
				DELETE FROM Cidade WHERE idCidade = OLD.cidade;
			END;
		END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `Cidade`
--

DROP TABLE IF EXISTS `Cidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cidade` (
  `idCidade` bigint(20) NOT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `estado` bigint(20) NOT NULL,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY (`idCidade`),
  UNIQUE KEY `UK_aofy2p0mtjqp3i61tg91res6x` (`nome`),
  UNIQUE KEY `UK_5kly3fbtte6hf8ys0v92k8snu` (`cidade`),
  KEY `FKh6fr9qce1jn2uls4htno0qbf3` (`estado`),
  CONSTRAINT `FKh6fr9qce1jn2uls4htno0qbf3` FOREIGN KEY (`estado`) REFERENCES `Estado` (`idEstado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cidade`
--

LOCK TABLES `Cidade` WRITE;
/*!40000 ALTER TABLE `Cidade` DISABLE KEYS */;
INSERT INTO `Cidade` VALUES (41,NULL,1,'Caraguatatuba');
/*!40000 ALTER TABLE `Cidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Documento`
--

DROP TABLE IF EXISTS `Documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Documento` (
  `idDocumento` bigint(20) NOT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idDocumento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Documento`
--

LOCK TABLES `Documento` WRITE;
/*!40000 ALTER TABLE `Documento` DISABLE KEYS */;
INSERT INTO `Documento` VALUES (40,'340.124.578-37','CPF');
/*!40000 ALTER TABLE `Documento` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `define_tipo_documento` BEFORE INSERT ON `Documento` 
FOR EACH ROW
BEGIN
	IF(LENGTH(NEW.numero) = 14) THEN
		SET NEW.tipo = 'CPF';
	ELSE 
		SET NEW.tipo = 'CNPJ';
	END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `Endereco`
--

DROP TABLE IF EXISTS `Endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Endereco` (
  `idEndereco` bigint(20) NOT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `bairro` bigint(20) NOT NULL,
  PRIMARY KEY (`idEndereco`),
  KEY `FK8ht1pm5smxh5k65vc211hm1es` (`bairro`),
  CONSTRAINT `FK8ht1pm5smxh5k65vc211hm1es` FOREIGN KEY (`bairro`) REFERENCES `Bairro` (`idBairro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Endereco`
--

LOCK TABLES `Endereco` WRITE;
/*!40000 ALTER TABLE `Endereco` DISABLE KEYS */;
INSERT INTO `Endereco` VALUES (43,'11672-020','Rua do Contorno','199',42);
/*!40000 ALTER TABLE `Endereco` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `delete_bairro` 
AFTER DELETE ON `Endereco` FOR EACH ROW
BEGIN
	DECLARE numRows INT;
        SET numRows = (SELECT COUNT(*) FROM Endereco WHERE bairro = OLD.bairro);
        IF (numRows < 1) THEN
			BEGIN
				DELETE FROM Bairro WHERE idBairro = OLD.bairro;
			END;
		END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `Estado`
--

DROP TABLE IF EXISTS `Estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Estado` (
  `idEstado` bigint(20) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `uf` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idEstado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Estado`
--

LOCK TABLES `Estado` WRITE;
/*!40000 ALTER TABLE `Estado` DISABLE KEYS */;
INSERT INTO `Estado` VALUES (1,'São Paulo','SP'),(2,'Acre','AC'),(3,'Alagoas	','AL'),(4,'Amapá','AP'),(5,'Amazonas','AM'),(6,'Bahia','BA'),(7,'Ceará','CE'),(8,'Distrito Federal','DF'),(9,'Espírito Santo','ES'),(10,'Goiás','GO'),(11,'Maranhão','MA'),(12,'Mato Grosso','MT'),(13,'Mato Grosso do Sul','MS'),(14,'Minas Gerais','MG'),(15,'Pará','PA'),(16,'Paraíba','PB'),(17,'Paraná','PR'),(18,'Pernambuco','PE'),(19,'Piauí','PI'),(20,'Rio de Janeiro','RJ'),(21,'Rio Grande do Norte','RN'),(22,'Rio Grande do Sul','RS'),(23,'Rondônia','RO'),(24,'Roraima','RR'),(25,'Santa Catarina','SC'),(26,'Sergipe','SE'),(27,'Tocantins','TO');
/*!40000 ALTER TABLE `Estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Funcionario`
--

DROP TABLE IF EXISTS `Funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Funcionario` (
  `senha` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  `pessoa` bigint(20) NOT NULL,
  PRIMARY KEY (`pessoa`),
  CONSTRAINT `FKjswill93do4651y3iij8rvxep` FOREIGN KEY (`pessoa`) REFERENCES `Pessoa` (`idPessoa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Funcionario`
--

LOCK TABLES `Funcionario` WRITE;
/*!40000 ALTER TABLE `Funcionario` DISABLE KEYS */;
INSERT INTO `Funcionario` VALUES ('heavy666','A','tiagolima',44);
/*!40000 ALTER TABLE `Funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pessoa`
--

DROP TABLE IF EXISTS `Pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Pessoa` (
  `idPessoa` bigint(20) NOT NULL,
  `dataNascimento` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `documento` bigint(20) NOT NULL,
  `endereco` bigint(20) NOT NULL,
  PRIMARY KEY (`idPessoa`),
  KEY `FK6h305v7qflv5wsdnywfrjhtjq` (`documento`),
  KEY `FKshewtplh8m3w3an9okcvfwh6b` (`endereco`),
  CONSTRAINT `FK6h305v7qflv5wsdnywfrjhtjq` FOREIGN KEY (`documento`) REFERENCES `Documento` (`idDocumento`),
  CONSTRAINT `FKshewtplh8m3w3an9okcvfwh6b` FOREIGN KEY (`endereco`) REFERENCES `Endereco` (`idEndereco`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pessoa`
--

LOCK TABLES `Pessoa` WRITE;
/*!40000 ALTER TABLE `Pessoa` DISABLE KEYS */;
INSERT INTO `Pessoa` VALUES (44,'1988-04-21','tiago@gmail.com','Tiago Lima',40,43);
/*!40000 ALTER TABLE `Pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Telefone`
--

DROP TABLE IF EXISTS `Telefone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Telefone` (
  `idTelefone` bigint(20) NOT NULL,
  `ddd` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `pessoa` bigint(20) NOT NULL,
  PRIMARY KEY (`idTelefone`),
  KEY `FK5nmr7ehkuajcjp2py6pqnt3w5` (`pessoa`),
  CONSTRAINT `FK5nmr7ehkuajcjp2py6pqnt3w5` FOREIGN KEY (`pessoa`) REFERENCES `Pessoa` (`idPessoa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Telefone`
--

LOCK TABLES `Telefone` WRITE;
/*!40000 ALTER TABLE `Telefone` DISABLE KEYS */;
INSERT INTO `Telefone` VALUES (45,'(12)','38879006','F',44);
/*!40000 ALTER TABLE `Telefone` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `define_tipo_telefone` 
BEFORE INSERT ON `Telefone` FOR EACH ROW
BEGIN
	IF(LENGTH(NEW.numero) = 8) THEN
		SET NEW.tipo = 'F';
	ELSE
		SEt NEW.tipo = 'C';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (46),(46),(46),(46),(46),(46),(46);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-23 23:53:08
