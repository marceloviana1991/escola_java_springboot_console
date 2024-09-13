package br.com.marcelo.escola.principal;

import java.util.Scanner;

public class Principal {

    private final Scanner leitura = new Scanner(System.in);

    public void exibeMenu() {
        var opcao = -1;

        while (opcao!= 9) {
            var menu = """
                    *** Escola de Cursos ***
                    
                    1- Cadastrar aluno
                    2- Cadastrar curso
                    3- Listar cursos
                    4- Buscar alunos por curso
                    
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
                    buscarAlunosPorCurso();
                    break;
                case 9:
                    System.out.println("Encerrando a aplicação!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void buscarAlunosPorCurso() {
    }

    private void listarCursos() {
    }

    private void cadastrarCursos() {
    }

    private void cadastrarAlunos() {
    }
}
