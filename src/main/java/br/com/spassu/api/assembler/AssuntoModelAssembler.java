package br.com.spassu.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.spassu.api.model.AssuntoModel;
import br.com.spassu.domain.model.Assunto;

@Component
public class AssuntoModelAssembler {
	
	@Autowired
    private ModelMapper modelMapper;
    
    public AssuntoModel toModel(Assunto assunto) {
        return modelMapper.map(assunto, AssuntoModel.class);
    }
    
    public List<AssuntoModel> toCollectionModel(List<Assunto> assuntos) {
        return assuntos.stream()
                .map(assunto -> toModel(assunto))
                .collect(Collectors.toList());
    }

}
