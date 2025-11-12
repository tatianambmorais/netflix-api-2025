package com.avengers.netflix.view;

import com.avengers.netflix.model.Cartao;
import com.avengers.netflix.model.Usuario;
import com.avengers.netflix.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/cartoes")

public class TelaCartao{
    @Autowired
    private CartaoRepository cartaoRepository;

    @GetMapping
    public List<Cartao> listar(@AuthenticationPrincipal Usuario usuario) {
        return cartaoRepository.findByUsuario(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cartao> atualizarCartao(@PathVariable Long id,
                                                  @RequestBody Cartao novosDados,
                                                  @AuthenticationPrincipal Usuario usuario) {
        Cartao cartao = cartaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));

        if (!cartao.getUsuario().equals(usuario)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado");
        }

        cartao.setNomeImpresso(novosDados.getNomeImpresso());
        cartao.setNumero(novosDados.getNumero());
        cartao.setValidade(novosDados.getValidade());
        cartao.setCvv(novosDados.getCvv());

        cartaoRepository.save(cartao);
        return ResponseEntity.ok(cartao);
    }
}
