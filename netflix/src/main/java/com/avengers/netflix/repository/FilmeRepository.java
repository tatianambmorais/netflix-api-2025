package com.avengers.netflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.avengers.netflix.model.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Integer> {
    Filme findByTitulo (String titulo);
}
