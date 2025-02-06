package br.com.spassu.infraestructure.service.report;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spassu.domain.model.dto.LivrosConsolidado;
import br.com.spassu.domain.service.LivroQueryService;
import br.com.spassu.domain.service.LivroReportService;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class PdfLivroReportService implements LivroReportService {
	
	@Autowired
	private LivroQueryService livroQueryService;
	
	@Override
	public byte[] emitirLivroConsolidado() {
		try {
			var inputStream = this.getClass().getResourceAsStream(
					"/relatorios/livros-consolidado.jasper");
			
			var parametros = new HashMap<String, Object>();
			parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
			
			List<LivrosConsolidado> livrosConsolidado = livroQueryService.consultarLivrosConsolidado();
			var dataSource = new JRBeanCollectionDataSource(livrosConsolidado);
			
			var jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);
		
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (Exception e) {
			throw new ReportException("Não foi possível emitir relatório de livros consolidado", e);
		}
	}

}
