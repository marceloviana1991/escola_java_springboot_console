package br.com.marcelo.escola.repository;

import br.com.marcelo.escola.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
