CREATE DATABASE IF NOT EXISTS comercio;

USE comercio;

CREATE TABLE Categorias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT
);

CREATE TABLE Productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    imagen VARCHAR(255),
    categoriaID INT,
    FOREIGN KEY (categoriaID) REFERENCES Categorias(id) ON DELETE SET NULL
);

CREATE TABLE Usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(50) NOT NULL DEFAULT 'cliente'
);

INSERT INTO Usuarios (nombre, email, password, rol) 
VALUES ('Administrador', 'jchabra595r@g.educaand.es', '12345678', 'admin');

CREATE TABLE Pedidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuarioID INT NOT NULL,
    productoID INT NOT NULL,
    fechaPedido DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuarioID) REFERENCES Usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (productoID) REFERENCES Productos(id) ON DELETE CASCADE
);

INSERT INTO Categorias (nombre, descripcion)
VALUES ('Higiene Personal', 'Productos relacionados con higiene personal y cuidado bucal');

INSERT INTO Productos (nombre, descripcion, precio, categoriaID, imagen)
VALUES 
    ('Pasta de dientes Colgate Sabor a Menta', 
     'Pasta de dientes Colgate sabor a menta, 150g. Asegura una limpieza profunda y un aliento fresco.',
     3.99, 
     1,
     'https://files.catbox.moe/yy6l0r.jpg');
