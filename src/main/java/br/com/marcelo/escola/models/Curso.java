package br.com.marcelo.escola.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate data;
    @Enumerated(EnumType.STRING)
    private TurnoCurso turnoCurso;
    @ManyToMany(mappedBy = "cursos")
    private List<Aluno> alunos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TurnoCurso getTurnoCurso() {
        return turnoCurso;
    }

    public void setTurnoCurso(TurnoCurso turnoCurso) {
        this.turnoCurso = turnoCurso;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public LocalDate getData() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        data.format(formatador);
        return data;
    }

    public void setData(String data) {
        String[] dataArray = data.split("/");
        int ano = Integer.parseInt(dataArray[0]);
        int mes = Integer.parseInt(dataArray[1]);
        int dia = Integer.parseInt(dataArray[2]);
        this.data = LocalDate.of(ano, mes, dia);
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", turnoCurso=" + turnoCurso +
                ", alunos=" + alunos +
                '}';
    }
}
