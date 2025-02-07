package br.com.spassu.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.spassu.api.assembler.LivroInputDisassembler;
import br.com.spassu.api.assembler.LivroModelAssembler;
import br.com.spassu.api.model.LivroModel;
import br.com.spassu.api.model.input.LivroInput;
import br.com.spassu.domain.model.Livro;
import br.com.spassu.domain.repository.LivroRepository;
import br.com.spassu.domain.service.CadastroLivroService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "Livros")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/livros", produces = MediaType.APPLICATION_JSON_VALUE)
public class LivroController {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private LivroModelAssembler livroModelAssembler;
	
	@Autowired
	private CadastroLivroService cadastroLivroService;
	
	@Autowired
	private LivroInputDisassembler livroInputDisassembler;
	
	@GetMapping 
	public List<LivroModel> listar() {
		  
		log.info("Consultando livros...");
		
		List<Livro> todosLivros = livroRepository.findAll();
		return livroModelAssembler.toCollectionModel(todosLivros); 
	}
	
	@GetMapping("/{livroId}")
	public LivroModel buscar(@PathVariable("livroId") Long livroId) {
		
		log.info("Consultando livro por ID...");
		
		Livro livro = cadastroLivroService.buscarOuFalhar(livroId);
	    
	    return livroModelAssembler.toModel(livro);
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public LivroModel adicionar(@RequestBody @Valid LivroInput livroInput) {
		
		log.info("Salvando um livro...");
		
	    return livroModelAssembler.toModel(
	    		cadastroLivroService.salvar(
	    				livroInputDisassembler.toDomainObject(livroInput)
	    		)
	    );
	        
	}
	
	@PutMapping("/{livroId}")
	public LivroModel atualizar(@PathVariable Long livroId, 
			@RequestBody @Valid LivroInput livroInput) {
		
		log.info("Atualizando um livro...");
		
		Livro livroAtual = cadastroLivroService.buscarOuFalhar(livroId);
		livroInputDisassembler.copyToDomainObject(livroInput, livroAtual);
		livroAtual = cadastroLivroService.alterar(livroAtual);
	    
	    return livroModelAssembler.toModel(livroAtual);
		
	}
	
	@DeleteMapping("/{livroId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long livroId) {
		
		log.info("Excluindo um livro...");
		
		cadastroLivroService.excluir(livroId);
		
	}
	 
}
