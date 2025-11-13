package com.avengers.netflix.service;

import com.avengers.netflix.model.Filme;
import com.avengers.netflix.model.Serie;
import com.avengers.netflix.repository.FilmeRepository;
import com.avengers.netflix.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

	public void exibirDetalhesFilme(String titulo) {
		Filme filme = filmeRepository.findByTitulo(titulo);
		if (filme != null) {
			System.out.println("\n=== DETALHES DO FILME ===");
			System.out.println("Título: " + filme.getTitulo());
			System.out.println("Gênero: " + filme.getGenero());
			System.out.println("Ano: " + filme.getAno());
			System.out.println("Duração: " + filme.getDuracao() + " minutos");
			System.out.println("Relevância: " + filme.getRelevancia());
			System.out.println("Sinopse: " + filme.getSinopse());
			System.out.println("Trailer: " + filme.getTrailer());
		} else {
			System.out.println("Filme não encontrado!");
		}
	}

	public void exibirDetalhesSerie(String titulo) {
		Serie serie = serieRepository.findByTitulo(titulo);
		if (serie != null) {
			System.out.println("\n=== DETALHES DA SÉRIE ===");
			System.out.println("Título: " + serie.getTitulo());
			System.out.println("Gênero: " + serie.getGenero());
			System.out.println("Ano: " + serie.getAno());
			System.out.println("Duração: " + serie.getDuracao() + " minutos por episódio");
			System.out.println("Temporadas: " + serie.getTemporadas());
			System.out.println("Episódios: " + serie.getEpisodios());
			System.out.println("Relevância: " + serie.getRelevancia());
			System.out.println("Sinopse: " + serie.getSinopse());
			System.out.println("Trailer: " + serie.getTrailer());
		} else {
			System.out.println("Série não encontrada!");
		}
	}

	public void listarFilmes() {
		List<Filme> filmes = filmeRepository.findAll();
		if (filmes.isEmpty()) {
			System.out.println("Nenhum filme cadastrado.");
		} else {
			System.out.println("\n=== FILMES DISPONÍVEIS ===");
			for (Filme filme : filmes) {
				System.out.println("- " + filme.getTitulo() + " (" + filme.getAno() + ")");
			}
		}
	}

	public void listarSeries() {
		List<Serie> series = serieRepository.findAll();
		if (series.isEmpty()) {
			System.out.println("Nenhuma série cadastrada.");
		} else {
			System.out.println("\n=== SÉRIES DISPONÍVEIS ===");
			for (Serie serie : series) {
				System.out.println("- " + serie.getTitulo() + " (" + serie.getAno() + ")");
			}
		}
	}
}

