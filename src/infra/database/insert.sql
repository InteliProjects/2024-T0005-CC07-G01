-- # Inserção inicial de dados no banco de dados

-- Inserção na tabela de Usuário
INSERT INTO usuario (tipo, nome, email, saldo, data_cadastro) VALUES
('Tipo1', 'Nome1', 'email1@example.com', 100.00, '2024-02-23'),
('Tipo2', 'Nome2', 'email2@example.com', 150.00, '2024-02-22'),
('Tipo3', 'Nome3', 'email3@example.com', 200.00, '2024-02-21');

-- Inserção na tabela de Usuário PF (Pessoa Física)
INSERT INTO usuario_pf (cpf, nome, rg, senha, data_nascimento, id_usuario) VALUES
('12345678900', 'Nome PF1', 'MG-11.222.333', 'senha123', '1990-01-01', 1),
('98765432100', 'Nome PF2', 'SP-44.555.666', 'senha123', '1991-02-01', 2),
('19283746500', 'Nome PF3', 'RJ-77.888.999', 'senha123', '1992-03-01', 3);

-- Inserção na tabela de Usuário PJ (Pessoa Jurídica)
INSERT INTO usuario_pj (cnpj, razao_social, senha, data_nascimento, id_usuario) VALUES
('11122233344455', 'Empresa1 S.A.', 'senha123', '2000-01-01', 1),
('66677788899900', 'Empresa2 Ltda.', 'senha123', '2001-02-01', 2),
('55544433322211', 'Empresa3 Eireli', 'senha123', '2002-03-01', 3);

-- Inserção na tabela de Endereço
INSERT INTO endereco (rua, cep, numero, bairro, cidade, estado, id_usuario) VALUES
('Rua Um', '12345-678', 100, 'Bairro 1', 'Cidade 1', 'SP', 1),
('Rua Dois', '23456-789', 200, 'Bairro 2', 'Cidade 2', 'RJ', 2),
('Rua Três', '34567-890', 300, 'Bairro 3', 'Cidade 3', 'MG', 3);

-- Inserção na tabela de Plano
INSERT INTO plano (nome, valor, qtd_internet) VALUES
('Plano Básico', 99.99, '5GB'),
('Plano Intermediário', 199.99, '10GB'),
('Plano Avançado', 299.99, '20GB');

-- Inserção na tabela de Planos_Usuarios
INSERT INTO planos_usuarios (id_plano, qtd_internet_consumido, qtd_internet_restante, data_inicio, data_final, fatura, telefone, status, id_endereco, id_usuario) VALUES
(1, 2.5, 2.5, '2024-02-01', '2024-03-01', 99.99, '11987654321', 'Ativo', 1, 1),
(2, 5.0, 5.0, '2024-02-01', '2024-03-01', 199.99, '21987654321', 'Ativo', 2, 2),
(3, 10.0, 10.0, '2024-02-01', '2024-03-01', 299.99, '31987654321', 'Ativo', 3, 3);
