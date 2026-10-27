INSERT INTO autor (nome, nacionalidade) VALUES ('Colleen Hoover', 'Estados Unidos');
INSERT INTO autor (nome, nacionalidade) VALUES ('Matt Haig', 'Reino Unido');
INSERT INTO autor (nome, nacionalidade) VALUES ('Nita Prose', 'Canadá');
INSERT INTO autor (nome, nacionalidade) VALUES ('Gabriel García Márquez', 'Colômbia');

INSERT INTO categoria (nome, descricao) VALUES ('Romance', 'Obras de romance e drama emocional');
INSERT INTO categoria (nome, descricao) VALUES ('Ficção Científica', 'Narrativas especulativas e universos paralelos');
INSERT INTO categoria (nome, descricao) VALUES ('Suspense', 'Obras de investigação policial e mistério');

INSERT INTO usuario (nome, email, senha, perfil) VALUES ('Administrador', 'admin@biblioteca.com', '$2a$12$o4dHSbnIPaWETDoIoTFU8.Yj4f8xcMqkmFwxPq1m.mnRSQ.8wnakm', 'ADMIN');
INSERT INTO usuario (nome, email, senha, perfil) VALUES ('Leitor Exemplo', 'leitor@biblioteca.com', '$2a$12$mqI9XofmYNr2Dc8Zgw0SnOXI8mSlmgsKeoun6xst50tmpLwQcJw.G', 'LEITOR');
INSERT INTO usuario (nome, email, senha, perfil) VALUES ('Ana Beatriz Souza', 'ana.souza@email.com', '$2a$12$SfY0NVCVtCsYtDp3NZpk7uy.7LuP3lyCuM88nF56agMSnZQ6RDvUa', 'LEITOR');
INSERT INTO usuario (nome, email, senha, perfil) VALUES ('Bruno Carvalho', 'bruno.carvalho@email.com', '$2a$12$SfY0NVCVtCsYtDp3NZpk7uy.7LuP3lyCuM88nF56agMSnZQ6RDvUa', 'LEITOR');
INSERT INTO usuario (nome, email, senha, perfil) VALUES ('Carla Menezes', 'carla.menezes@email.com', '$2a$12$SfY0NVCVtCsYtDp3NZpk7uy.7LuP3lyCuM88nF56agMSnZQ6RDvUa', 'LEITOR');
INSERT INTO usuario (nome, email, senha, perfil) VALUES ('Diego Ferreira', 'diego.ferreira@email.com', '$2a$12$SfY0NVCVtCsYtDp3NZpk7uy.7LuP3lyCuM88nF56agMSnZQ6RDvUa', 'LEITOR');

INSERT INTO livro (titulo, isbn, ano_publicacao, disponivel, autor_id, categoria_id) VALUES ('Verity', '978-85-508-0700-4', 2018, FALSE, 1, 3);
INSERT INTO livro (titulo, isbn, ano_publicacao, disponivel, autor_id, categoria_id) VALUES ('É Assim que Acaba', '978-85-508-1060-8', 2016, TRUE, 1, 1);
INSERT INTO livro (titulo, isbn, ano_publicacao, disponivel, autor_id, categoria_id) VALUES ('A Biblioteca da Meia-Noite', '978-65-5511-175-6', 2020, FALSE, 2, 2);
INSERT INTO livro (titulo, isbn, ano_publicacao, disponivel, autor_id, categoria_id) VALUES ('A Empregada', '978-65-5565-305-7', 2022, TRUE, 3, 3);
INSERT INTO livro (titulo, isbn, ano_publicacao, disponivel, autor_id, categoria_id) VALUES ('A Empregada em Fúria', '978-65-5565-647-8', 2023, TRUE, 3, 3);
INSERT INTO livro (titulo, isbn, ano_publicacao, disponivel, autor_id, categoria_id) VALUES ('Cem Anos de Solidão', '978-85-01-06367-2', 1967, TRUE, 4, 1);

INSERT INTO emprestimo (livro_id, usuario_id, data_emprestimo, data_devolucao_prevista, data_devolucao_efetiva, status) VALUES (1, 3, CURRENT_DATE, DATEADD('DAY', 7, CURRENT_DATE), NULL, 'ATIVO');
INSERT INTO emprestimo (livro_id, usuario_id, data_emprestimo, data_devolucao_prevista, data_devolucao_efetiva, status) VALUES (3, 4, CURRENT_DATE, DATEADD('DAY', 10, CURRENT_DATE), NULL, 'ATIVO');
INSERT INTO emprestimo (livro_id, usuario_id, data_emprestimo, data_devolucao_prevista, data_devolucao_efetiva, status) VALUES (4, 5, DATEADD('DAY', -10, CURRENT_DATE), DATEADD('DAY', -3, CURRENT_DATE), DATEADD('DAY', -3, CURRENT_DATE), 'DEVOLVIDO');
