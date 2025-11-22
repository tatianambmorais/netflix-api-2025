//package com.avengers.netflix.view;
//
//
//import com.avengers.netflix.service.UsuarioService;
//import org.springframework.stereotype.Component;
//
//import java.util.Scanner;
//import java.time.LocalDate;
//
//@Component
//public class TelaCadastro {
//    private final Scanner scanner = new Scanner(System.in);
//    private final UsuarioService usuarioService;
//
//    public TelaCadastro(UsuarioService usuarioService) {
//        this.usuarioService = usuarioService;
//    }
//
//    public void mostrar(){
//        System.out.println("Que tipo de usuário deseja cadastrar? 1 - Administrador/2 - Cliente   ");
//        String tipoUsuario = scanner.nextLine().trim();
//        if (tipoUsuario.equals("1")) {
//            cadastrarAdm();
//
//        }
//        if (tipoUsuario.equals("2")) {
//            cadastrarCliente();
//        }
////        else {
////            System.out.println("Tipo de usuário é obrigatório");
////        }
//
//    }
//
//    private void cadastrarAdm() {
//        System.out.println("=== Cadastro Backendflix ===");
//        System.out.print("Nome completo: ");
//        String nome = scanner.nextLine().trim();
//        System.out.print("Data de nascimento (YYYY-MM-DD): ");
//        LocalDate dataNascimento= LocalDate.parse(scanner.nextLine());
//        System.out.print("E-mail: ");
//        String email = scanner.nextLine().trim();
//        System.out.print("Senha: ");
//        String senha=scanner.nextLine().trim();
//        System.out.print("Confirmar senha: ");
//        String confirmaSenha=scanner.nextLine().trim();
//        System.out.print("CPF/CNPJ: ");
//        String cpfCnpj = scanner.nextLine().trim();
//        usuarioService.cadastraAdm(nome, dataNascimento,email, senha,confirmaSenha, cpfCnpj);
//        System.out.println("Cadastro realizado. Verifique o e-mail (simulado).");
//
//    }
//
//    private void cadastrarCliente() {
//        System.out.println("=== Cadastro Backendflix ===");
//        System.out.print("Nome completo: ");
//        String nome = scanner.nextLine().trim();
//        System.out.print("Data de nascimento (YYYY-MM-DD): ");
//        LocalDate dataNascimento= LocalDate.parse(scanner.nextLine());
//        System.out.print("E-mail: ");
//        String email = scanner.nextLine().trim();
//        System.out.print("Senha: ");
//        String senha=scanner.nextLine().trim();
//        System.out.print("Confirmar senha: ");
//        String confirmaSenha=scanner.nextLine().trim();
//        System.out.print("Número do cartão: ");
//        String numeroCartao = scanner.nextLine().trim();
//        System.out.print("Validade do cartão (MM/AA): ");
//        String validadeCartao = scanner.nextLine().trim();
//        System.out.print("Código de segurança: ");
//        String codSeguranca = scanner.nextLine().trim();
//        System.out.print("Nome do titular: ");
//        String nomeTitular = scanner.nextLine().trim();
//        System.out.print("CPF/CNPJ: ");
//        String cpfCnpj = scanner.nextLine().trim();
//        usuarioService.cadastraUsuario(nome, dataNascimento,email, senha,confirmaSenha, numeroCartao, validadeCartao, codSeguranca, nomeTitular, cpfCnpj);
//
//        System.out.println("Cadastro realizado. Verifique o e-mail (simulado).");
//    }
//}
