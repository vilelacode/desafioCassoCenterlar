package com.example.demo.controllers;

import com.example.demo.entities.Matricula;
import com.example.demo.entities.record.MatriculaDto;
import com.example.demo.services.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;


    @GetMapping
    public ResponseEntity<?> listarTodasAsMatriculas() {
        return ResponseEntity.ok().body(matriculaService.listarTodasAsMatriculas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarMatriculaPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(matriculaService.buscarMatriculaPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> apagarMatriculaPorId(@PathVariable Long id) {
        matriculaService.apagarMatricula(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarMatricula(@PathVariable Long id,
                                    @RequestBody MatriculaDto matricula){
        return ResponseEntity.ok().body(matriculaService.atualizarNotaDeMatricula(id, matricula));
    }

    @PostMapping
    public ResponseEntity<?> criarMatricula(@RequestBody Matricula matricula) {
        return ResponseEntity.ok().body(matriculaService.matricularAlunoEmCurso(matricula));
    }



}
