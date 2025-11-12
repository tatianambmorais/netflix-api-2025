package com.avengers.netflix.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@MappedSuperclass
public abstract class Midia {
    private String titulo;
    private String genero;
    private String relevancia;
    private String sinopse;
    private int duracao;
    private int ano;
    private String trailer;
}
