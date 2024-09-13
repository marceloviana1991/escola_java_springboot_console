package br.com.marcelo.escola.repository;

import br.com.marcelo.escola.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    public List<Curso> findByDataBetween(LocalDate dataInicial, LocalDate dataFinal);
}
