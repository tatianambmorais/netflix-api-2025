package com.avengers.netflix.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCompleto;
    private LocalDate dataNascimento;
    private String email;
    private String senhaHash;
    private String numeroCartao;
    private String validadeCartao;
    private String codigoSeguranca;
    private String nomeTitular;
    private String cpfCnpj;
    private boolean confirmado;
    private String token;

}
