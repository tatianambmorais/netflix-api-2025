package com.avengers.netflix.view;

import com.avengers.netflix.model.Cartao;
import com.avengers.netflix.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class TelaAtualizaCartao {

    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    private CartaoRepository cartaoRepository;

    public void mostrar() {
        System.out.println("=== Atualização de Cartão ===");
        System.out.print("Digite o ID do cartão: ");
        Long id = Long.parseLong(scanner.nextLine().trim());

        Optional<Cartao> optCartao = cartaoRepository.findById(id);
        if (optCartao.isEmpty()) {
            System.out.println("Cartão não encontrado!");
            return;
        }

        Cartao cartao = optCartao.get();

        System.out.println("Cartão encontrado: " + cartao.getNomeImpresso());
        System.out.println("--- Atualize os dados abaixo ---");

        System.out.print("Novo nome impresso: ");
        cartao.setNomeImpresso(scanner.nextLine().trim());

        System.out.print("Novo número: ");
        cartao.setNumero(scanner.nextLine().trim());

        System.out.print("Nova validade (MM/AA): ");
        cartao.setValidade(scanner.nextLine().trim());

        System.out.print("Novo CVV: ");
        cartao.setCvv(scanner.nextLine().trim());

        cartaoRepository.save(cartao);

        System.out.println("Cartão atualizado com sucesso!");
    }
}
