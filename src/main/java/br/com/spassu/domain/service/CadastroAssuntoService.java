package br.com.spassu.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.spassu.domain.exception.AssuntoNaoEncontradoException;
import br.com.spassu.domain.exception.CampoExcedeuLimiteException;
import br.com.spassu.domain.exception.EntidadeEmUsoException;
import br.com.spassu.domain.model.Assunto;
import br.com.spassu.domain.repository.AssuntoRepository;

@Service
public class CadastroAssuntoService {
	
	private static final String MSG_ASSUNTO_EM_USO = "Assunto de código %d não pode ser removido, pois está em uso";
	
	@Autowired
    private AssuntoRepository assuntoRepository;
	
	@Transactional
    public Assunto salvar(Assunto assunto) {
		
		if(assunto.getDescricao().length() > 20) {
			throw new CampoExcedeuLimiteException("descrição", 20);
		}
		
        return assuntoRepository.save(assunto);
    }
    
    public Assunto buscarOuFalhar(Long assuntoId) {
        return assuntoRepository.findById(assuntoId)
            .orElseThrow(() -> new AssuntoNaoEncontradoException(assuntoId));
    }
    
    @Transactional
	public void excluir(Long assuntoId) {

		try {

			assuntoRepository.deleteById(assuntoId);
			assuntoRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			
			throw new AssuntoNaoEncontradoException(assuntoId);
			
		} catch (DataIntegrityViolationException e) {

			throw new EntidadeEmUsoException(String.format(MSG_ASSUNTO_EM_USO, assuntoId));

		}
	}

}
