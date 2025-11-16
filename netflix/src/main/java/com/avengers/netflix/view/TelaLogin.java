package com.avengers.netflix.view;

import com.avengers.netflix.model.Usuario;
import com.avengers.netflix.repository.UsuarioRepository;
import com.avengers.netflix.utils.CriptografiaUtils;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class TelaLogin {
private final UsuarioRepository usuarioRepository;
    private final Scanner scanner=new Scanner(System.in);

    public TelaLogin(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario mostrar(){
        System.out.println("=== Login Backendflix ===");
        System.out.print("E-mail: ");
        String email=scanner.nextLine().trim();
        System.out.print("Senha: ");
        String senha=scanner.nextLine().trim();
        Usuario u=usuarioRepository.findByEmail(email);
        if(u==null){
            System.out.println("Credenciais inválidas.");
            return null;
        }
        if(!u.isConfirmado()){
            System.out.println("Conta não confirmada. Verifique seu e-mail.");
            return null;
        }
        String hash= CriptografiaUtils.sha256(senha);
        if(!hash.equals(u.getSenhaHash())){
            System.out.println("Credenciais inválidas.");
            return null;
        }
        System.out.println("Login realizado. Bem-vindo, " + u.getNomeCompleto());
        return u;
    }
}
