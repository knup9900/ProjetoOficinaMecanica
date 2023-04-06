-- MariaDB dump 10.19  Distrib 10.4.27-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: oficina
-- ------------------------------------------------------
-- Server version	10.4.27-MariaDB

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (0,'gerente','xxxxxxxxx','(99)9999-9999',0),(1,'Tiago','Cidade belo horizonte rua 1 bairro 1 n1','(99)9999-9999',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (1,'root','rootroot',0),(2,'tiago','qweasd',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (1,'óleo',25.00,0,0),(2,'fluido de radiador',16.50,1,0),(3,'lixa 320',3.00,6,1),(4,'lixa 400',3.00,4,1);
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pv_produto`
--

LOCK TABLES `pv_produto` WRITE;
/*!40000 ALTER TABLE `pv_produto` DISABLE KEYS */;
INSERT INTO `pv_produto` VALUES (24,1,1,25.00),(27,2,1,16.50);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recibo`
--

LOCK TABLES `recibo` WRITE;
/*!40000 ALTER TABLE `recibo` DISABLE KEYS */;
INSERT INTO `recibo` VALUES (3,'DINHEIRO',24),(4,'DINHEIRO',25),(5,'DINHEIRO',26),(6,'DINHEIRO',27);
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servico`
--

LOCK TABLES `servico` WRITE;
/*!40000 ALTER TABLE `servico` DISABLE KEYS */;
INSERT INTO `servico` VALUES (12,230.00,'troca de óleo',0),(13,200.00,'alinhamento e balanciamento',0),(14,220.00,'manutencao embreagem',0),(15,130.00,'revisao do freio',0);
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sv_servico`
--

LOCK TABLES `sv_servico` WRITE;
/*!40000 ALTER TABLE `sv_servico` DISABLE KEYS */;
INSERT INTO `sv_servico` VALUES (24,12,'troca de óleo',230.00),(25,13,'alinhamento e balanciamento',200.00),(26,13,'alinhamento e balanciamento',200.00),(26,14,'manutencao embreagem',220.00),(26,15,'revisao do freio',130.00),(27,14,'manutencao embreagem',220.00),(27,15,'revisao do freio',130.00);
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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venda`
--

LOCK TABLES `venda` WRITE;
/*!40000 ALTER TABLE `venda` DISABLE KEYS */;
INSERT INTO `venda` VALUES (24,'marcelo','aaa-1111','fiat',255.00,0,'Pago','2023-04-05',0),(25,'joao','bbb-2222','gol',200.00,0,'Pago','2023-04-05',0),(26,'francisco','zzz-9999','palio',550.00,0,'Pago','2023-04-05',0),(27,'Joao','aaa-1111','palio',366.50,0,'Não Pago','2023-04-05',0);
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

-- Dump completed on 2023-04-05 22:15:16
