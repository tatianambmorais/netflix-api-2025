package com.avengers.netflix.service;

import com.avengers.netflix.model.Cartao;
import com.avengers.netflix.model.Usuario;
import com.avengers.netflix.repository.CartaoRepository;
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


    public void atualizarCartao(
            Usuario logado,
            Cartao cartao,
            String numero,
            String nome,
            String validade,
            String cvv
    ) {
        if (!pertenceAoUsuario(cartao, logado)) {
            throw new RuntimeException("Acesso negado!");
        }

        cartao.setNumero(numero);
        cartao.setNomeImpresso(nome);
        cartao.setValidade(validade);
        cartao.setCvv(cvv);

        cartaoRepository.save(cartao);
    }

    public boolean pertenceAoUsuario(Cartao cartao, Usuario usuario) {
        return cartao.getUsuario().getId().equals(usuario.getId());
    }

    public void cadastrarCartao(Usuario usuario, String numero, String nomeImpresso, String validade, String cvv) {
        Cartao cartao = new Cartao();
        cartao.setNumero(numero);
        cartao.setNomeImpresso(nomeImpresso);
        cartao.setValidade(validade);
        cartao.setCvv(cvv);
        cartao.setUsuario(usuario);
        cartaoRepository.save(cartao);
    }

    public Cartao buscarPorId(Long idCartao) {
       return  cartaoRepository.findById(idCartao).orElseThrow();
    }
}
