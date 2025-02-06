package br.com.spassu.domain.exception;

public class AssuntoNaoEncontradoException extends NegocioException {
	
	private static final long serialVersionUID = 1L;
	
	public AssuntoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public AssuntoNaoEncontradoException(Long assuntoId) {
        this(String.format("Não existe um cadastro de assunto com código %d", 
        		assuntoId));
    }

}
