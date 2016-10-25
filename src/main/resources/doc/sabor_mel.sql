-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 25-Out-2016 às 20:32
-- Versão do servidor: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `sabor_mel`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `bairro`
--

CREATE TABLE IF NOT EXISTS `bairro` (
  `idBairro` bigint(20) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `cidade` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `bairro`
--

INSERT INTO `bairro` (`idBairro`, `nome`, `cidade`) VALUES
(30, 'Pontal Santa Marina', 29),
(72, 'Itaim Bibi', 71);

--
-- Acionadores `bairro`
--
DELIMITER //
CREATE TRIGGER `delete_cidade` AFTER DELETE ON `bairro`
 FOR EACH ROW BEGIN
	DECLARE numRows INT;
        SET numRows = (SELECT COUNT(*) FROM Bairro WHERE cidade = OLD.cidade);
        IF (numRows < 1) THEN
			BEGIN
				DELETE FROM Cidade WHERE idCidade = OLD.cidade;
			END;
		END IF;
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cidade`
--

CREATE TABLE IF NOT EXISTS `cidade` (
  `idCidade` bigint(20) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `estado` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cidade`
--

INSERT INTO `cidade` (`idCidade`, `nome`, `estado`) VALUES
(29, 'Caraguatatuba', 1),
(71, 'São Paulo', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `documento`
--

CREATE TABLE IF NOT EXISTS `documento` (
  `idDocumento` bigint(20) NOT NULL,
  `numero` varchar(18) NOT NULL,
  `tipo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `documento`
--

INSERT INTO `documento` (`idDocumento`, `numero`, `tipo`) VALUES
(28, '340.124.578-37', 0),
(70, '521.457.858-39', 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `endereco`
--

CREATE TABLE IF NOT EXISTS `endereco` (
  `idEndereco` bigint(20) NOT NULL,
  `cep` varchar(9) NOT NULL,
  `logradouro` varchar(100) NOT NULL,
  `numero` varchar(7) NOT NULL,
  `bairro` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `endereco`
--

INSERT INTO `endereco` (`idEndereco`, `cep`, `logradouro`, `numero`, `bairro`) VALUES
(31, '11672-020', 'Rua do Contorno', '199', 30),
(73, '00515-150', 'Rua Viradouro', '63', 72);

--
-- Acionadores `endereco`
--
DELIMITER //
CREATE TRIGGER `delete_bairro` AFTER DELETE ON `endereco`
 FOR EACH ROW BEGIN
	DECLARE numRows INT;
        SET numRows = (SELECT COUNT(*) FROM Endereco WHERE bairro = OLD.bairro);
        IF (numRows < 1) THEN
			BEGIN
				DELETE FROM Bairro WHERE idBairro = OLD.bairro;
			END;
		END IF;
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `estado`
--

CREATE TABLE IF NOT EXISTS `estado` (
  `idEstado` bigint(20) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `uf` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `estado`
--

INSERT INTO `estado` (`idEstado`, `nome`, `uf`) VALUES
(1, 'São Paulo', 'SP'),
(2, 'Acre', 'AC'),
(3, 'Alagoas	', 'AL'),
(4, 'Amapá', 'AP'),
(5, 'Amazonas', 'AM'),
(6, 'Bahia', 'BA'),
(7, 'Ceará', 'CE'),
(8, 'Distrito Federal', 'DF'),
(9, 'Espírito Santo', 'ES'),
(10, 'Goiás', 'GO'),
(11, 'Maranhão', 'MA'),
(12, 'Mato Grosso', 'MT'),
(13, 'Mato Grosso do Sul', 'MS'),
(14, 'Minas Gerais', 'MG'),
(15, 'Pará', 'PA'),
(16, 'Paraíba', 'PB'),
(17, 'Paraná', 'PR'),
(18, 'Pernambuco', 'PE'),
(19, 'Piauí', 'PI'),
(20, 'Rio de Janeiro', 'RJ'),
(21, 'Rio Grande do Norte', 'RN'),
(22, 'Rio Grande do Sul', 'RS'),
(23, 'Rondônia', 'RO'),
(24, 'Roraima', 'RR'),
(25, 'Santa Catarina', 'SC'),
(26, 'Sergipe', 'SE'),
(27, 'Tocantins', 'TO');

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario`
--

CREATE TABLE IF NOT EXISTS `funcionario` (
  `acesso` int(11) DEFAULT NULL,
  `senha` varchar(100) NOT NULL,
  `usuario` varchar(20) NOT NULL,
  `pessoa` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `funcionario`
--

INSERT INTO `funcionario` (`acesso`, `senha`, `usuario`, `pessoa`) VALUES
(0, 'E1DD9B9ACB6DDD3CCB36E3B96FC83666E3F00C5CAB12016F5A2443214F5EE9DE', 'tiagolima', 32),
(1, '61FBB95F920E6B069A8EF5DBD9ADDB436BC5A8BC92790B148519D4CA4EA2D5D0', 'paulavilla', 74);

-- --------------------------------------------------------

--
-- Estrutura da tabela `hibernate_sequence`
--

CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(84),
(84),
(84),
(84),
(84),
(84),
(84);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pessoa`
--

CREATE TABLE IF NOT EXISTS `pessoa` (
  `idPessoa` bigint(20) NOT NULL,
  `dataNascimento` date NOT NULL,
  `email` varchar(45) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `sexo` int(11) DEFAULT NULL,
  `documento` bigint(20) NOT NULL,
  `endereco` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `pessoa`
--

INSERT INTO `pessoa` (`idPessoa`, `dataNascimento`, `email`, `nome`, `sexo`, `documento`, `endereco`) VALUES
(32, '1988-04-21', 'tiago@gmail.com', 'Tiago Lima', 0, 28, 31),
(74, '1988-03-12', 'paula@gmail.com', 'Paula Villalobos', 1, 70, 73);

-- --------------------------------------------------------

--
-- Estrutura da tabela `telefone`
--

CREATE TABLE IF NOT EXISTS `telefone` (
  `idTelefone` bigint(20) NOT NULL,
  `ddd` varchar(4) NOT NULL,
  `numero` varchar(10) NOT NULL,
  `tipo` int(11) DEFAULT NULL,
  `pessoa` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `telefone`
--

INSERT INTO `telefone` (`idTelefone`, `ddd`, `numero`, `tipo`, `pessoa`) VALUES
(33, '(12)', '38879006', 0, 32),
(75, '(11)', '951703043', 1, 74);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bairro`
--
ALTER TABLE `bairro`
 ADD PRIMARY KEY (`idBairro`), ADD KEY `FK60r3aysiw339apo88005c6d16` (`cidade`);

--
-- Indexes for table `cidade`
--
ALTER TABLE `cidade`
 ADD PRIMARY KEY (`idCidade`), ADD KEY `FKh6fr9qce1jn2uls4htno0qbf3` (`estado`);

--
-- Indexes for table `documento`
--
ALTER TABLE `documento`
 ADD PRIMARY KEY (`idDocumento`);

--
-- Indexes for table `endereco`
--
ALTER TABLE `endereco`
 ADD PRIMARY KEY (`idEndereco`), ADD KEY `FK8ht1pm5smxh5k65vc211hm1es` (`bairro`);

--
-- Indexes for table `estado`
--
ALTER TABLE `estado`
 ADD PRIMARY KEY (`idEstado`), ADD UNIQUE KEY `UK_rqbyd5fl82quobtqh1sggbqda` (`nome`), ADD UNIQUE KEY `UK_9nl6ktpk90uens3seaft1gyxp` (`uf`);

--
-- Indexes for table `funcionario`
--
ALTER TABLE `funcionario`
 ADD PRIMARY KEY (`pessoa`);

--
-- Indexes for table `pessoa`
--
ALTER TABLE `pessoa`
 ADD PRIMARY KEY (`idPessoa`), ADD KEY `FK6h305v7qflv5wsdnywfrjhtjq` (`documento`), ADD KEY `FKshewtplh8m3w3an9okcvfwh6b` (`endereco`);

--
-- Indexes for table `telefone`
--
ALTER TABLE `telefone`
 ADD PRIMARY KEY (`idTelefone`), ADD KEY `FK5nmr7ehkuajcjp2py6pqnt3w5` (`pessoa`);

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `bairro`
--
ALTER TABLE `bairro`
ADD CONSTRAINT `FK60r3aysiw339apo88005c6d16` FOREIGN KEY (`cidade`) REFERENCES `cidade` (`idCidade`);

--
-- Limitadores para a tabela `cidade`
--
ALTER TABLE `cidade`
ADD CONSTRAINT `FKh6fr9qce1jn2uls4htno0qbf3` FOREIGN KEY (`estado`) REFERENCES `estado` (`idEstado`);

--
-- Limitadores para a tabela `endereco`
--
ALTER TABLE `endereco`
ADD CONSTRAINT `FK8ht1pm5smxh5k65vc211hm1es` FOREIGN KEY (`bairro`) REFERENCES `bairro` (`idBairro`);

--
-- Limitadores para a tabela `funcionario`
--
ALTER TABLE `funcionario`
ADD CONSTRAINT `FKjswill93do4651y3iij8rvxep` FOREIGN KEY (`pessoa`) REFERENCES `pessoa` (`idPessoa`);

--
-- Limitadores para a tabela `pessoa`
--
ALTER TABLE `pessoa`
ADD CONSTRAINT `FK6h305v7qflv5wsdnywfrjhtjq` FOREIGN KEY (`documento`) REFERENCES `documento` (`idDocumento`),
ADD CONSTRAINT `FKshewtplh8m3w3an9okcvfwh6b` FOREIGN KEY (`endereco`) REFERENCES `endereco` (`idEndereco`);

--
-- Limitadores para a tabela `telefone`
--
ALTER TABLE `telefone`
ADD CONSTRAINT `FK5nmr7ehkuajcjp2py6pqnt3w5` FOREIGN KEY (`pessoa`) REFERENCES `pessoa` (`idPessoa`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
