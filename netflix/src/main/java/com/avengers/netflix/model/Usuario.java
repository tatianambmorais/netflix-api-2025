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
    private String cpfCnpj;
    private boolean confirmado;
    private String token;
    private TipoUsuario tipoUsuario;
    @OneToOne
    private Assinatura assinatura;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_favoritos",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "midia_id")
    )

    private Set<Midia> favoritos = new HashSet<>();

}
