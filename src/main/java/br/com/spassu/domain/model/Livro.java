package br.com.spassu.domain.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Livro implements Comparable<Livro> {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Codl")
	private Long codl;
	
	@NotNull
	@NotBlank
	@Column(name = "Titulo", length = 40)
	private String titulo;
	
	@NotNull
	@NotBlank
	@Column(name = "Editora", length = 40)
	private String editora;
	
	@Column(name = "Edicao", nullable = false)
	private Integer edicao;
	
	@Column(name = "AnoPublicacao", length = 4, nullable = false)
	private Integer anoPublicacao;
	
	@Column(name = "Preco", nullable = false)
	private BigDecimal preco;
	
	@ManyToMany
	@JoinTable(name = "Livro_Autor",
			joinColumns = @JoinColumn(name = "Livro.Codl") ,
			inverseJoinColumns = @JoinColumn(name = "Autor.Codau"))
	private Set<Autor> autores = new HashSet<>();
	
	@ManyToMany
	@JoinTable(name = "Livro_Assunto",
			joinColumns = @JoinColumn(name = "Livro.Codl") ,
			inverseJoinColumns = @JoinColumn(name = "Assunto.Codas"))
	private Set<Assunto> assuntos = new HashSet<>();
	
	@Override
	public int compareTo(Livro other) {
		return titulo.compareTo(other.getTitulo());
	}

}
