DROP TABLE IF EXISTS planos_usuarios CASCADE;
DROP TABLE IF EXISTS endereco CASCADE;
DROP TABLE IF EXISTS usuario_pj CASCADE;
DROP TABLE IF EXISTS usuario_pf CASCADE;
DROP TABLE IF EXISTS plano CASCADE;
DROP TABLE IF EXISTS usuario CASCADE;

-- Criação da tabela de Usuário
CREATE TABLE usuario (
    id_usuario SERIAL PRIMARY KEY,
    tipo VARCHAR(255),
    nome VARCHAR(255),
    email VARCHAR(255),
    saldo DECIMAL(10, 2),
    data_cadastro DATE
);

-- Criação da tabela de Usuário PF (Pessoa Física)
CREATE TABLE usuario_pf (
    cpf VARCHAR(14) PRIMARY KEY,
    nome VARCHAR(255),
    rg VARCHAR(20),
    senha VARCHAR(255),
    data_nascimento DATE,
    id_usuario INTEGER,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

-- Criação da tabela de Usuário PJ (Pessoa Jurídica)
CREATE TABLE usuario_pj (
    cnpj VARCHAR(14) PRIMARY KEY,
    razao_social VARCHAR(255),
    senha VARCHAR(255),
    data_nascimento DATE,
    id_usuario INTEGER,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);


-- Criação da tabela de Endereço
CREATE TABLE endereco (
    id_endereco SERIAL PRIMARY KEY,
    rua VARCHAR(255),
    cep VARCHAR(9),
    numero INTEGER,
    bairro VARCHAR(255),
    cidade VARCHAR(255),
    estado VARCHAR(255),
    id_usuario INTEGER,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

-- Criação da tabela de Plano
CREATE TABLE plano (
    id_plano SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    valor DECIMAL(10, 2),
    qtd_internet VARCHAR(255)
);

-- Criação da tabela de Planos_Usuarios
CREATE TABLE planos_usuarios (
    contrato SERIAL PRIMARY KEY,
    id_plano INTEGER,
    qtd_internet_consumido DOUBLE PRECISION,
    qtd_internet_restante DOUBLE PRECISION,
    data_inicio DATE,
    data_final DATE,
    fatura DECIMAL(10, 2),
    telefone VARCHAR(20),
    status VARCHAR(50),
    id_endereco INTEGER,
    id_usuario INTEGER,
    FOREIGN KEY (id_plano) REFERENCES plano(id_plano),
    FOREIGN KEY (id_endereco) REFERENCES endereco(id_endereco),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);


