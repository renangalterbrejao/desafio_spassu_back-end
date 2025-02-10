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
public class LivroTest {
	
	private String jsonLivroCorreto;
    private String jsonLivroIncorreto;
    private String jsonLivroIncorretoAssociacao;
    private String jsonLivroAlteracao;
    
    @LocalServerPort
	private int port;
    
    @BeforeEach
    public void setUp() {
    	RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/livros";
    	
        jsonLivroCorreto = ResourceUtils.getContentFromResource("/json/correto/livro-correto.json");
        jsonLivroIncorreto = ResourceUtils.getContentFromResource("/json/incorreto/livro-incorreto.json");
        jsonLivroIncorretoAssociacao = ResourceUtils.getContentFromResource("/json/incorreto/livro-incorreto-associacao.json");
        jsonLivroAlteracao = ResourceUtils.getContentFromResource("/json/correto/livro-a-ser-alterado.json");
    }
    
    @Test
    public void deveRetornarStatus400_QuandoCadastrarLivroIncorretamente() {
        RestAssured.given()
                .body(jsonLivroIncorreto)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
    
    @Test
    public void deveRetornarStatus201_QuandoCadastrarLivroCorretamente() {
        RestAssured.given()
                .body(jsonLivroCorreto)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }
    
    @Test
    public void deveRetornarStatus400_QuandoCadastrarLivroCorretamente() {
        RestAssured.given()
                .body(jsonLivroIncorretoAssociacao)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
    
    @Test
    public void deveRetornarStatus400_QuandoConsultarLivroInexistente() {
		
		RestAssured.given()
	        .pathParam("livroId", Integer.valueOf(10))
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	    .when()
	        .get("/{livroId}")
	    .then()
	        .statusCode(HttpStatus.BAD_REQUEST.value());
		
    }
    
    @Test
    public void deveRetornarStatus200_QuandoConsultarLivroExistente() {
		
		RestAssured.given()
	        .pathParam("livroId", Integer.valueOf(1))
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	    .when()
	        .get("/{livroId}")
	    .then()
	        .statusCode(HttpStatus.OK.value());
		
    }
    
    @Test
    public void deveRetornarStatus200_QuandoAtualizarLivro() {
		
		RestAssured.given()
			.body(jsonLivroAlteracao)
	        .pathParam("livroId", Integer.valueOf(1))
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	    .when()
	        .put("/{livroId}")
	    .then()
	        .statusCode(HttpStatus.OK.value());
		
    }
    
    @Test
    public void deveRetornarStatus204_QuandoExcluirLivro() {
		
		RestAssured.given()
	        .pathParam("livroId", Integer.valueOf(3))
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	    .when()
	        .delete("/{livroId}")
	    .then()
	        .statusCode(HttpStatus.NO_CONTENT.value());
		
    }

}
