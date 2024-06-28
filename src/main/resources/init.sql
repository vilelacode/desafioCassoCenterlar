CREATE TABLE matriculas (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           aluno VARCHAR(255) NOT NULL,
                           curso VARCHAR(255) NOT NULL,
                            nota DECIMAL(4,2),
                            alunos_id INT,
                            cursos_id INT,
                               FOREIGN KEY (alunos_id) REFERENCES tb_alunos(id),
                                 FOREIGN KEY (cursos_id) REFERENCES tb_cursos(id)
);

CREATE TABLE tb_alunos (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(255) NOT NULL,

);

CREATE TABLE tb_cursos (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(255) NOT NULL,
                         descricao TEXT
);



INSERT INTO tb_alunos (nome) VALUES ('Luca'), ('Modric'), ('Messi'), ('Neymar'), ('Cristiano');

INSERT INTO tb_cursos (nome, descricao) VALUES
                                                    ('Física','Curso de Física'),
                                                    ('Matemática', 'Curso de Matemática'),
                                                    ('Química', 'Curso de Química'),
                                                    ('Biologia', 'Curso de Biologia'),
                                                    ('História', 'Curso de História');


