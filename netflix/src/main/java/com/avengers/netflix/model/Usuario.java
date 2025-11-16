package com.avengers.netflix.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;

import java.time.LocalDate;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_favoritos", // Nome da tabela de ligação
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "midia_id")
    )

    private Set<Midia> favoritos = new HashSet<>();

}
