-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: oficinamecanica
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.38-MariaDB

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
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionario` (
  `cdfuncionario` int(11) NOT NULL AUTO_INCREMENT,
  `nmfuncionario` varchar(150) NOT NULL,
  `endereco` varchar(4000) NOT NULL,
  `tel` varchar(14) DEFAULT NULL,
  `obsoleto` tinyint(4) NOT NULL,
  PRIMARY KEY (`cdfuncionario`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (0,'gerente','xxxxxxxxx','(99)9999-9999',0),(1,'Tiago','Cidade comida rua batata bairro frita n67','(99)9999-9999',0),(2,'Gabriell','Cidade comida rua sopa bairro macarro n813','(11)1111-1111',1),(3,'fg','fsdg','(11)1111-1111',1),(5,'dollynho','Inferno','(66)66666-6666',0),(6,'zé ninguem','de baixo da ponte','(00)00000-0000',1);
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `idlogin` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `cdfuncionario` int(11) NOT NULL,
  PRIMARY KEY (`idlogin`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (1,'root','rootroot',0),(2,'tiago','qweasd',1),(3,'a','123',1),(4,'fg','123',3);
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto` (
  `cdproduto` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(150) NOT NULL,
  `preco` decimal(9,2) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `obsoleto` tinyint(4) NOT NULL,
  PRIMARY KEY (`cdproduto`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (1,'óleo',10.00,1,0),(2,'fluido de radiador',30.00,2,0),(3,'lixa 320',1.00,6,0),(4,'lixa 400',1.00,4,0),(5,'lixa 1200',1.00,8,1),(6,'algo de bom',10.00,15,1);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pv_produto`
--

DROP TABLE IF EXISTS `pv_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pv_produto` (
  `cdvenda` int(11) NOT NULL,
  `cdproduto` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `precocada` decimal(9,2) NOT NULL,
  PRIMARY KEY (`cdvenda`,`cdproduto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pv_produto`
--

LOCK TABLES `pv_produto` WRITE;
/*!40000 ALTER TABLE `pv_produto` DISABLE KEYS */;
INSERT INTO `pv_produto` VALUES (4,5,1,0.00),(5,1,1,0.00),(7,3,3,1.00),(16,1,1,10.00),(18,3,3,1.00),(20,3,3,1.00),(22,2,1,30.00),(22,3,1,1.00),(23,4,1,1.00);
/*!40000 ALTER TABLE `pv_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recibo`
--

DROP TABLE IF EXISTS `recibo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recibo` (
  `cdrecibo` int(11) NOT NULL AUTO_INCREMENT,
  `formapag` varchar(45) NOT NULL,
  `cdvenda` int(11) NOT NULL,
  PRIMARY KEY (`cdrecibo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recibo`
--

LOCK TABLES `recibo` WRITE;
/*!40000 ALTER TABLE `recibo` DISABLE KEYS */;
INSERT INTO `recibo` VALUES (1,'DINHEIRO',21),(2,'DINHEIRO',23);
/*!40000 ALTER TABLE `recibo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servico`
--

DROP TABLE IF EXISTS `servico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servico` (
  `cdservico` int(11) NOT NULL AUTO_INCREMENT,
  `valor` decimal(9,2) NOT NULL,
  `descricao` varchar(45) NOT NULL,
  `obsoleto` tinyint(4) NOT NULL,
  PRIMARY KEY (`cdservico`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servico`
--

LOCK TABLES `servico` WRITE;
/*!40000 ALTER TABLE `servico` DISABLE KEYS */;
INSERT INTO `servico` VALUES (1,50.00,'Sangria',0),(3,123.00,'servico2',0),(4,544.00,'servico3',0),(5,123.00,'servico4',0),(6,10.00,'algo de util talvez',1),(7,10.50,'algo',0),(8,10.50,'algo',0),(9,5.57,'algo3',1),(10,10.00,'seila',0),(11,10.57,'seiladnv',0);
/*!40000 ALTER TABLE `servico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sv_servico`
--

DROP TABLE IF EXISTS `sv_servico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sv_servico` (
  `cdvenda` int(11) NOT NULL,
  `cdservico` int(11) NOT NULL,
  `descricao` varchar(45) NOT NULL,
  `preco` decimal(9,2) NOT NULL,
  PRIMARY KEY (`cdvenda`,`cdservico`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sv_servico`
--

LOCK TABLES `sv_servico` WRITE;
/*!40000 ALTER TABLE `sv_servico` DISABLE KEYS */;
INSERT INTO `sv_servico` VALUES (3,3,'servico2',0.00),(4,5,'servico4',0.00),(5,1,'Sangria',0.00),(16,1,'Sangria',50.00),(16,5,'servico4',123.00),(18,1,'Sangria',50.00),(18,4,'servico3',544.00),(19,11,'seiladnv',10.57),(20,4,'servico3',544.00),(21,1,'Sangria',50.00),(21,5,'servico4',123.00),(22,8,'algo',10.50),(23,4,'servico3',544.00);
/*!40000 ALTER TABLE `sv_servico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venda`
--

DROP TABLE IF EXISTS `venda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venda` (
  `cdvenda` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(150) NOT NULL,
  `placacarro` char(8) NOT NULL,
  `modelo` varchar(45) NOT NULL,
  `precototal` decimal(9,2) NOT NULL,
  `cdfuncionario` int(11) NOT NULL,
  `situacao` varchar(45) NOT NULL,
  `datavenda` date NOT NULL,
  `obsoleto` tinyint(4) NOT NULL,
  PRIMARY KEY (`cdvenda`),
  KEY `cdfuncionario` (`cdfuncionario`),
  CONSTRAINT `venda_ibfk_1` FOREIGN KEY (`cdfuncionario`) REFERENCES `funcionario` (`cdfuncionario`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venda`
--

LOCK TABLES `venda` WRITE;
/*!40000 ALTER TABLE `venda` DISABLE KEYS */;
INSERT INTO `venda` VALUES (19,'batata','xxx-1111','asd',10.57,0,'Pago','2019-11-24',0),(20,'joaozinho','xxx-1111','fuscao',547.00,0,'Não Pago','2019-11-24',0),(21,'tijolinho','aaa-0000','corola',173.00,0,'Não Pago','2019-11-24',0),(22,'josé','xxx-1111','batata',41.50,0,'Não Pago','2019-11-26',0),(23,'tomate','xxx-1111','batata frita',545.00,0,'Não Pago','2019-11-26',0);
/*!40000 ALTER TABLE `venda` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-26 21:35:03
