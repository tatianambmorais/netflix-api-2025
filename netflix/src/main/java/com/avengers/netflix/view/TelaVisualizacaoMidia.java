package com.avengers.netflix.view;

import com.avengers.netflix.service.MidiaService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class TelaVisualizacaoMidia {

	private final MidiaService midiaService;

	public TelaVisualizacaoMidia(MidiaService midiaService) {
		this.midiaService = midiaService;
	}

	public void exibirMenu() {
		Scanner scanner = new Scanner(System.in);
		String opcao;

		do {
			System.out.println("\n=== VISUALIZAÇÃO DE MÍDIA ===");
			System.out.println("1 - Listar Filmes");
			System.out.println("2 - Listar Séries");
			System.out.println("3 - Ver Detalhes de Filme");
			System.out.println("4 - Ver Detalhes de Série");
			System.out.println("0 - Voltar");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextLine();

			switch (opcao) {
				case "1":
					midiaService.listarFilmes();
					break;
				case "2":
					midiaService.listarSeries();
					break;
				case "3":
					System.out.print("Digite o título do filme: ");
					String tituloFilme = scanner.nextLine();
					midiaService.exibirDetalhesFilme(tituloFilme);
					break;
				case "4":
					System.out.print("Digite o título da série: ");
					String tituloSerie = scanner.nextLine();
					midiaService.exibirDetalhesSerie(tituloSerie);
					break;
				case "0":
					System.out.println("Voltando ao menu principal...");
					break;
				default:
					System.out.println("Opção inválida!");
			}
		} while (!"0".equals(opcao));
	}
}