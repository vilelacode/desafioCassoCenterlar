package com.example.demo.entities.record;

import lombok.Builder;

@Builder
public record AlunoDto(Long id, String nome) {
}
