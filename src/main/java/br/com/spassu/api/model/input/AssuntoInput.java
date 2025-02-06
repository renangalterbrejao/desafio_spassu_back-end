package br.com.spassu.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AssuntoInput {
	
	@NotBlank
    private String descricao;

}
