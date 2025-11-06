package com.avengers.netflix.service;

import com.avengers.netflix.model.Usuario;
import com.avengers.netflix.repository.UsuarioRepository;
import com.avengers.netflix.utils.CriptografiaUtils;
import com.avengers.netflix.utils.TokenUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;


    public UsuarioService(UsuarioRepository usuarioRepository, EmailService emailService){
        this.usuarioRepository = usuarioRepository;
        this.emailService = emailService;
    }


    public void cadastraUsuario(String nome, LocalDate dataNascimento, String email, String senha, String confirmaSenha, String numeroCartao, String validadeCartao, String codSeguranca, String nomeTitular, String cpfCnpj){

        verificaSeJaExiste(email);

        Usuario usuario = new Usuario();
        usuario.setNomeCompleto(nome);
        usuario.setDataNascimento(dataNascimento);
        usuario.setEmail(email);
        usuario.setConfirmado(false);
        usuario.setNumeroCartao(numeroCartao);
        usuario.setValidadeCartao(validadeCartao);
        usuario.setCodigoSeguranca(codSeguranca);
        usuario.setNomeTitular(nomeTitular);
        usuario.setCpfCnpj(cpfCnpj);
        usuario.setSenhaHash(confirmaSenha(senha, confirmaSenha));
        usuario.setToken(TokenUtils.generateToken());
        usuarioRepository.save(usuario);
        emailService.enviar(usuario);
    }

    private void verificaSeJaExiste(String email) {
        Usuario u = usuarioRepository.findByEmail(email);
        if (u != null) {
            System.out.println("Usuário já cadastrado");
        }

    }

    private String confirmaSenha(String senha, String confirmaSenha) {
        if(!senha.equals(confirmaSenha)){
            System.out.println("Senhas não conferem.");
        }
        return criptografaSenha(senha);
    }

    private String criptografaSenha(String senha) {
        return CriptografiaUtils.sha256(senha);
    }


    public void confirmaToken(String token, String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (token.equals(usuario.getToken())){
            usuario.setConfirmado(true);
            usuarioRepository.save(usuario);
            System.out.println("Usuário confirmado. Pode proceder para o login");
        }
        else {
            System.out.println("Token incorreto. Tente novamente");
        }
    }
}
