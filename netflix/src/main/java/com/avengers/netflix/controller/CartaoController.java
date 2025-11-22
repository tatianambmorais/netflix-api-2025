package com.avengers.netflix.controller;

import com.avengers.netflix.model.Cartao;
import com.avengers.netflix.model.Usuario;
import com.avengers.netflix.model.dto.AtualizaCartaoDTO;
import com.avengers.netflix.service.CartaoService;
import com.avengers.netflix.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/cartoes")
public class CartaoController {

    private final CartaoService cartaoService;
    private final UsuarioService usuarioService;

    public CartaoController(CartaoService cartaoService, UsuarioService usuarioService) {
        this.cartaoService = cartaoService;
        this.usuarioService = usuarioService;
    }

    private Usuario validarCliente(String email) {
        Usuario usuario = usuarioService.buscarPorEmail(email);

        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");
        }

        if (!"CLIENTE".equals(usuario.getTipoUsuario().toString())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas clientes podem gerenciar cartões.");
        }

        return usuario;
    }


    @GetMapping("/{email}")
    public List<Cartao> listar(@PathVariable String email) {
        Usuario cliente = validarCliente(email);
        return cartaoService.listarCartoesPorUsuario(cliente);
    }

    @PutMapping("/{email}/{idCartao}")
    public String atualizar(
            @PathVariable String email,
            @PathVariable Long idCartao,
            @RequestBody AtualizaCartaoDTO dto
    ) {
        Usuario cliente = validarCliente(email);
        Cartao cartao = cartaoService.buscarPorId(idCartao);

        cartaoService.atualizarCartao(cliente, cartao, dto.numero(), dto.nome(), dto.validade(), dto.cvv());

        return "Cartão atualizado com sucesso!";
    }
}

