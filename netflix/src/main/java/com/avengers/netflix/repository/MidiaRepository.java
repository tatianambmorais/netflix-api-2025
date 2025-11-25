package com.avengers.netflix.repository;


import com.avengers.netflix.model.Midia;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MidiaRepository extends JpaRepository<Midia, Long>, JpaSpecificationExecutor<Midia> {
    // 1. Ordenar conteudo por titulo (asc)
    // Serve para Filme e Serie
    @Query("SELECT m FROM Midia m ORDER BY m.titulo ASC")
    List<Midia> findAllOrderedByTitulo();

    // 2. Filtrar por genero (case-insensitive), ordenar por título
    @Query("SELECT m FROM Midia m WHERE LOWER(m.genero) = LOWER(:genero) ORDER BY m.titulo ASC")
    List<Midia> findByGenero(@Param("genero") String genero);

    // 3. Buscar top N conteudos por relevância
    // Lembre-se: Quem define o "N" é o Pageable passado pelo Service
    @Query("SELECT m FROM Midia m ORDER BY m.relevancia DESC")
    List<Midia> findTopMidias(Pageable pageable);

    // 4. Listar conteúdos lançados após ano X
    @Query("SELECT m FROM Midia m WHERE m.anoLancamento > :ano")
    List<Midia> findLancadosApos(@Param("ano") int ano);

    // 7. Listar conteúdos com trailer_url não nulo
    @Query("SELECT m FROM Midia m WHERE m.trailerUrl IS NOT NULL")
    List<Midia> findComTrailer();

    // 8. Buscar por palavra-chave no título/sinopse
    @Query("SELECT m FROM Midia m WHERE LOWER(m.titulo) LIKE LOWER(CONCAT('%', :termo, '%')) " +
            "OR LOWER(m.sinopse) LIKE LOWER(CONCAT('%', :termo, '%')) " +
            "ORDER BY m.relevancia DESC")
    List<Midia> searchByTermo(@Param("termo") String termo);

}

