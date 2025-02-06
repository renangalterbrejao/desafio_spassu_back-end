package br.com.spassu.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.spassu.api.model.input.AssuntoInput;
import br.com.spassu.domain.model.Assunto;

@Component
public class AssuntoInputDisassembler {
	
	@Autowired
    private ModelMapper modelMapper;
    
    public Assunto toDomainObject(AssuntoInput assuntoInput) {
        return modelMapper.map(assuntoInput, Assunto.class);
    }
    
    public void copyToDomainObject(AssuntoInput assuntoInput, Assunto assunto) {
        modelMapper.map(assuntoInput, assunto);
    }

}
