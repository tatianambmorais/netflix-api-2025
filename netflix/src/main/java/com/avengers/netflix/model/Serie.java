package com.avengers.netflix.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Serie extends Midia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int temporadas;
	private int episodios;
}