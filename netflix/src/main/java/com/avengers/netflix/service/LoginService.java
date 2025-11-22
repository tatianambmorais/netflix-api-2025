package com.avengers.netflix.service;


import com.avengers.netflix.model.Usuario;
import com.avengers.netflix.repository.UsuarioRepository;
import com.avengers.netflix.utils.CriptografiaUtils;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UsuarioRepository usuarioRepository;

    public LoginService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario autenticar(String email, String senha) {

        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) {
            throw new IllegalArgumentException("Credenciais inválidas.");
        }

        if (!usuario.isConfirmado()) {
            throw new IllegalArgumentException("Conta não confirmada. Verifique seu e-mail.");
        }

        String hash = CriptografiaUtils.sha256(senha);
        if (!hash.equals(usuario.getSenhaHash())) {
            throw new IllegalArgumentException("Credenciais inválidas.");
        }

        return usuario;
    }
}
