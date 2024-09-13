package br.com.marcelo.escola.principal;

import br.com.marcelo.escola.models.Aluno;
import br.com.marcelo.escola.models.Curso;
import br.com.marcelo.escola.models.TurnoCurso;
import br.com.marcelo.escola.repository.AlunoRepository;
import br.com.marcelo.escola.repository.CursoRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private final Scanner leitura = new Scanner(System.in);
    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;

    public Principal(CursoRepository cursoRepository, AlunoRepository alunoRepository) {
        this.cursoRepository = cursoRepository;
        this.alunoRepository = alunoRepository;
    }


    public void exibeMenu() {
        var opcao = -1;

        while (opcao!= 9) {
            var menu = """
                    *** Escola de Cursos ***
                    
                    1- Cadastrar aluno
                    2- Cadastrar curso
                    3- Listar cursos
                    4- Listar alunos
                    5- Matricular aluno em curso
                    6_ Listar cursos por data
                    
                    9 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarAlunos();
                    break;
                case 2:
                    cadastrarCursos();
                    break;
                case 3:
                    listarCursos();
                    break;
                case 4:
                    listarAlunos();
                    break;
                case 5:
                    matricularAlunos();
                    break;
                case 6:
                    listarCursosPorData();
                    break;
                case 9:
                    System.out.println("Encerrando a aplicação!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void listarCursosPorData() {
        System.out.println("Informe a data inicial da pesquisa: ");
        String dataInicial = leitura.nextLine();
        System.out.println("Informe a data final da pesquisa: ");
        String dataFinal = leitura.nextLine();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInicialConvertida = LocalDate.from(formatador.parse(dataInicial));
        LocalDate dataFinalConvertida = LocalDate.from(formatador.parse(dataFinal));
        List<Curso> cursos = cursoRepository.findByDataBetween(
                dataInicialConvertida, dataFinalConvertida);
        cursos.forEach(System.out::println);
    }

    private void matricularAlunos() {
        System.out.println("Lista de Cursos cadastrados: ");
        listarCursos();
        System.out.println("Informe o Id do curso que deseja realizar a matrícula: ");
        long idCurso = Long.parseLong(leitura.nextLine());
        Optional<Curso> curso = cursoRepository.findById(idCurso);
        System.out.println("Lista de Alunos cadastraodos: ");
        listarAlunos();
        System.out.println("Informe o nome do aluno que deseja realizar a matrícula: ");
        String nomeAluno = leitura.nextLine();
        Optional<Aluno> aluno = alunoRepository.findByNomeContainingIgnoreCase(nomeAluno);
        if (aluno.isPresent() && curso.isPresent()) {
            System.out.println("Deseja matricular o aluno " + aluno.get().getNome() +
                    " no curso " + curso.get().getNome() + "? (S/N)");
            String cadastrar = leitura.nextLine();
            if (cadastrar.equalsIgnoreCase("s")) {
                aluno.get().getCursos().add(curso.get());
                alunoRepository.save(aluno.get());
            }
        }
    }

    private void listarAlunos() {
        List<Aluno> alunos = alunoRepository.findAll();
        alunos.forEach(System.out::println);
    }

    private void listarCursos() {
        List<Curso> cursos = cursoRepository.findAll();
        cursos.forEach(System.out::println);
    }

    private void cadastrarCursos() {
        String cadastrarNovo = "S";
        while (cadastrarNovo.equalsIgnoreCase("s")) {
            System.out.println("Informe o nome do curso: ");
            String nome = leitura.nextLine();
            System.out.println("Informe a data do curso: ");
            String data = leitura.nextLine();
            System.out.println("Informe o turno do curso: (manha, tarde ou noite)");
            TurnoCurso turno = TurnoCurso.valueOf(leitura.nextLine().toUpperCase());
            Curso curso = new Curso(nome, data, turno);
            cursoRepository.save(curso);
            System.out.println("Cadastrar novo curso? (S/N)");
            cadastrarNovo = leitura.nextLine();
        }
    }

    private void cadastrarAlunos() {
        String cadastrarNovo = "S";
        while (cadastrarNovo.equalsIgnoreCase("s")) {
            System.out.println("Informe o nome do aluno: ");
            String nome = leitura.nextLine();
            Aluno aluno = new Aluno(nome);
            alunoRepository.save(aluno);
            System.out.println("Cadastrar novo aluno? (S/N)");
            cadastrarNovo = leitura.nextLine();
        }
    }
}
