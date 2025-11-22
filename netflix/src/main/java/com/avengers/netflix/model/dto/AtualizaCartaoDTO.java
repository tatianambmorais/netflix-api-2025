package com.avengers.netflix.model.dto;

public record AtualizaCartaoDTO(
        String numero,
        String nome,
        String validade,
        String cvv
) {}
