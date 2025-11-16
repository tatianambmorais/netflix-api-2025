package com.avengers.netflix.view;

import com.avengers.netflix.service.MidiaService;
import com.avengers.netflix.service.UsuarioService;
import com.avengers.netflix.model.Filme;
import com.avengers.netflix.model.Serie;
import com.avengers.netflix.model.Midia;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class TelaVisualizacaoMidia {

	private final MidiaService midiaService;
    private final UsuarioService usuarioService;

    public TelaVisualizacaoMidia(MidiaService midiaService, UsuarioService usuarioService) {
        this.midiaService = midiaService;
        this.usuarioService = usuarioService;
    }

	public void exibirMenu(String emailUsuario) {
        Scanner scanner = new Scanner(System.in);
        String opcao;
        String titulo;

		do {
			System.out.println("\n=== VISUALIZAÇÃO DE MÍDIA ===");
			System.out.println("1 - Listar Filmes");
			System.out.println("2 - Listar Séries");
			System.out.println("3 - Ver Detalhes de Filme");
			System.out.println("4 - Ver Detalhes de Série");
            System.out.println("5 - Ver Favoritos");
			System.out.println("0 - Voltar");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextLine();

			switch (opcao) {
				case "1":
                    listarMidasEImprimir(midiaService.listarFilmes());
					break;
				case "2":
                    listarMidasEImprimir(midiaService.listarSeries());
                    break;
				case "3":
					System.out.print("Digite o título do filme: ");
					titulo = scanner.nextLine();
                    exibirDetalhesEGerenciarFavorito(titulo, "F", emailUsuario);
                    break;
				case "4":
					System.out.print("Digite o título da série: ");
					titulo= scanner.nextLine();
                    exibirDetalhesEGerenciarFavorito(titulo, "S", emailUsuario);
					break;
                case "5":
                    TelaFavorito telaFavorito = new TelaFavorito(usuarioService);
                    telaFavorito.exibirLista(emailUsuario);
                    break;
				case "0":
					System.out.println("Voltando ao menu principal...");
					break;
				default:
					System.out.println("Opção inválida!");
			}
		} while (!"0".equals(opcao));
	}
    private void exibirDetalhesEGerenciarFavorito(String titulo, String tipo, String emailUsuario) {
        Scanner scanner = new Scanner(System.in);

        try {
            Midia midia;
            if (tipo.equals("F")) {
                midia = midiaService.buscarFilmePorTitulo(titulo);

                Filme filme = (Filme) midia;
                System.out.println("\n=== DETALHES DO FILME ===");
                System.out.println("ID: " + filme.getId());
                System.out.println("Título: " + filme.getTitulo());
                System.out.println("Gênero: " + filme.getGenero());
                System.out.println("Ano: " + filme.getAno());
                System.out.println("Duração: " + filme.getDuracao() + " minutos");
                System.out.println("Relevância: " + filme.getRelevancia());
                System.out.println("Sinopse: " + filme.getSinopse());
                System.out.println("Trailer: " + filme.getTrailer());

            } else if (tipo.equals("S")) {
                midia = midiaService.buscarSeriePorTitulo(titulo);

                Serie serie = (Serie) midia;
                System.out.println("\n=== DETALHES DA SÉRIE ===");
                System.out.println("ID: " + serie.getId());
                System.out.println("Título: " + serie.getTitulo());
                System.out.println("Gênero: " + serie.getGenero());
                System.out.println("Ano: " + serie.getAno());
                System.out.println("Duração: " + serie.getDuracao() + " minutos por episódio");
                System.out.println("Relevância: " + serie.getRelevancia());
                System.out.println("Sinopse: " + serie.getSinopse());
                System.out.println("Trailer: " + serie.getTrailer());

            } else {
                return;
            }

            System.out.println("\n-------------------------------------------");
            System.out.println("1. Adicionar/Remover dos Favoritos");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            String escolha = scanner.nextLine();

            if (escolha.equals("1")) {
                adicionarOuRemover(emailUsuario, midia.getId());
            }

        } catch (IllegalArgumentException e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }

    private void adicionarOuRemover(String emailUsuarioLogado, Long midiaId) {
        try {
            usuarioService.adicionarOuRemoverFavorito(emailUsuarioLogado, midiaId);
            System.out.println("Status: Favoritos atualizados para a Mídia ID " + midiaId + ".");
        } catch (IllegalArgumentException e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }

    private void listarMidasEImprimir(List<? extends Midia> midias) {
        if (midias.isEmpty()) {
            System.out.println("Nenhuma mídia cadastrada.");
            return;
        }


        if (midias.get(0) instanceof Filme) {
            System.out.println("\n=== FILMES DISPONÍVEIS ===");
            for (Midia midia : midias) {
                Filme filme = (Filme) midia;
                System.out.println("- " + filme.getTitulo() + " (ID: " + filme.getId() + ", Ano: " + filme.getAno() + ")");
            }

        } else if (midias.get(0) instanceof Serie) {
            System.out.println("\n=== SÉRIES DISPONÍVEIS ===");
            for (Midia midia : midias) {
                Serie serie = (Serie) midia;
                System.out.println("- " + serie.getTitulo() + " (ID: " + serie.getId() + ", Ano: " + serie.getAno() + ")");
            }
        }
    }
}