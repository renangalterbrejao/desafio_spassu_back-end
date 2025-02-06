package br.com.spassu.api.model;

import java.math.BigDecimal;
import java.util.Set;

import br.com.spassu.domain.model.Assunto;
import br.com.spassu.domain.model.Autor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LivroModel {
	
	private Long codl;
	private String titulo;
	private String editora;
	private Integer edicao;
	private Integer anoPublicacao;
	private BigDecimal preco;
	private Set<Autor> autores;
	private Set<Assunto> assuntos;
}
