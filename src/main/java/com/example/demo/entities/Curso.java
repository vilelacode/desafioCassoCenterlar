package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_cursos")
///*@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter*/
@Data
public class Curso {
    @Id
    private Long id;
    @Column(name = "curso_nome")
    private String nome;
    @Column(name = "curso_descricao")
    private String descricao;

    @OneToMany(mappedBy = "curso")
    private List<Matricula> matricula = new ArrayList<>();

}
