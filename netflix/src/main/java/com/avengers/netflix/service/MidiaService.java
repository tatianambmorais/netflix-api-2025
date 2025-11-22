package com.avengers.netflix.service;

import com.avengers.netflix.model.Filme;
import com.avengers.netflix.model.Midia;
import com.avengers.netflix.model.Serie;
import com.avengers.netflix.model.dto.MidiaDTO;
import com.avengers.netflix.repository.FilmeRepository;
import com.avengers.netflix.repository.MidiaRepository;
import com.avengers.netflix.repository.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MidiaService {

    private final FilmeRepository filmeRepository;
    private final SerieRepository serieRepository;
    private final MidiaRepository midiaRepository;

    public MidiaService(FilmeRepository filmeRepository,
                        SerieRepository serieRepository,
                        MidiaRepository midiaRepository) {
        this.filmeRepository = filmeRepository;
        this.serieRepository = serieRepository;
        this.midiaRepository = midiaRepository;
    }

    public void cadastrarMidia(MidiaDTO dto) {

        String tipo = dto.tipo().toUpperCase();

        switch (tipo) {

            case "F" -> {
                Filme filme = new Filme();
                filme.setTitulo(dto.titulo());
                filme.setGenero(dto.genero());
                filme.setRelevancia(dto.relevancia());
                filme.setSinopse(dto.sinopse());
                filme.setDuracao(dto.duracao());
                filme.setAno(dto.ano());
                filme.setTrailer(dto.trailer());

                filmeRepository.save(filme);
            }

            case "S" -> {
                Serie serie = new Serie();
                serie.setTitulo(dto.titulo());
                serie.setGenero(dto.genero());
                serie.setRelevancia(dto.relevancia());
                serie.setSinopse(dto.sinopse());
                serie.setDuracao(dto.duracao());
                serie.setAno(dto.ano());
                serie.setTrailer(dto.trailer());
//                serie.setTemporadas(dto.temporadas());
//                serie.setEpisodios(dto.episodios());

                serieRepository.save(serie);
            }

            default -> throw new IllegalArgumentException("Tipo inválido. Use 'F' ou 'S'.");
        }
    }

    public List<Filme> listarFilmes() { return filmeRepository.findAll(); }
    public List<Serie> listarSeries() { return serieRepository.findAll(); }

    public Midia buscarPorId(Long id) {
        return midiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mídia não encontrada!"));
    }

}
