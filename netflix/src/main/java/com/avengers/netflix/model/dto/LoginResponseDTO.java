package com.avengers.netflix.model.dto;


public record LoginResponseDTO(
        String status,
        String nome,
        String email,
        String tipo
) {}
