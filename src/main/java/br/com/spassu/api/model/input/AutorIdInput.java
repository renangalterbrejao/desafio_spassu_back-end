package br.com.spassu.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AutorIdInput {
	
	@NotNull
    private Long codAu;

}
