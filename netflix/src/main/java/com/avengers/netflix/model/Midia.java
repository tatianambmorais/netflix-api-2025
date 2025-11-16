package com.avengers.netflix.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class Midia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String genero;
    private String relevancia;
    @Column(length = 1000)
    private String sinopse;
    private Integer duracao;
    private Integer ano;
    private String trailer;


}
