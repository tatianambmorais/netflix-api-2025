package com.avengers.netflix.service;

import com.avengers.netflix.model.Filme;
import com.avengers.netflix.model.Serie;
import com.avengers.netflix.repository.FilmeRepository;
import com.avengers.netflix.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MidiaService {

    private final FilmeRepository filmeRepository;
    private final SerieRepository serieRepository;

    @Autowired
    public MidiaService(FilmeRepository filmeRepository, SerieRepository serieRepository) {
        this.filmeRepository = filmeRepository;
        this.serieRepository = serieRepository;
    }

    public void cadastrarMidia(String tipo, String titulo, String genero, String relevancia,
                               String sinopse, int duracao, int ano, String trailer) {

        if (tipo.equals("F")) {
            Filme filme = new Filme();
            filme.setTitulo(titulo);
            filme.setGenero(genero);
            filme.setRelevancia(relevancia);
            filme.setSinopse(sinopse);
            filme.setDuracao(duracao);
            filme.setAno(ano);
            try {
                filme.setTrailer(trailer);
            } catch (Exception e) {
                System.out.println("URL inválida! Trailer não será salvo.");
            }
            filmeRepository.save(filme);
        }
        else if (tipo.equals("S")) {
            Serie serie = new Serie();
            serie.setTitulo(titulo);
            serie.setGenero(genero);
            serie.setRelevancia(relevancia);
            serie.setSinopse(sinopse);
            serie.setDuracao(duracao);
            serie.setAno(ano);
            serie.setTrailer(trailer);
            serieRepository.save(serie);
        }
        else {
            System.out.println("Tipo inválido! Use 'F' para Filme ou 'S' para Série.");
        }
    }
}

