package br.com.spassu.api.model.input;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LivroInput {
	
	@Valid
	@NotBlank
	private String titulo;
	
	@Valid
	@NotBlank
	private String editora;
	
	@NotNull
    @PositiveOrZero
	private Integer edicao;
	
	@NotNull
    @PositiveOrZero
	private Integer anoPublicacao;
	
	@NotNull
    @PositiveOrZero
	private BigDecimal preco;
	
	@Valid
    @Size(min = 1)
    @NotNull
    private List<AutorIdInput> idsAutores;
	
	@Valid
    @Size(min = 1)
    @NotNull
    private List<AssuntoIdInput> idsAssuntos;
}
