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
public class AutorTest {

    private String jsonAutorCorreto;
    private String jsonAutorIncorreto;
    private String jsonAutorRepetido;
    private String jsonAutorAlteracao;
    
    @LocalServerPort
	private int port;

    @BeforeEach
    public void setUp() {
    	RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/autores";
    	
        jsonAutorCorreto = ResourceUtils.getContentFromResource("/json/correto/autor-correto.json");
        jsonAutorIncorreto = ResourceUtils.getContentFromResource("/json/incorreto/autor-incorreto.json");
        jsonAutorRepetido = ResourceUtils.getContentFromResource("/json/incorreto/autor-repetido.json");
        jsonAutorAlteracao = ResourceUtils.getContentFromResource("/json/correto/autor-a-ser-alterado.json");
    }

    @Test
    public void deveRetornarStatus400_QuandoCadastrarAutorIncorretamente() {
        RestAssured.given()
                .body(jsonAutorIncorreto)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
    
    @Test
    public void deveRetornarStatus201_QuandoCadastrarAutorCorretamente() {
        RestAssured.given()
                .body(jsonAutorCorreto)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }
    
    @Test
    public void deveRetornarStatus400_QuandoCadastrarAutorJaExistente() {
        RestAssured.given()
                .body(jsonAutorRepetido)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
    
    @Test
    public void deveRetornarStatus400_QuandoConsultarAutorInexistente() {
		
		RestAssured.given()
	        .pathParam("autorId", Integer.valueOf(10))
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	    .when()
	        .get("/{autorId}")
	    .then()
	        .statusCode(HttpStatus.BAD_REQUEST.value());
		
    }
    
    @Test
    public void deveRetornarStatus200_QuandoConsultarAutorExistente() {
		
		RestAssured.given()
	        .pathParam("autorId", Integer.valueOf(1))
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	    .when()
	        .get("/{autorId}")
	    .then()
	        .statusCode(HttpStatus.OK.value());
		
    }
    
    @Test
    public void deveRetornarStatus200_QuandoAtualizarAutor() {
		
		RestAssured.given()
			.body(jsonAutorAlteracao)
	        .pathParam("autorId", Integer.valueOf(2))
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	    .when()
	        .put("/{autorId}")
	    .then()
	        .statusCode(HttpStatus.OK.value());
		
    }
    
    @Test
    public void deveRetornarStatus204_QuandoExcluirAutor() {
		
		RestAssured.given()
	        .pathParam("autorId", Integer.valueOf(3))
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	    .when()
	        .delete("/{autorId}")
	    .then()
	        .statusCode(HttpStatus.NO_CONTENT.value());
		
    }
    
    @Test
    public void deveRetornarStatus409_QuandoExcluirAutorAssociado() {
		
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