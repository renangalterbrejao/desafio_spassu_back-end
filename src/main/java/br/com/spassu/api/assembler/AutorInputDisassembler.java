package br.com.spassu.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.spassu.api.model.input.AutorInput;
import br.com.spassu.domain.model.Autor;

@Component
public class AutorInputDisassembler {
	
	@Autowired
    private ModelMapper modelMapper;
    
    public Autor toDomainObject(AutorInput autorInput) {
        return modelMapper.map(autorInput, Autor.class);
    }
    
    public void copyToDomainObject(AutorInput autorInput, Autor autor) {
        modelMapper.map(autorInput, autor);
    }

}
