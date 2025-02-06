package br.com.spassu.domain.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Object objeto;
	private static final String MSG_ERRO_LOG = "Ocorreu o seguinte erro: ";
	
	public NegocioException(String mensagem) {
		super(mensagem);
		log.info(MSG_ERRO_LOG+mensagem);
	}
	
	public NegocioException(String mensagem, Throwable causa) {
		super(mensagem, causa);
		log.info(MSG_ERRO_LOG+mensagem+" com causa: "+causa.toString());
	}
	
	public NegocioException(String mensagem, Throwable causa, Object objeto) {
		super(mensagem, causa);
		this.objeto = objeto;
		log.info(MSG_ERRO_LOG+mensagem+" com causa: "+causa.toString());
	}

	public Object getObjeto() {
		return objeto;
	}

	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}
	
}
