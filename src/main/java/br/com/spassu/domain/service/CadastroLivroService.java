package br.com.spassu.domain.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.spassu.domain.exception.EntidadeEmUsoException;
import br.com.spassu.domain.exception.LivroNaoEncontradoException;
import br.com.spassu.domain.exception.NegocioException;
import br.com.spassu.domain.model.Assunto;
import br.com.spassu.domain.model.Autor;
import br.com.spassu.domain.model.Livro;
import br.com.spassu.domain.repository.LivroRepository;

@Service
public class CadastroLivroService {
	
	private static final String MSG_LIVRO_EM_USO = "Livro de código %d não pode ser removido, pois está em uso";
	private static final String MSG_ERRO_LIVRO = "O livro não pode ser incluido pois houve problema de"
			+ " integração de dados. Verifique todos os Autores e Assuntos "
			+ "	relacionados já estão persistidos ";
	
	@Autowired
    private LivroRepository livroRepository;
	
	@Autowired
	private CadastroAutorService cadastroAutorService;
	
	@Autowired
	private CadastroAssuntoService cadastroAssuntoService;
    
    @Transactional
    public Livro salvar(Livro livro) {
    	
    	/*Comentar para melhorar a performance*/
    	verificarSetarAutorAssunto(livro);
    	
    	Optional<Livro> livrosJaExistentes = livroRepository
    			.findBooksByTituloAndEditora(livro.getTitulo(), livro.getEditora());
    	
    	if(livrosJaExistentes.isPresent()) {
    		throw new NegocioException(String.format
		    				("O livro com titulo '%s' e editora '%s' já existe!", 
		    						livro.getTitulo(), livro.getEditora()
		    				)
    					);
    	}
    	
    	try {
    		
    		return livroRepository.save(livro);
			
    	} catch (Exception e) {

			throw new NegocioException(String.format(MSG_ERRO_LIVRO), e);
			
    	} 
    }
    
    public Livro buscarOuFalhar(Long livroId) {
        return livroRepository.findById(livroId)
            .orElseThrow(() -> new LivroNaoEncontradoException(livroId));
    }
    
    @Transactional
    public Livro alterar(Livro livro) {
    	
    	/*Comentar para melhorar a performance*/
    	verificarSetarAutorAssunto(livro);
    	
    	try {
    		
    		return livroRepository.save(livro);
    		
    	} catch (Exception e) {

			throw new NegocioException(String.format(MSG_ERRO_LIVRO), e);
			
    	} 
    }
    
    @Transactional
	public void excluir(Long livroId) {

		try {

			livroRepository.deleteById(livroId);
			livroRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			
			throw new LivroNaoEncontradoException(livroId);
			
		} catch (DataIntegrityViolationException e) {

			throw new EntidadeEmUsoException(String.format(MSG_LIVRO_EM_USO, livroId));

		}
	}
    
    private void verificarSetarAutorAssunto(Livro livro) {
    	Set<Assunto> idsAssuntos = new HashSet<>(livro.getAssuntos());
    	livro.getAssuntos().clear();
    	for(Assunto assunto : idsAssuntos) {
    		Assunto assuntoAtual = cadastroAssuntoService.buscarOuFalhar(
    							assunto.getCodas());
    		livro.getAssuntos().add(assuntoAtual);	
    	}
    	
    	
    	Set<Autor> idsAutores = new HashSet<>(livro.getAutores());
    	livro.getAutores().clear();
    	for(Autor autor : idsAutores) {
    		Autor autorAtual = cadastroAutorService.buscarOuFalhar(
    							autor.getCodAu());
    		livro.getAutores().add(autorAtual);	
    	}
    }

}
