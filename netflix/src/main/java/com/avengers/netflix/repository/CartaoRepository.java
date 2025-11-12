package com.avengers.netflix.repository;

import com.avengers.netflix.model.Cartao;
import com.avengers.netflix.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    List<Cartao> findByUsuario(Usuario usuario);
}
