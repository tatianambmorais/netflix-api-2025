package com.avengers.netflix.model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Serie extends Midia {

	private int temporadas;
	private int episodios;
}