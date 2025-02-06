package br.com.spassu.domain.model.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LivrosConsolidado {
	
	private String titulo;
	private String editora;
	private String autorNome;
	private BigDecimal preco;

}
