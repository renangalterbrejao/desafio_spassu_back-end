package br.com.spassu.domain.service;

import java.util.List;

import br.com.spassu.domain.model.dto.LivrosConsolidado;

public interface LivroQueryService {
	
	List<LivrosConsolidado> consultarLivrosConsolidado();

}
