package com.avengers.netflix.utils;

import com.avengers.netflix.model.Midia;
import org.springframework.data.jpa.domain.Specification;

public class ConteudoSpec {

    public static Specification<Midia> midiaTitle(String titulo){
        return (root, query, criteriaBuilder) -> {
            String padrao = "%" + titulo.toLowerCase() + "%";

            return criteriaBuilder.like(criteriaBuilder.lower(root.get("titulo")), padrao
            );
        };

};
}