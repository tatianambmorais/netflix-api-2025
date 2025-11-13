package com.avengers.netflix.service;

import com.avengers.netflix.model.Cartao;
import com.avengers.netflix.model.Usuario;
import com.avengers.netflix.repository.CartaoRepository;
import com.avengers.netflix.repository.UsuarioRepository;
import com.avengers.netflix.utils.CriptografiaUtils;
import com.avengers.netflix.utils.TokenUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;
    private final CartaoRepository cartaoRepository;


    public UsuarioService(UsuarioRepository usuarioRepository, EmailService emailService, CartaoRepository cartaoRepository){
        this.usuarioRepository = usuarioRepository;
        this.emailService = emailService;
        this.cartaoRepository = cartaoRepository;
    }

    public void cadastraUsuario(String nome, LocalDate dataNascimento, String email, String senha,
                                String confirmaSenha, String numeroCartao, String validadeCartao,
                                String codSeguranca, String nomeTitular, String cpfCnpj){

        verificaSeJaExiste(email);

        String senhaCriptografada = confirmaSenha(senha, confirmaSenha);

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
        usuario.setSenhaHash(senhaCriptografada);
        usuario.setToken(TokenUtils.generateToken());

        usuarioRepository.save(usuario);

        Cartao cartao = new Cartao();
        cartao.setNumero(CriptografiaUtils.sha256(numeroCartao));
        cartao.setNomeImpresso(nomeTitular);
        cartao.setValidade(validadeCartao);
        cartao.setCvv(CriptografiaUtils.sha256(codSeguranca));
        cartao.setUsuario(usuario);

        cartaoRepository.save(cartao);

        emailService.enviar(usuario);
    }

    private void verificaSeJaExiste(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario != null) {
            throw new IllegalArgumentException("Usuária(o) já cadastrada(o) com este e-mail.");
        }
    }

    private String confirmaSenha(String senha, String confirmaSenha) {
        if (!senha.equals(confirmaSenha)) {
            throw new IllegalArgumentException("As senhas não conferem.");
        }
        return criptografaSenha(senha);
    }

    private String criptografaSenha(String senha) {
        return CriptografiaUtils.sha256(senha);
    }

    public void confirmaToken(String token, String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) {
            throw new IllegalArgumentException("E-mail não encontrado.");
        }

        if (!token.equals(usuario.getToken())) {
            throw new IllegalArgumentException("Token incorreto. Tente novamente.");
        }

        usuario.setConfirmado(true);
        usuarioRepository.save(usuario);

        System.out.println("Usuário confirmado. Pode proceder para o login");
    }}
