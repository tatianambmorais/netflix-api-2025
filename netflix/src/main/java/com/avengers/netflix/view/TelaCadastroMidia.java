package com.avengers.netflix.view;

import com.avengers.netflix.service.MidiaService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class TelaCadastroMidia {

    private final Scanner scanner = new Scanner(System.in);
    private final MidiaService midiaService;

    public TelaCadastroMidia(MidiaService midiaService) {
        this.midiaService = midiaService;
    }

    public void cadastrar() {
        System.out.println("=== Cadastro de Mídia ===");

        System.out.print("Título: ");
        String titulo = scanner.nextLine().trim();

        System.out.print("Gênero: ");
        String genero = scanner.nextLine().trim();

        System.out.print("Relevância: ");
        String relevancia = scanner.nextLine().trim();

        System.out.print("Sinopse: ");
        String sinopse = scanner.nextLine().trim();

        System.out.print("Duração (em minutos): ");
        int duracao = Integer.parseInt(scanner.nextLine());

        System.out.print("Ano de lançamento: ");
        int ano = Integer.parseInt(scanner.nextLine());

        System.out.print("Trailer (URL): ");
        String trailer = scanner.nextLine().trim();

        System.out.print("Tipo (F para Filme / S para Série): ");
        String tipo = scanner.nextLine().trim().toUpperCase();

        // Chama o service para salvar a mídia
        midiaService.cadastrarMidia(tipo, titulo, genero, relevancia, sinopse, duracao, ano, trailer);

        System.out.println("Mídia cadastrada com sucesso!");
    }
}
