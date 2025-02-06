package br.com.spassu.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.spassu.api.model.input.LivroInput;
import br.com.spassu.domain.model.Livro;

@Component
public class LivroInputDisassembler {
	
	@Autowired
    private ModelMapper modelMapper;
    
    public Livro toDomainObject(LivroInput livroInput) {
        return modelMapper.map(livroInput, Livro.class);
    }
    
    public void copyToDomainObject(LivroInput livroInput, Livro livro) {
        modelMapper.map(livroInput, livro);
    }

}
