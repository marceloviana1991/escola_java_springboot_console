package br.com.marcelo.escola;

import br.com.marcelo.escola.principal.Principal;
import br.com.marcelo.escola.repository.AlunoRepository;
import br.com.marcelo.escola.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLOutput;

@SpringBootApplication
public class EscolaApplication implements CommandLineRunner {
	@Autowired
	private CursoRepository cursoRepository;
	@Autowired
	private AlunoRepository alunoRepository;

	public static void main(String[] args) {
		SpringApplication.run(EscolaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(
				cursoRepository, alunoRepository
		);
		principal.exibeMenu();
	}
}
