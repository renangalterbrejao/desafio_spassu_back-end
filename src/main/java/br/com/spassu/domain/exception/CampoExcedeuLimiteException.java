package br.com.spassu.domain.exception;

public class CampoExcedeuLimiteException extends NegocioException {
	
	private static final long serialVersionUID = 1L;
	
	public CampoExcedeuLimiteException(String mensagem) {
		super(mensagem);
	}
	
	public CampoExcedeuLimiteException(String nomeCampo, int numeroMaxCaracteres) {
        this(String.format("O campo %s não pode ser maior que %d caracteres", 
        		nomeCampo, numeroMaxCaracteres));
    }

}
