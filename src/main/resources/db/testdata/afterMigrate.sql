set foreign_key_checks = 0;

lock tables livro write, autor write, assunto write, livro_assunto write, livro_autor write; 

delete from livro;
delete from autor;
delete from assunto;
delete from livro_autor;
delete from livro_assunto;

set foreign_key_checks = 1;

alter table livro auto_increment = 1;
alter table autor auto_increment = 1;
alter table assunto auto_increment = 1;
alter table livro_autor auto_increment = 1;
alter table livro_assunto auto_increment = 1;

insert into autor (codau, nome) values (1, 'Luiz de Queiroz');
insert into autor (codau, nome) values (2, 'Autor a ser alterado');
insert into autor (codau, nome) values (3, 'Autor a ser excluido');
insert into assunto (codas, descricao) values (1, 'Assunto1');
insert into assunto (codas, descricao) values (2, 'Assunto alterado');
insert into assunto (codas, descricao) values (3, 'Assunto excluido');

insert into livro (codl, ano_publicacao, edicao, editora, preco, titulo) 
	values (1, '1898', 8, 'Abril', 80.00, 'Dom Casmurro');
insert into livro (codl, ano_publicacao, edicao, editora, preco, titulo) 
	values (2, '2015', 1, 'Abril', 34.00, 'Livro a ser alterado');
insert into livro (codl, ano_publicacao, edicao, editora, preco, titulo) 
	values (3, '1978', 5, 'Abril', 255.00, 'Livro a ser excluido');
insert into livro (codl, ano_publicacao, edicao, editora, preco, titulo) 
	values (4, '1572', 2, 'Abril', 40.00, 'Os Lusíadas');

insert into livro_autor (livro_codl, autor_codau) values (1, 1);
insert into livro_assunto (livro_codl, assunto_codas) values (1, 1);

unlock tables;








