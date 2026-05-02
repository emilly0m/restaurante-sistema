CREATE DATABASE RestauranteBD;
USE RestauranteBD;

CREATE TABLE Garcom (
    id INT AUTO_INCREMENT PRIMARY KEY,
    perfil VARCHAR (20) DEFAULT 'garcom',
    nome VARCHAR(100) NOT NULL,
    login VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(50) NOT NULL
);

CREATE TABLE Produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco DOUBLE NOT NULL
);

CREATE TABLE Mesa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    status VARCHAR(20) NOT NULL DEFAULT 'livre'
);

CREATE TABLE Comanda (
    id INT AUTO_INCREMENT PRIMARY KEY,
    mesa_id INT NOT NULL,
    garcom_id INT NOT NULL,
    FOREIGN KEY (mesa_id) REFERENCES mesa(id),
    FOREIGN KEY (garcom_id) REFERENCES garcom(id)
);

CREATE TABLE Item_Comanda (
    id INT AUTO_INCREMENT PRIMARY KEY,
    comanda_id INT NOT NULL,
    produto_id INT NOT NULL,
    quantidade INT NOT NULL,
    subtotal DOUBLE NOT NULL,
    FOREIGN KEY (comanda_id) REFERENCES comanda(id),
    FOREIGN KEY (produto_id) REFERENCES produto(id)
);

INSERT INTO garcom (nome, login, senha, perfil) VALUES ('Administrador', 'admin', 'admin123', 'admin');
INSERT INTO Garcom (nome, login, senha, perfil) VALUES ('Bruno Alves', 'bruno', '1234', garcom);
INSERT INTO Produto (nome, preco) VALUES ('Bife acebolado', 25.90);
INSERT INTO Mesa (status) VALUES ('livre');
INSERT INTO Comanda (mesa_id, garcom_id) VALUES (1, 1);
INSERT INTO Item_Comanda (comanda_id, produto_id, quantidade, subtotal) VALUES (1, 1, 2, 51.80);
