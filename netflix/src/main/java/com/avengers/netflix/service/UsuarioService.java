package com.avengers.netflix.service;

import com.avengers.netflix.model.*;
import com.avengers.netflix.model.Cartao;
import com.avengers.netflix.model.TipoUsuario;
import com.avengers.netflix.model.Usuario;
import com.avengers.netflix.repository.CartaoRepository;
import com.avengers.netflix.repository.UsuarioRepository;
import com.avengers.netflix.utils.CriptografiaUtils;
import com.avengers.netflix.utils.TokenUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;
    private final CartaoRepository cartaoRepository;
    private final MidiaService midiaService;


    public UsuarioService(UsuarioRepository usuarioRepository, EmailService emailService, CartaoRepository cartaoRepository, MidiaService midiaService){
        this.usuarioRepository = usuarioRepository;
        this.emailService = emailService;
        this.cartaoRepository = cartaoRepository;
        this.midiaService = midiaService;
    }

    public void cadastraUsuario(String nome, LocalDate dataNascimento, String email, String senha,
                                String confirmaSenha, String numeroCartao, String validadeCartao,
                                String codSeguranca, String nomeTitular, String cpfCnpj){

        verificaSeJaExiste(email);

        String senhaCriptografada = confirmaSenha(senha, confirmaSenha);

        Usuario usuario = new Usuario();
        cadastraUsuarioDadosComuns(nome, dataNascimento, email, cpfCnpj, usuario, senhaCriptografada);
        usuario.setNumeroCartao(numeroCartao);
        usuario.setValidadeCartao(validadeCartao);
        usuario.setCodigoSeguranca(codSeguranca);
        usuario.setNomeTitular(nomeTitular);
        usuario.setTipoUsuario(TipoUsuario.CLIENTE);

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

    public void cadastraAdm(String nome, LocalDate dataNascimento, String email, String senha,
                            String confirmaSenha, String cpfCnpj){

        verificaSeJaExiste(email);

        String senhaCriptografada = confirmaSenha(senha, confirmaSenha);

        Usuario usuario = new Usuario();
        cadastraUsuarioDadosComuns(nome, dataNascimento, email, cpfCnpj, usuario, senhaCriptografada);
        usuario.setTipoUsuario(TipoUsuario.ADMINISTRADOR);

        usuarioRepository.save(usuario);
        emailService.enviar(usuario);
    }

    private static void cadastraUsuarioDadosComuns(String nome, LocalDate dataNascimento, String email, String cpfCnpj, Usuario usuario, String senhaCriptografada) {
        usuario.setNomeCompleto(nome);
        usuario.setDataNascimento(dataNascimento);
        usuario.setEmail(email);
        usuario.setSenhaHash(senhaCriptografada);
        usuario.setCpfCnpj(cpfCnpj);
        usuario.setConfirmado(false);
        usuario.setToken(TokenUtils.generateToken());
        usuario.setSenhaHash(senhaCriptografada);
        usuario.setToken(TokenUtils.generateToken());
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

    public Map<String, List<Midia>> listarFavoritosSeparados(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        Map<String, List<Midia>> favoritosSeparados = new HashMap<>();

        List<Midia> filmes = usuario.getFavoritos().stream()
                .filter(midia -> midia instanceof Filme)
                .collect(Collectors.toList());

        List<Midia> series = usuario.getFavoritos().stream()
                .filter(midia -> midia instanceof Serie)
                .collect(Collectors.toList());

        favoritosSeparados.put("filmes", filmes);
        favoritosSeparados.put("series", series);

        return favoritosSeparados;
    }

    @Transactional
    public Usuario adicionarOuRemoverFavorito(String email, Long midiaId) {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        Midia midia = midiaService.buscarPorId(midiaId);
        if (usuario.getFavoritos().contains(midia)) {
            usuario.getFavoritos().remove(midia);
        } else {
            usuario.getFavoritos().add(midia);
        }

        return usuarioRepository.save(usuario);
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
