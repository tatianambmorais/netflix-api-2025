package com.avengers.netflix;

import com.avengers.netflix.model.Usuario;
import com.avengers.netflix.repository.UsuarioRepository;
import com.avengers.netflix.service.UsuarioService;
import com.avengers.netflix.view.TelaCadastro;
import com.avengers.netflix.view.TelaConfirmacaoToken;
import com.avengers.netflix.view.TelaLogin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
public class NetflixApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(NetflixApplication.class, args);

		TelaCadastro telaCadastro = context.getBean(TelaCadastro.class);
		TelaLogin telaLogin = context.getBean(TelaLogin.class);
		TelaConfirmacaoToken telaToken = context.getBean(TelaConfirmacaoToken.class);
		Scanner scanner=new Scanner(System.in);
		while(true){
			System.out.println("=== Backendflix ===");
			System.out.println("1 - Cadastrar");
			System.out.println("2 - Confirmar conta");
			System.out.println("3 - Login");
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
				telaLogin.mostrar();
			} else if("0".equals(op)){
				System.out.println("Saindo...");
				break;
			} else {
				System.out.println("Opção inválida.");
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
