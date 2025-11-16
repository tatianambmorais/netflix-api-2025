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
    private String cpfCnpj;
    private boolean confirmado;
    private String token;
    private TipoUsuario tipoUsuario;
    @Column(nullable = true)
    private String numeroCartao;
    @Column(nullable = true)
    private String validadeCartao;
    @Column(nullable = true)
    private String codigoSeguranca;
    @Column(nullable = true)
    private String nomeTitular;
    @Column(nullable = true)
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cartao> cartoes;

}
