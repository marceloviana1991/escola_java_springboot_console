package br.com.marcelo.escola.repository;

import br.com.marcelo.escola.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Optional<Aluno> findByNomeContainingIgnoreCase(String nome);
}
