package com.avengers.netflix;

import com.avengers.netflix.model.Usuario;
import com.avengers.netflix.view.TelaCadastro;
import com.avengers.netflix.view.TelaLogin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
public class NetflixApplication {

	public static void main(String[] args) {

		SpringApplication.run(NetflixApplication.class, args);
		Map<String,Usuario> repositorio=new ConcurrentHashMap<>();
		Map<String,String> tokens=new ConcurrentHashMap<>();
		TelaCadastro telaCadastro=new TelaCadastro(repositorio,tokens);
		TelaLogin telaLogin=new TelaLogin(repositorio);
		Scanner scanner=new Scanner(System.in);
		while(true){
			System.out.println("=== Backendflix ===");
			System.out.println("1 - Cadastrar");
			System.out.println("2 - Confirmar conta (cole token)");
			System.out.println("3 - Login");
			System.out.println("0 - Sair");
			System.out.print("Opção: ");
			String op=scanner.nextLine().trim();
			if("1".equals(op)){
				telaCadastro.mostrar();
			} else if("2".equals(op)){
				System.out.print("Token: ");
				String token=scanner.nextLine().trim();
				String email=tokens.remove(token);
				if(email==null){
					System.out.println("Token inválido.");
				} else {
					Usuario u=repositorio.get(email);
					if(u!=null){
						u.setConfirmado(true);
						System.out.println("Conta confirmada para " + email);
					} else {
						System.out.println("Usuário não encontrado.");
					}
				}
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

}
