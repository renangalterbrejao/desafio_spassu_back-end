package br.com.spassu.domain.service;

import org.springframework.stereotype.Service;

@Service
public interface LivroReportService {
	
	byte[] emitirLivroConsolidado();

}
