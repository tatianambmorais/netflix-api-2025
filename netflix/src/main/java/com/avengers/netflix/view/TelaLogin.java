package com.avengers.netflix.view;

import com.avengers.netflix.model.Usuario;
import com.avengers.netflix.utils.CriptografiaUtils;

import java.util.Map;
import java.util.Scanner;

public class TelaLogin {
    private final Map<String, Usuario> repositorio;
    private final Scanner scanner=new Scanner(System.in);

    public TelaLogin(Map<String,Usuario> repositorio){
        this.repositorio=repositorio;
    }

    public void mostrar(){
        System.out.println("=== Login Backendflix ===");
        System.out.print("E-mail: ");
        String email=scanner.nextLine().trim();
        System.out.print("Senha: ");
        String senha=scanner.nextLine().trim();
        Usuario u=repositorio.get(email);
        if(u==null){
            System.out.println("Credenciais inválidas.");
            return;
        }
        if(!u.isConfirmado()){
            System.out.println("Conta não confirmada. Verifique seu e-mail.");
            return;
        }
        String hash= CriptografiaUtils.sha256(senha);
        if(!hash.equals(u.getSenhaHash())){
            System.out.println("Credenciais inválidas.");
            return;
        }
        System.out.println("Login realizado. Bem-vindo, " + u.getNomeCompleto());
    }
}
