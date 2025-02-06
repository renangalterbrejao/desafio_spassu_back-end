package br.com.spassu.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.spassu.domain.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
	
	@Query("SELECT l FROM Livro l WHERE "
			+ "l.titulo = :titulo "
			+ "AND l.editora = :editora ")
    Optional<Livro> findBooksByTituloAndEditora(
    		@Param("titulo") String titulo, 
    		@Param("editora") String editora
    );

}
