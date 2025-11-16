package com.avengers.netflix.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "filme")

public class Filme extends Midia {

}
