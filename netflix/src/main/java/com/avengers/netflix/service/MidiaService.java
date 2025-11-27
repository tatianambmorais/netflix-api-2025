package com.avengers.netflix.service;

import com.avengers.netflix.model.Filme;
import com.avengers.netflix.model.Midia;
import com.avengers.netflix.model.Serie;
import com.avengers.netflix.model.dto.MidiaDTO;
import com.avengers.netflix.repository.FilmeRepository;
import com.avengers.netflix.repository.MidiaRepository;
import com.avengers.netflix.repository.SerieRepository;
import com.avengers.netflix.utils.ConteudoSpec;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Midia> findMidiaByTitle(String titulo){
        Specification<Midia> spec = ConteudoSpec.midiaTitle(titulo);
        return midiaRepository.findAll(spec);
    }

    // Item 1: Ordenar por Título
    public List<MidiaDTO> listarTodasOrdenadas() {
        return converterLista(midiaRepository.findAllOrderedByTitulo());
    }

    // Item 2: Filtrar por Gênero
    public List<MidiaDTO> buscarPorGenero(String genero) {
        return converterLista(midiaRepository.findByGenero(genero));
    }

    // Item 3: Top N conteudos (O controller define a quantidade, ex: 5)
    public List<MidiaDTO> buscarTopRelevantes(int quantidade) {
        return converterLista(midiaRepository.findTopMidias(PageRequest.of(0, quantidade)));
    }

    // Item 4: Lançados após ano X
    public List<MidiaDTO> buscarLancamentosRecentes(int ano) {
        return converterLista(midiaRepository.findLancadosApos(ano));
    }

    // Item 7: Apenas com Trailer
    public List<MidiaDTO> buscarComTrailer() {
        return converterLista(midiaRepository.findComTrailer());
    }

    // Item 8: Busca por palavra-chave
    public List<MidiaDTO> buscarPorTermo(String termo) {
        return converterLista(midiaRepository.searchByTermo(termo));
    }

    // --- Metodo Auxiliar de Conversão ---
    private List<MidiaDTO> converterLista(List<Midia> midias) {
        return midias.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public MidiaDTO toDTO(Midia midia) {
        String tipo = (midia instanceof Filme) ? "F" : "S";

        // Verifica se é filme para pegar a duração, senão null
        Integer duracao = (midia instanceof Filme) ? ((Filme) midia).getDuracao() : null;
        String relevancia = String.valueOf(midia.getRelevancia());

        return new MidiaDTO(
                tipo,
                midia.getTitulo(),
                midia.getGenero(),
                relevancia,
                midia.getSinopse(),
                duracao,
                midia.getAno(),
                midia.getTrailerUrl()
        );
    }
}
