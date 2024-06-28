package com.example.demo.services;

import com.example.demo.entities.Matricula;
import com.example.demo.entities.record.MatriculaDto;
import com.example.demo.repositories.MatriculaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class MatriculaService {

    private Logger logger = Logger.getLogger(CursoService.class.getName());

    private final MatriculaRepository matriculaRepository;

    public MatriculaService(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }

    public void apagarMatricula(Long id) {
        try{
            val matricula = matriculaRepository.findById(id)
                    .orElseThrow(
                            () -> new EntityNotFoundException("Matricula não encontrada!"));
            matriculaRepository.delete(matricula);
        }catch (Exception e) {
            throw new RuntimeException("Houve um erro ao apagar a matricula!");
        }

    }

    public List<Matricula> listarTodasAsMatriculas() {
        try{
            List<Matricula> result = matriculaRepository.findAll();
            if(result.isEmpty()){
                throw new EntityNotFoundException("Não há matriculas cadastradas!");
            }
            logger.info("Listando todas as matriculas!");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Houve um erro ao listar as matriculas!");
        }
    }

    public Matricula buscarMatriculaPorId(Long id) {
        try {
            Matricula result = matriculaRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Matricula não encontrada!"));
            logger.info("Matricula encontrada com sucesso!");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Houve um erro ao buscar a matricula!");
        }
    }

    public Matricula matricularAlunoEmCurso(Matricula matricula) {
            try {
                Matricula result = matriculaRepository.save(matricula);
                logger.info("Aluno matriculado com sucesso!");
                return result;
            } catch (Exception e) {
                throw new RuntimeException("Houve um erro ao matricular o aluno no curso!");
            }

        }


    public Matricula atualizarNotaDeMatricula(Long id, MatriculaDto dto) {
        try {
            Matricula matricula = matriculaRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Matricula não encontrada!"));
            matricula.setNota(dto.nota());
            return matriculaRepository.save(matricula);
        } catch (Exception e) {
            throw new RuntimeException("Houve um erro ao atualizar a nota da matricula!");
        }

    }

    public Object gerarBoletinDeCurso(Long id) {

        try{
            List<Matricula> matriculasDoCurso = matriculaRepository.findByCursoId(id);

            if (matriculasDoCurso.isEmpty()) {
                throw new EntityNotFoundException("Nenhuma matricula encontrada para o curso com id: " + id);
            }

            Map<String, Double> template= new HashMap<>();

            template.put("Nota média do curso", matriculasDoCurso.stream()
                    .mapToDouble(Matricula::getNota)
                    .average()
                    .orElse(0.0));

            return template;
        }catch (Exception e){
            throw new RuntimeException("Houve um erro ao gerar o boletin!");
        }
    }


}
