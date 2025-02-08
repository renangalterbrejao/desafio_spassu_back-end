package br.com.spassu.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.spassu.domain.exception.AutorNaoEncontradoException;
import br.com.spassu.domain.exception.CampoExcedeuLimiteException;
import br.com.spassu.domain.exception.EntidadeEmUsoException;
import br.com.spassu.domain.exception.NegocioException;
import br.com.spassu.domain.model.Assunto;
import br.com.spassu.domain.model.Autor;
import br.com.spassu.domain.repository.AutorRepository;

@Service
public class CadastroAutorService {
	
	private static final String MSG_AUTOR_EM_USO = "Autor de código %d não pode ser removido, pois está em uso";
	
	@Autowired
    private AutorRepository autorRepository;
	
	@Transactional
    public Autor salvar(Autor autor) {
		
		verificarLimiteCampo(autor);
		
		Optional<Autor> autorJaExistente = autorRepository.findByNome(autor.getNome());
    	
    	if(autorJaExistente.isPresent()) {
    		throw new NegocioException(String.format
		    				("O autor com nome '%s' já existe!", 
		    						autor.getNome()
		    				)
    					);
    	}
		
        return autorRepository.save(autor);
    }
    
    public Autor buscarOuFalhar(Long autorId) {
        return autorRepository.findById(autorId)
            .orElseThrow(() -> new AutorNaoEncontradoException(autorId));
    }
    
    @Transactional
    public Autor alterar(Autor autor) {
    	
    	verificarLimiteCampo(autor);
    	
    	return autorRepository.save(autor);	
    	
    }
    
    @Transactional
	public void excluir(Long autorId) {

		try {

			autorRepository.deleteById(autorId);
			autorRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			
			throw new AutorNaoEncontradoException(autorId);
			
		} catch (DataIntegrityViolationException e) {

			throw new EntidadeEmUsoException(String.format(MSG_AUTOR_EM_USO, autorId));

		}
	}
    
    private void verificarLimiteCampo(Autor autor) {
    	if(autor.getNome().length() > 40) {
			throw new CampoExcedeuLimiteException("nome", 40);
		}
    }

}
