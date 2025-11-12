package com.avengers.netflix.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

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

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cartao> cartoes;

}
