CREATE DATABASE RestauranteBD;
USE RestauranteBD;

-- Tabela de usuários (para Login)
CREATE TABLE Usuario (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(100) NOT NULL,
                         login VARCHAR(50) NOT NULL UNIQUE,
                         senha VARCHAR(50) NOT NULL
);

-- Tabela Produto (Classe 1)
CREATE TABLE Produto (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(100) NOT NULL,
                         preco DOUBLE NOT NULL
);

-- Tabela Garcom (Classe 2)
CREATE TABLE Garcom (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        nome VARCHAR(100) NOT NULL
);

-- Tabela Mesa (Classe 3)
CREATE TABLE Mesa (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      status VARCHAR(20) NOT NULL DEFAULT 'livre'
);

-- Tabela Comanda
CREATE TABLE Comanda (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         mesa_id INT NOT NULL,
                         garcom_id INT NOT NULL,
                         FOREIGN KEY (mesa_id) REFERENCES mesa(id),
                         FOREIGN KEY (garcom_id) REFERENCES garcom(id)
);

-- Tabela ItemComanda
CREATE TABLE Item_Comanda (
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              comanda_id INT NOT NULL,
                              produto_id INT NOT NULL,
                              quantidade INT NOT NULL,
                              subtotal DOUBLE NOT NULL,
                              FOREIGN KEY (comanda_id) REFERENCES comanda(id),
                              FOREIGN KEY (produto_id) REFERENCES produto(id)
);

-- Usuário padrão para login (senha: 1234)
INSERT INTO usuario (nome, login, senha) VALUES ('Administrador', 'admin', '1234');

DROP TABLE IF EXISTS item_comanda;
DROP TABLE IF EXISTS comanda;
DROP TABLE IF EXISTS garcom;
ALTER TABLE usuario RENAME TO garcom;
UPDATE garcom SET login = 'joao', senha = '1234' WHERE id = 1;