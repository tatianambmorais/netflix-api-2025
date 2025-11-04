package com.avengers.netflix.view;
import com.avengers.netflix.model.Usuario;
import com.avengers.netflix.service.EmailService;
import com.avengers.netflix.utils.CriptografiaUtils;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.UUID;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TelaCadastro {
    private final Scanner scanner=new Scanner(System.in);
    private final EmailService emailService=new EmailService();
    private final Map<String, Usuario> repositorio;
    private final Map<String,String> tokens;

    public TelaCadastro(Map<String,Usuario> repositorio, Map<String,String> tokens){
        this.repositorio=repositorio;
        this.tokens=tokens;
    }

    public void mostrar(){
        System.out.println("=== Cadastro Backendflix ===");
        Usuario u=new Usuario();
        System.out.print("Nome completo: ");
        u.setNomeCompleto(scanner.nextLine().trim());
        System.out.print("Data de nascimento (YYYY-MM-DD): ");
        String data=scanner.nextLine().trim();
        u.setDataNascimento(LocalDate.parse(data));
        System.out.print("E-mail: ");
        u.setEmail(scanner.nextLine().trim());
        System.out.print("Senha: ");
        String senha=scanner.nextLine().trim();
        System.out.print("Confirmar senha: ");
        String confirma=scanner.nextLine().trim();
        if(!senha.equals(confirma)){
            System.out.println("Senhas não conferem.");
            return;
        }
        u.setSenhaHash(CriptografiaUtils.sha256(senha));
        System.out.print("Número do cartão: ");
        u.setNumeroCartao(scanner.nextLine().trim());
        System.out.print("Validade do cartão (MM/AA): ");
        u.setValidadeCartao(scanner.nextLine().trim());
        System.out.print("Código de segurança: ");
        u.setCodigoSeguranca(scanner.nextLine().trim());
        System.out.print("Nome do titular: ");
        u.setNomeTitular(scanner.nextLine().trim());
        System.out.print("CPF/CNPJ: ");
        u.setCpfCnpj(scanner.nextLine().trim());
        u.setConfirmado(false);
        if(repositorio.containsKey(u.getEmail())){
            System.out.println("E-mail já cadastrado.");
            return;
        }
        repositorio.put(u.getEmail(), u);
        String token=UUID.randomUUID().toString();
        tokens.put(token, u.getEmail());
        emailService.enviarConfirmacao(u.getEmail(), token);
        System.out.println("Cadastro realizado. Verifique o e-mail (simulado).");
    }
}
