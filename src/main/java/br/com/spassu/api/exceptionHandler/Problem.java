package br.com.spassu.api.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {
	
	private Integer status; //Status http (RFC 7807)
	private OffsetDateTime timestamp;
	private String type; //URI que especifica o tipo do problema (RFC 7807)
	private String title; //Descrição mais legível para humanos (RFC 7807)
	private String detail; //Descrição técnica mais detalhada (RFC 7807)
	private String userMessage; //Mensagem para o usuário final
	private List<Object> objects;
	
	@Getter
	@Builder
	public static class Object {
		private String name;
		private String userMessage;
	}
	
	//private String mensagem;
	//private Object objeto

}
