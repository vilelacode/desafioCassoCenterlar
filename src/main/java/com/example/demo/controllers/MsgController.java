package com.example.demo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("messages")
public class MsgController {


    @GetMapping
    public ResponseEntity testConnection(){
        log.info("Conexão realizada com sucesso!");
        return ResponseEntity.ok("Home...");
    }

}
