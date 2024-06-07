USE LGRMECH;

-- Tabela para armazenar dados comuns a todos os tipos de usuários
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo ENUM('usuario', 'funcionario', 'adm'),
    nome VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    senha VARCHAR(100),
    telefone VARCHAR(20)
);

-- Tabela para armazenar informações específicas de usuários normais
CREATE TABLE usuarios_usuario (
    id INT PRIMARY KEY,
    CONSTRAINT fk_usuario FOREIGN KEY (id) REFERENCES usuarios(id)
    -- Aqui você adicionaria os campos específicos do usuário, como endereço, etc.
);

-- Tabela para armazenar informações específicas de funcionários
CREATE TABLE usuarios_funcionario (
    id INT PRIMARY KEY,
    departamento VARCHAR(100),
    CONSTRAINT fk_funcionario FOREIGN KEY (id) REFERENCES usuarios(id)
    -- Aqui você adicionaria os campos específicos de funcionário, como salário, etc.
);

-- Tabela para armazenar informações específicas de administradores
CREATE TABLE usuarios_adm (
    id INT PRIMARY KEY,
    cargo VARCHAR(100),
    CONSTRAINT fk_adm FOREIGN KEY (id) REFERENCES usuarios(id)
    -- Aqui você adicionaria os campos específicos de administrador, como privilégios, etc.
);

-- Insere um usuário comum na tabela usuarios e associa-o à tabela usuarios_usuario
INSERT INTO usuarios (tipo, nome, email, senha, telefone) 
VALUES ('usuario', 'Nome do Usuário', 'usuario@example.com', 'senha123', '123456789');
INSERT INTO usuarios_usuario (id) 
VALUES (LAST_INSERT_ID());

-- Insere um funcionário na tabela usuarios e associa-o à tabela usuarios_funcionario
INSERT INTO usuarios (tipo, nome, email, senha, telefone) 
VALUES ('funcionario', 'Nome do Funcionário', 'funcionario@example.com', 'senha456', '987654321');
INSERT INTO usuarios_funcionario (id, departamento) 
VALUES (LAST_INSERT_ID(), 'Departamento A');

-- Insere um administrador na tabela usuarios e associa-o à tabela usuarios_adm
INSERT INTO usuarios (tipo, nome, email, senha, telefone, usuario) 
VALUES ('adm', 'Lucas', 'LuquinhasKiing@gmail.com', '123456', '5555555554', 'Luquinhas');
INSERT INTO usuarios_adm (id) 
VALUES (LAST_INSERT_ID());

CREATE TABLE login (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    username VARCHAR(50) NULL,
    password VARCHAR(100) NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE projeto (
     id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
     codigo VARCHAR(50) NULL,
     codigo VARCHAR(50) NULL,
     codigo VARCHAR(50) NULL
     codigo VARCHAR(50) NULL,
     
SELECT * FROM usuarios;







