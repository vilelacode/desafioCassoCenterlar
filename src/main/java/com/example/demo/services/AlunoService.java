package com.example.demo.services;

import com.example.demo.entities.Aluno;
import com.example.demo.entities.Curso;
import com.example.demo.entities.Matricula;
import com.example.demo.entities.record.AlunoDto;
import com.example.demo.repositories.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Service
@Slf4j
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    Logger logger = Logger.getLogger(AlunoService.class.getName());

    public List<AlunoDto> listarTodosOsAlunos() {
        try{
            List<Aluno> result = repository.findAll();
            if(result.isEmpty()){
                logger.info("Não há alunos cadastrados!");
            }
            log.info("Listando todos os alunos!");

            return result.stream().map( x -> {
                return AlunoDto.builder()
                        .id(x.getId())
                        .nome(x.getNome())
                        .build();
            }).toList();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Houve um erro ao listar os alunos!");
        }
    }

    public Aluno buscarAlunoPorId(Long id) {
        try {
            Aluno result = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado!"));
            logger.info("Aluno encontrado com sucesso!");
            return result;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Houve um erro ao buscar o aluno!");
        }
    }

    public Aluno salvarAluno(Aluno aluno) {
        try{
            Aluno result = repository.save(aluno);
            logger.info("Aluno salvo com sucesso!");
            return result;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Houve um erro ao salvar o aluno!");
        }
    }

    public void apagarAluno(Long id) {
        try {
            var aluno = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado!"));
            repository.delete(aluno);
            logger.info("Aluno apagado com sucesso!");
        }
        catch (IllegalArgumentException e) {
            throw new RuntimeException("Houve um erro ao apagar o aluno!");
        }
    }

    public Aluno atualizarAlunoExistente(Long id, String nome){

        try {
            var aluno = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado!"));
            aluno.setNome(nome);
            repository.save(aluno);
            logger.info("Aluno atualizado com sucesso!");
            return aluno;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Houve um erro ao atualizar o aluno!");
        }
    }

    public List<Curso> listarCursosDeAluno(Long id) {
        try {
            Aluno aluno = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado!"));

            List<Curso> cursos = aluno.getMatriculas().stream()
                    .map(Matricula::getCurso)
                    .toList();
            logger.info("Listando todos os cursos do aluno!");
            return cursos;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Houve um erro ao listar os cursos do aluno!");
        }
    }

}


