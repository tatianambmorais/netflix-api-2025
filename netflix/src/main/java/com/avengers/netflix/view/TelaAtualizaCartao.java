package com.avengers.netflix.view;

import com.avengers.netflix.model.Cartao;
import com.avengers.netflix.model.Usuario;
import com.avengers.netflix.service.CartaoService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class TelaAtualizaCartao {

    private final Scanner scanner = new Scanner(System.in);
    private final CartaoService cartaoService;

    public TelaAtualizaCartao(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    public void mostrar(Usuario usuarioLogado) {
        if (usuarioLogado == null) {
            System.out.println("Você precisa estar logado para atualizar cartões!");
            return;
        }
        
        System.out.println("=== Atualização de Cartão ===");
        
        List<Cartao> cartoes = cartaoService.listarCartoesPorUsuario(usuarioLogado);
        if (cartoes.isEmpty()) {
            System.out.println("Você não possui cartões cadastrados!");
            return;
        }

        System.out.println("Seus cartões:");
        for (int i = 0; i < cartoes.size(); i++) {
            Cartao c = cartoes.get(i);
            String numeroMascarado = "**** **** **** " + (c.getNumero().length() >= 4 ? c.getNumero().substring(c.getNumero().length() - 4) : "****");
            System.out.println((i + 1) + " - " + c.getNomeImpresso() + " (" + numeroMascarado + ")");
        }

        System.out.print("Escolha o cartão (número): ");
        int escolha = Integer.parseInt(scanner.nextLine().trim()) - 1;
        
        if (escolha < 0 || escolha >= cartoes.size()) {
            System.out.println("Cartão inválido!");
            return;
        }

        Cartao cartao = cartoes.get(escolha);
        
        if (!cartaoService.pertenceAoUsuario(cartao, usuarioLogado)) {
            System.out.println("Acesso negado!");
            return;
        }

        System.out.println("--- Atualize os dados abaixo ---");

        System.out.print("Novo nome impresso: ");
        String novoNome = scanner.nextLine().trim();

        System.out.print("Novo número: ");
        String novoNumero = scanner.nextLine().trim();

        System.out.print("Nova validade (MM/AA): ");
        String novaValidade = scanner.nextLine().trim();

        System.out.print("Novo CVV: ");
        String novoCvv = scanner.nextLine().trim();

        cartaoService.atualizarCartao(cartao, novoNumero, novoNome, novaValidade, novoCvv);
        System.out.println("Cartão atualizado com sucesso!");
    }
}
