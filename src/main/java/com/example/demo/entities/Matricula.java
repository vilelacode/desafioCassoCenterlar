package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "tb_matriculas")
public class Matricula {

    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    private Double nota;

}
