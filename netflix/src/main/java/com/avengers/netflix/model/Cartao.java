package com.avengers.netflix.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "cartoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cartao{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private String nomeImpresso;
    private String validade;
    private String cvv;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}

