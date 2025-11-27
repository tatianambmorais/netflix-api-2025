package com.avengers.netflix.controller;

import com.avengers.netflix.model.Assinatura;
import com.avengers.netflix.model.dto.AssinaturaDTO;
import com.avengers.netflix.service.AssinaturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/assinaturas")
public class AssinaturaController {

    private final AssinaturaService assinaturaService;

    public AssinaturaController(AssinaturaService assinaturaService) {
        this.assinaturaService = assinaturaService;
    }

    @PostMapping
    public ResponseEntity<Assinatura> cadastrarAssinatura(@RequestBody AssinaturaDTO assinaturaDTO) {
        assinaturaService.cadastrar(assinaturaDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<AssinaturaDTO>> recuperaAssiantura() {
        List<AssinaturaDTO> assinaturas = assinaturaService.listarAssinaturas();
        return ResponseEntity.ok(assinaturas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssinaturaDTO> obterAssinaturaPorId(@PathVariable Long id) {
        AssinaturaDTO assinaturaDTO = assinaturaService.obterAssinaturaPorId(id);
        return ResponseEntity.ok(assinaturaDTO);
    }
}
