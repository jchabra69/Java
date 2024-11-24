-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.1.0 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.5.0.6677
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para tienda
DROP DATABASE IF EXISTS `tienda`;
CREATE DATABASE IF NOT EXISTS `tienda` /*!40100 DEFAULT CHARACTER SET ascii */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `tienda`;

-- Volcando estructura para tabla tienda.fabricantes
DROP TABLE IF EXISTS `fabricantes`;
CREATE TABLE IF NOT EXISTS `fabricantes` (
  `idFabricante` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idFabricante`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=ascii;

-- Volcando datos para la tabla tienda.fabricantes: ~9 rows (aproximadamente)
DELETE FROM `fabricantes`;
INSERT INTO `fabricantes` (`idFabricante`, `nombre`) VALUES
	(1, 'Asus'),
	(2, 'Lenovo'),
	(3, 'Hewlett-Packard'),
	(4, 'Samsung'),
	(5, 'Seagate'),
	(6, 'Crucial'),
	(7, 'Gigabyte'),
	(8, 'Huawei'),
	(9, 'Xiaomi');

-- Volcando estructura para tabla tienda.productos
DROP TABLE IF EXISTS `productos`;
CREATE TABLE IF NOT EXISTS `productos` (
  `idProducto` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` double NOT NULL,
  `idFabricante` int NOT NULL,
  PRIMARY KEY (`idProducto`),
  KEY `FKc70aquo0w04g2asco4da9yoe0` (`idFabricante`),
  CONSTRAINT `FKc70aquo0w04g2asco4da9yoe0` FOREIGN KEY (`idFabricante`) REFERENCES `fabricantes` (`idFabricante`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=ascii;

-- Volcando datos para la tabla tienda.productos: ~11 rows (aproximadamente)
DELETE FROM `productos`;
INSERT INTO `productos` (`idProducto`, `nombre`, `precio`, `idFabricante`) VALUES
	(1, 'Monitor 27 LED Full HD', 199.25, 1),
	(2, 'Monitor 24 LED Full HD', 119.99, 1),
	(3, 'Portatil IdeaPad 320', 359.65, 2),
	(4, 'Portatil Yoga 520', 452.79, 2),
	(5, 'Impresora HP Deskjet 3720', 59.99, 3),
	(6, 'Impresora HP Laserjet Pro M26nw', 111.86, 3),
	(7, 'Disco SSD 1 TB', 72.99, 4),
	(8, 'Disco duro SATA3 1TB', 38.49, 5),
	(9, 'GeForce GTX 1080 Xtreme', 611.55, 6),
	(10, 'Memoria RAM DDR4 8GB', 24.19, 6),
	(11, 'GeForce GTX 1050Ti', 319.19, 7);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
