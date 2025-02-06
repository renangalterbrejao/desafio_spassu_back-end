package br.com.spassu.domain.exception;

public class LivroNaoEncontradoException extends NegocioException {
	
	private static final long serialVersionUID = 1L;
	
	public LivroNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public LivroNaoEncontradoException(Long livroId) {
        this(String.format("Não existe um cadastro de livro com código %d", 
                livroId));
    }

}
