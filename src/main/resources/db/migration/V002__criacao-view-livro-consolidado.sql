CREATE VIEW spassu.view_livro_consolidado AS
SELECT 
  l.codl,
  l.titulo,
  l.editora,
  l.ano_publicacao,
  l.edicao,
  l.preco,
  a.nome AS autor_nome,
  a.codau AS autor_codau,
  asu.descricao AS assunto_descricao,
  asu.codas AS assunto_codas
FROM 
  livro l
  LEFT JOIN livro_autor la ON l.codl = la.livro_codl
  LEFT JOIN autor a ON la.autor_codau = a.codau
  LEFT JOIN livro_assunto ls ON l.codl = ls.livro_codl
  LEFT JOIN assunto asu ON ls.assunto_codas = asu.codas;