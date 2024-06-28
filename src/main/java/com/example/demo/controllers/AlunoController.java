package com.example.demo.controllers;

import com.example.demo.entities.Aluno;
import com.example.demo.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<?> listarTodosOsAlunos() {
        return ResponseEntity.ok().body(alunoService.listarTodosOsAlunos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(alunoService.buscarAlunoPorId(id));
    }

    @PostMapping()
    public ResponseEntity<?> salvarAluno(@RequestBody Aluno aluno) {
        return ResponseEntity.status(201).body(alunoService.salvarAluno(aluno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> apagarAluno(@PathVariable  Long id) {
        alunoService.apagarAluno(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarAluno(@PathVariable Long id,
                                    @RequestBody Aluno aluno) {
        return ResponseEntity.ok().body(alunoService.atualizarAlunoExistente(id, aluno.getNome()));
    }


}
