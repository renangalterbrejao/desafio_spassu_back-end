package br.com.spassu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.spassu.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
@SuppressWarnings("deprecation")
public class AssuntoTest {
	
	private String jsonAssuntoCorreto;
    private String jsonAssuntoIncorreto;
    private String jsonAssuntoAlteracao;
    
    @LocalServerPort
	private int port;
    
    @BeforeEach
    public void setUp() {
    	RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/assuntos";
    	
        jsonAssuntoCorreto = ResourceUtils.getContentFromResource("/json/correto/assunto-correto.json");
        jsonAssuntoIncorreto = ResourceUtils.getContentFromResource("/json/incorreto/assunto-incorreto.json");
        jsonAssuntoAlteracao = ResourceUtils.getContentFromResource("/json/correto/assunto-a-ser-alterado.json");
    }
    
    @Test
    public void deveRetornarStatus400_QuandoCadastrarAssuntoIncorretamente() {
        RestAssured.given()
                .body(jsonAssuntoIncorreto)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
    
    @Test
    public void deveRetornarStatus201_QuandoCadastrarAssuntoCorretamente() {
        RestAssured.given()
                .body(jsonAssuntoCorreto)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }
    
    @Test
    public void deveRetornarStatus400_QuandoConsultarAssuntoInexistente() {
		
		RestAssured.given()
	        .pathParam("assuntoId", Integer.valueOf(10))
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	    .when()
	        .get("/{assuntoId}")
	    .then()
	        .statusCode(HttpStatus.BAD_REQUEST.value());
		
    }
    
    @Test
    public void deveRetornarStatus200_QuandoConsultarAssuntoExistente() {
		
		RestAssured.given()
	        .pathParam("assuntoId", Integer.valueOf(1))
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	    .when()
	        .get("/{assuntoId}")
	    .then()
	        .statusCode(HttpStatus.OK.value());
		
    }
    
    @Test
    public void deveRetornarStatus200_QuandoAtualizarAssunto() {
		
		RestAssured.given()
			.body(jsonAssuntoAlteracao)
	        .pathParam("assuntoId", Integer.valueOf(2))
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	    .when()
	        .put("/{assuntoId}")
	    .then()
	        .statusCode(HttpStatus.OK.value());
		
    }
    
    @Test
    public void deveRetornarStatus204_QuandoExcluirAssunto() {
		
		RestAssured.given()
	        .pathParam("assuntoId", Integer.valueOf(3))
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	    .when()
	        .delete("/{assuntoId}")
	    .then()
	        .statusCode(HttpStatus.NO_CONTENT.value());
		
    }
    
    @Test
    public void deveRetornarStatus409_QuandoExcluirAssuntoAssociado() {
		
		RestAssured.given()
	        .pathParam("assuntoId", Integer.valueOf(1))
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	    .when()
	        .delete("/{assuntoId}")
	    .then()
	        .statusCode(HttpStatus.CONFLICT.value());
		
    }

}
