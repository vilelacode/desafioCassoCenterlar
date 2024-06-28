package com.example.demo.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_alunos")
/*@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter*/
@Data
public class Aluno {

    @Id
    @Column(name = "aluno_id")
    private Long id;
    @Column(name = "aluno_nome")
    private String nome;
    @OneToMany(mappedBy = "aluno")
    private List<Matricula> matriculas = new ArrayList<>();

}
