package com.example.demo.services;

import com.example.demo.entities.Curso;
import com.example.demo.entities.record.CursoDto;
import com.example.demo.repositories.CursoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@Slf4j
public class CursoService {

    private Logger logger = Logger.getLogger(CursoService.class.getName());

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> listarTodosOsCursos() {
        try{
            List<Curso> result = cursoRepository.findAll();
            if(result.isEmpty()){
                throw new EntityNotFoundException("Não há cursos cadastrados!");
            }
            logger.info("Listando todos os cursos!");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Houve um erro ao listar os cursos!");
        }
    }

    public Curso buscarCursoPorId(Long id) {
        try {
            return cursoRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado!"));

        } catch (Exception e) {
            throw new RuntimeException("Houve um erro ao buscar o curso!");
        }

    }

    public Curso salvarCurso(Curso curso) {
        try{
            Curso result = cursoRepository.save(curso);
            logger.info("Curso salvo com sucesso!");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Houve um erro ao salvar o curso!");
        }

    }

    public void apagarCurso(Long id) {
        try {
            var curso = cursoRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado!"));
            cursoRepository.delete(curso);
            logger.info("Curso apagado com sucesso!");
        }
        catch (Exception e) {
                throw new RuntimeException("Houve um erro ao apagar o curso!");
            }
    }

    public Curso atualizarCursoPorId(Long id, CursoDto curso) {
        try {
            Curso obj = cursoRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado!"));
            obj.setNome(curso.nome());
            cursoRepository.save(obj);
            logger.info("Curso atualizado com sucesso!");
            return obj;
        } catch (Exception e) {
            throw new RuntimeException("Houve um erro ao atualizar o curso!");
        }

    }



}
