package com.avengers.netflix.controller;

import com.avengers.netflix.model.dto.CadastroUsuarioDTO;
import com.avengers.netflix.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@RequestBody CadastroUsuarioDTO dados) {

        if (dados.tipo().equalsIgnoreCase("CLIENTE")) {

            usuarioService.cadastraUsuario(
                    dados.nome(),
                    dados.dataNascimento(),
                    dados.email(),
                    dados.senha(),
                    dados.confirmaSenha(),
                    dados.numeroCartao(),
                    dados.validadeCartao(),
                    dados.codSeguranca(),
                    dados.nomeTitular(),
                    dados.cpfCnpj()
            );

            return "Cliente cadastrado com sucesso! Confirme seu e-mail.";

        } else if (dados.tipo().equalsIgnoreCase("ADMIN")) {

            usuarioService.cadastraAdm(
                    dados.nome(),
                    dados.dataNascimento(),
                    dados.email(),
                    dados.senha(),
                    dados.confirmaSenha(),
                    dados.cpfCnpj()
            );

            return "Administrador cadastrado com sucesso! Confirme seu e-mail.";

        } else {
            throw new IllegalArgumentException("Tipo de usuário inválido: use CLIENTE ou ADMIN.");
        }
    }

    @PostMapping("/confirmar")
    public String confirmarToken(@RequestBody Map<String, String> dados) {
        usuarioService.confirmaToken(dados.get("token"), dados.get("email"));
        return "Conta confirmada com sucesso!";
    }
}
