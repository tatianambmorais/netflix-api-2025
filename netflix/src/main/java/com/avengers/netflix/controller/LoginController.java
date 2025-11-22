package com.avengers.netflix.controller;


import com.avengers.netflix.model.Usuario;
import com.avengers.netflix.model.dto.LoginRequestDTO;
import com.avengers.netflix.model.dto.LoginResponseDTO;
import com.avengers.netflix.service.LoginService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {

        Usuario u = loginService.autenticar(request.email(), request.senha());

        return new LoginResponseDTO(
                "ok",
                u.getNomeCompleto(),
                u.getEmail(),
                u.getTipoUsuario().name()
        );
    }
}
