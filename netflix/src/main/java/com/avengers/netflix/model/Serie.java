package com.avengers.netflix.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Serie extends Midia{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String genero;
    private String relevancia;
    private String sinopse;
    private int duracao;
    private int ano;
    private int temporadas;
    private int episodios;
    private String trailer;

}