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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.spassu.api.assembler.AssuntoInputDisassembler;
import br.com.spassu.api.assembler.AssuntoModelAssembler;
import br.com.spassu.api.model.AssuntoModel;
import br.com.spassu.api.model.input.AssuntoInput;
import br.com.spassu.domain.model.Assunto;
import br.com.spassu.domain.repository.AssuntoRepository;
import br.com.spassu.domain.service.CadastroAssuntoService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "Assuntos")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/assuntos", produces = MediaType.APPLICATION_JSON_VALUE)
public class AssuntoController {
	
	@Autowired
	private AssuntoRepository assuntoRepository;
	
	@Autowired
	private AssuntoModelAssembler assuntoModelAssembler;
	
	@Autowired
	private CadastroAssuntoService cadastroAssuntoService;
	
	@Autowired
	private AssuntoInputDisassembler assuntoInputDisassembler;
	
	@GetMapping 
	public List<AssuntoModel> listar() {
		  
		log.info("Consultando assuntos...");
		
		List<Assunto> todosAssuntos = assuntoRepository.findAll();
		return assuntoModelAssembler.toCollectionModel(todosAssuntos); 
	}
	
	@GetMapping("/{assuntoId}")
	public AssuntoModel buscar(@PathVariable("assuntoId") Long assuntoId) {
		
		log.info("Consultando assunto por ID...");
		
		Assunto assunto = cadastroAssuntoService.buscarOuFalhar(assuntoId);
	    
	    return assuntoModelAssembler.toModel(assunto);
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AssuntoModel adicionar(@RequestBody @Valid AssuntoInput assuntoInput) {
		
		log.info("Salvando assunto...");
		
		Assunto assunto = assuntoInputDisassembler.toDomainObject(assuntoInput);
	    
		assunto = cadastroAssuntoService.salvar(assunto);
	    
	    return assuntoModelAssembler.toModel(assunto);
	}
	
	@DeleteMapping("/{assuntoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long assuntoId) {
		
		log.info("Excluindo assunto...");
		
		cadastroAssuntoService.excluir(assuntoId);
		
	}

}
