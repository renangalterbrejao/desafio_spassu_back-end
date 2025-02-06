package br.com.spassu.domain.exception;

public class AutorNaoEncontradoException extends NegocioException {
	
	private static final long serialVersionUID = 1L;
	
	public AutorNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public AutorNaoEncontradoException(Long autorId) {
        this(String.format("Não existe um cadastro de autor com código %d", 
        		autorId));
    }

}
