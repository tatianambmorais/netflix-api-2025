package com.avengers.netflix.controller;

import com.avengers.netflix.service.FavoritoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favoritos")
@CrossOrigin(origins = "*")
public class FavoritoController {

    private final FavoritoService favoritoService;

    public FavoritoController(FavoritoService favoritoService) {
        this.favoritoService = favoritoService;
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> listar(@PathVariable String email) {
        return ResponseEntity.ok(favoritoService.listarFavoritosSeparados(email));
    }

    @PostMapping("/{email}/alternar")
    public ResponseEntity<?> alternar(
            @PathVariable String email,
            @RequestParam Long midiaId
    ) {
        favoritoService.alternarFavorito(email, midiaId);
        return ResponseEntity.ok("Favorito atualizado com sucesso!");
    }
}
