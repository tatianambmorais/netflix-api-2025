package com.avengers.netflix.repository;

import com.avengers.netflix.model.Assinatura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssinaturaRepository extends JpaRepository<Assinatura, Long> {
    Assinatura findByNome(String assinatura);
}
