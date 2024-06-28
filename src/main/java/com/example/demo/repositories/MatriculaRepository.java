package com.example.demo.repositories;

import com.example.demo.entities.Curso;
import com.example.demo.entities.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    List<Matricula> findByCursoId(Long id);

}


