package com.example.demo.controllers;

import com.example.demo.entities.Curso;
import com.example.demo.entities.record.CursoDto;
import com.example.demo.services.CursoService;
import com.example.demo.services.MatriculaService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private MatriculaService matriculaService;


    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(cursoService.listarTodosOsCursos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(cursoService.buscarCursoPorId(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Curso curso) {
        return ResponseEntity.ok().body(cursoService.salvarCurso(curso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        cursoService.apagarCurso(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody CursoDto curso) {
        return ResponseEntity.ok().body(cursoService.atualizarCursoPorId(id, curso));
    }

    @GetMapping("{id}/media")
    public ResponseEntity<?> getMedia(@PathVariable  Long id) {
        return ResponseEntity.ok().body(matriculaService.gerarBoletinDeCurso(id));
    }





}
