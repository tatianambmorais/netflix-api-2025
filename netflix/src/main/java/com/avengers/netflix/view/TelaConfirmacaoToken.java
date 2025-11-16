package com.avengers.netflix.view;

import com.avengers.netflix.service.UsuarioService;
import org.springframework.stereotype.Component;

@Component
public class TelaConfirmacaoToken {

    private final UsuarioService usuarioService;

    public TelaConfirmacaoToken(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void confirmaToken(String token, String email) {
        if(token==null){
            System.out.println("Token inválido.");
        } else {
            usuarioService.confirmaToken(token, email);
        }

    }
}
