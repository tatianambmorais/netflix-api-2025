package com.avengers.netflix.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class Midia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String genero;
    private String relevancia;
    @Column(length = 1000)
    private String sinopse;
    private int duracao;
    private int ano;
    private String trailer;


}
