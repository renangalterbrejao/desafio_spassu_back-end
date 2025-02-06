package br.com.spassu.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.spassu.domain.model.Assunto;

@Repository
public interface AssuntoRepository extends JpaRepository<Assunto, Long> {

}
