package br.com.spassu.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.spassu.api.model.AutorModel;
import br.com.spassu.domain.model.Autor;

@Component
public class AutorModelAssembler {
	
	@Autowired
    private ModelMapper modelMapper;
    
    public AutorModel toModel(Autor autor) {
        return modelMapper.map(autor, AutorModel.class);
    }
    
    public List<AutorModel> toCollectionModel(List<Autor> autores) {
        return autores.stream()
                .map(autor -> toModel(autor))
                .collect(Collectors.toList());
    }

}
