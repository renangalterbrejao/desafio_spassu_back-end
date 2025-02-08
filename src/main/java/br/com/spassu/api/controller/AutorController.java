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

import br.com.spassu.api.assembler.AutorInputDisassembler;
import br.com.spassu.api.assembler.AutorModelAssembler;
import br.com.spassu.api.model.AssuntoModel;
import br.com.spassu.api.model.AutorModel;
import br.com.spassu.api.model.input.AssuntoInput;
import br.com.spassu.api.model.input.AutorInput;
import br.com.spassu.domain.model.Assunto;
import br.com.spassu.domain.model.Autor;
import br.com.spassu.domain.repository.AutorRepository;
import br.com.spassu.domain.service.CadastroAutorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "Autores")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/autores", produces = MediaType.APPLICATION_JSON_VALUE)
public class AutorController {
	
	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private AutorModelAssembler autorModelAssembler;
	
	@Autowired
	private CadastroAutorService cadastroAutorService;
	
	@Autowired
	private AutorInputDisassembler autorInputDisassembler;
	
	@ApiOperation("Lista todos os autores")
	@GetMapping 
	public List<AutorModel> listar() {
		  
		log.info("Consultando autores...");
		
		List<Autor> todosAutores = autorRepository.findAll();
		return autorModelAssembler.toCollectionModel(todosAutores); 
	}
	
	@ApiOperation("Lista todos os autores por ID")
	@GetMapping("/{autorId}")
	public AutorModel buscar(@PathVariable("autorId") Long autorId) {
		
		log.info("Consultando autor por ID...");
		
		Autor autor = cadastroAutorService.buscarOuFalhar(autorId);
	    
	    return autorModelAssembler.toModel(autor);
		
	}
	
	@ApiOperation("Adiciona um autor")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AutorModel adicionar(@RequestBody @Valid AutorInput autorInput) {
		
		log.info("Salvando autor...");
		
		Autor autor = autorInputDisassembler.toDomainObject(autorInput);
	    
		autor = cadastroAutorService.salvar(autor);
	    
	    return autorModelAssembler.toModel(autor);
	}
	
	@ApiOperation("Altera um autor")
	@PutMapping("/{autorId}")
	public AutorModel atualizar(@PathVariable Long autorId, 
			@RequestBody @Valid AutorInput autorInput) {
		
		log.info("Atualizando um autor...");
		
		Autor autorAtual = cadastroAutorService.buscarOuFalhar(autorId);
		autorInputDisassembler.copyToDomainObject(autorInput, autorAtual);
		autorAtual = cadastroAutorService.alterar(autorAtual);
	    
	    return autorModelAssembler.toModel(autorAtual);
		
	}
	
	@ApiOperation("Exclui um autor")
	@DeleteMapping("/{autorId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long autorId) {
		
		log.info("Excluindo autor...");
		
		cadastroAutorService.excluir(autorId);
		
	}

}
