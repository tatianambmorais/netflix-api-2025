package com.avengers.netflix.model.dto;



public record CadastroUsuarioDTO(
        String tipo, // ADMIN ou CLIENTE

        String nome,
        String dataNascimento,
        String email,
        String senha,
        String confirmaSenha,
        String cpfCnpj,

        String numeroCartao,
        String validadeCartao,
        String codSeguranca,
        String nomeTitular,
        String assinatura
) {}
