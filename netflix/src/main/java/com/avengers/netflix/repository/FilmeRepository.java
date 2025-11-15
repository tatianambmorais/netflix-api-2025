package com.avengers.netflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.avengers.netflix.model.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
    Filme findByTitulo (String titulo);
}
