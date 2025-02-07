package br.com.spassu.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.spassu.domain.service.LivroReportService;
import io.swagger.annotations.Api;

@Api(tags = "Relatorios")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/relatorios", produces = MediaType.APPLICATION_JSON_VALUE)
public class RelatoriosController {
	
	@Autowired
	private LivroReportService livroReportService;
	
	@GetMapping(path = "/livros-consolidado-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> emitirLivrosConsolidados() {
		
		byte[] bytesPdf = livroReportService.emitirLivroConsolidado();
		
		var headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=livros-consolidado.pdf");
		
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.headers(headers)
				.body(bytesPdf);
	}
	
}
