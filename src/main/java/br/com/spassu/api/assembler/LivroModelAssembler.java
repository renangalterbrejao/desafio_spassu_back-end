package br.com.spassu.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.spassu.api.model.LivroModel;
import br.com.spassu.domain.model.Livro;

@Component
public class LivroModelAssembler {
	
	@Autowired
    private ModelMapper modelMapper;
    
    public LivroModel toModel(Livro livro) {
        return modelMapper.map(livro, LivroModel.class);
    }
    
    public List<LivroModel> toCollectionModel(List<Livro> livros) {
        return livros.stream()
                .map(livro -> toModel(livro))
                .collect(Collectors.toList());
    }

}
