package br.com.spassu.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AssuntoInput {
	
	@NotBlank
	//@Size(max = 20)
    private String descricao;

}
