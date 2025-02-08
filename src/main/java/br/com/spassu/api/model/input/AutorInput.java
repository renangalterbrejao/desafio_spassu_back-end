package br.com.spassu.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AutorInput {
	
	@NotBlank
	//@Size(max = 40)
    private String nome;

}
