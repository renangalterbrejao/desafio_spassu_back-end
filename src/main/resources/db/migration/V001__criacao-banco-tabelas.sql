create table assunto (codas bigint not null auto_increment, descricao varchar(20) not null, primary key (codas)) engine=InnoDB;

create table autor (codau bigint not null auto_increment, nome varchar(40) not null, primary key (codau)) engine=InnoDB;

create table livro (codl bigint not null auto_increment, ano_publicacao integer not null, edicao integer not null, editora varchar(40) not null, preco decimal(19,2) not null, titulo varchar(40) not null, primary key (codl)) engine=InnoDB;

create table livro_assunto (livro_codl bigint not null, assunto_codas bigint not null, primary key (livro_codl, assunto_codas)) engine=InnoDB;

create table livro_autor (livro_codl bigint not null, autor_codau bigint not null, primary key (livro_codl, autor_codau)) engine=InnoDB;

alter table livro_assunto add constraint FK479yo1o2h4bts07hnfb06wb0 foreign key (assunto_codas) references assunto (codas);

alter table livro_assunto add constraint FKorwqhfh2o2kof6yohjkpgnq1u foreign key (livro_codl) references livro (codl);

alter table livro_autor add constraint FKm20y83716io0lwoombcxwq34i foreign key (autor_codau) references autor (codau);

alter table livro_autor add constraint FK89geljs5v6shh884tjw8tbqk3 foreign key (livro_codl) references livro (codl);