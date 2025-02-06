package br.com.spassu.infraestructure.service.query;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.spassu.domain.model.dto.LivrosConsolidado;
import br.com.spassu.domain.service.LivroQueryService;

@Repository
public class LivroQueryServiceImpl implements LivroQueryService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<LivrosConsolidado> consultarLivrosConsolidado() {
		
	    String query = "SELECT * FROM view_livro_consolidado";
	    List<Object[]> resultados = entityManager.createNativeQuery(query).getResultList();
	    List<LivrosConsolidado> livrosConsolidado = new ArrayList<>();
	    String autorNomeAnterior = null;
	    
	    for (Object[] resultado : resultados) {
	        LivrosConsolidado livro = new LivrosConsolidado();
	        livro.setTitulo((String) resultado[1]);
	        livro.setEditora((String) resultado[2]);
	        String autorNomeAtual = (String) resultado[6];
	        
	        if (autorNomeAtual.equals(autorNomeAnterior)) {
	            livro.setAutorNome("");
	        } else {
	            livro.setAutorNome(autorNomeAtual);
	            autorNomeAnterior = autorNomeAtual;
	        }
	        
	        livro.setPreco((BigDecimal) resultado[5]);
	        livro.setAssuntoDescricao((String) resultado[8]);
	        livrosConsolidado.add(livro);
	    }
	    
	    return livrosConsolidado;
	}

}
