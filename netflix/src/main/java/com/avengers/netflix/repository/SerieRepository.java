package com.avengers.netflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.avengers.netflix.model.Serie;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {

    Serie findByTitulo(String titulo);
}
