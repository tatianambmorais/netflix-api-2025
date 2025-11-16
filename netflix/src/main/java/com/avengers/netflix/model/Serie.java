package com.avengers.netflix.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "serie")

public class Serie extends Midia {
	private int temporadas;
	private int episodios;
}