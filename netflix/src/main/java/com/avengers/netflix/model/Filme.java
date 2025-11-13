package com.avengers.netflix.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Filme extends Midia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
}
