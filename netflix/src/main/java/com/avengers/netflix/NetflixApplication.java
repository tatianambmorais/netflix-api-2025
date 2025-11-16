package com.avengers.netflix;

import com.avengers.netflix.model.TipoUsuario;
import com.avengers.netflix.model.Usuario;
import com.avengers.netflix.repository.UsuarioRepository;
import com.avengers.netflix.service.UsuarioService;
import com.avengers.netflix.view.TelaCadastro;
import com.avengers.netflix.view.TelaConfirmacaoToken;
import com.avengers.netflix.view.TelaLogin;
import com.avengers.netflix.view.TelaCadastroMidia;
import com.avengers.netflix.view.TelaAtualizaCartao;
import com.avengers.netflix.view.TelaVisualizacaoMidia;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
public class NetflixApplication {

	private static Usuario usuarioLogado = null;

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(NetflixApplication.class, args);

		TelaCadastro telaCadastro = context.getBean(TelaCadastro.class);
		TelaLogin telaLogin = context.getBean(TelaLogin.class);
		TelaConfirmacaoToken telaToken = context.getBean(TelaConfirmacaoToken.class);
		TelaCadastroMidia telaMidia = context.getBean(TelaCadastroMidia.class);
		TelaAtualizaCartao telaAtualizaCartao = context.getBean(TelaAtualizaCartao.class);
		TelaVisualizacaoMidia telaVisualizacao = context.getBean(TelaVisualizacaoMidia.class);
        Scanner scanner=new Scanner(System.in);
		while(true){
			System.out.println("=== Backendflix ===");
			if (usuarioLogado == null) {
				System.out.println("1 - Cadastrar");
				System.out.println("2 - Confirmar conta");
				System.out.println("3 - Login");
			}
			if(usuarioLogado != null) {
				System.out.println("Logado como: " + usuarioLogado.getNomeCompleto());
				System.out.println("4 - Visualizar Mídia");
				if (usuarioLogado.getTipoUsuario().equals(TipoUsuario.CLIENTE)) {
					System.out.println("5 - Atualizar Cartão");
					System.out.println("6 - Logout");
				}
				if (usuarioLogado.getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR)){
					System.out.println("5 - Cadastrar Mídia");
					System.out.println("6 - Logout");
				}
			}
			System.out.println("0 - Sair");
			System.out.print("Opção: ");
			String op=scanner.nextLine().trim();
			if("1".equals(op)){
				telaCadastro.mostrar();
				cadastraToken(scanner, telaToken);

			} else if("2".equals(op)){
				cadastraToken(scanner, telaToken);
				telaLogin.mostrar();

			} else if("3".equals(op)){
				usuarioLogado = telaLogin.mostrar();

			} else if("4".equals(op) && usuarioLogado != null){
				telaVisualizacao.exibirMenu();

			} else if("5".equals(op) && usuarioLogado != null) {
				if (usuarioLogado.getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR)) {
					telaMidia.cadastrar();
				}
				if (usuarioLogado.getTipoUsuario().equals(TipoUsuario.CLIENTE)) {
					telaAtualizaCartao.mostrar(usuarioLogado);
				}

			} else if("6".equals(op) && usuarioLogado != null){
				usuarioLogado = null;
				System.out.println("Logout realizado com sucesso!");

			} else if("0".equals(op)){
				System.out.println("Saindo...");
				break;
			} else {
				System.out.println("Opção inválida ou acesso negado.");
			}
		}
	}

	private static void cadastraToken(Scanner scanner, TelaConfirmacaoToken telaToken) {
		System.out.print("Digite seu email: ");
		String email= scanner.nextLine().trim();
		System.out.print("Digite seu token: ");
		String token= scanner.nextLine().trim();
		telaToken.confirmaToken(token, email);
	}

}
