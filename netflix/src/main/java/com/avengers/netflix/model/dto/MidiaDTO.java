package com.avengers.netflix.model.dto;

public record MidiaDTO(
        String tipo,        // "F" ou "S"
        String titulo,
        String genero,
        String relevancia,
        String sinopse,
        Integer duracao,
        Integer ano,
        String trailer
) {}
