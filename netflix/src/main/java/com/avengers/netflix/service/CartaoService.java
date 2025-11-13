package com.avengers.netflix.service;

import com.avengers.netflix.model.Cartao;
import com.avengers.netflix.model.Usuario;
import com.avengers.netflix.repository.CartaoRepository;
import com.avengers.netflix.utils.CriptografiaUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaoService {

    private final CartaoRepository cartaoRepository;

    public CartaoService(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    public List<Cartao> listarCartoesPorUsuario(Usuario usuario) {
        return cartaoRepository.findByUsuario(usuario);
    }

    public void atualizarCartao(Cartao cartao, String novoNumero, String novoNome, String novaValidade, String novoCvv) {
        cartao.setNumero(CriptografiaUtils.sha256(novoNumero));
        cartao.setNomeImpresso(novoNome);
        cartao.setValidade(novaValidade);
        cartao.setCvv(CriptografiaUtils.sha256(novoCvv));
        cartaoRepository.save(cartao);
    }

    public boolean pertenceAoUsuario(Cartao cartao, Usuario usuario) {
        return cartao.getUsuario().getId().equals(usuario.getId());
    }
}