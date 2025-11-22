package com.avengers.netflix.controller;

import com.avengers.netflix.model.Filme;
import com.avengers.netflix.model.Midia;
import com.avengers.netflix.model.Serie;
import com.avengers.netflix.model.dto.MidiaDTO;
import com.avengers.netflix.service.MidiaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/midias")
public class MidiaController {

    private final MidiaService midiaService;

    public MidiaController(MidiaService midiaService) {
        this.midiaService = midiaService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody MidiaDTO dto) {
        midiaService.cadastrarMidia(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filmes")
    public List<Filme> listarFilmes() {
        return midiaService.listarFilmes();
    }

    @GetMapping("/series")
    public List<Serie> listarSeries() {
        return midiaService.listarSeries();
    }

    @GetMapping("/{tipo}/{id}")
    public Object detalhes(@PathVariable String tipo, @PathVariable Long id) {
        return midiaService.buscarPorId(id);
    }

}
