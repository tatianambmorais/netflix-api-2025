package com.avengers.netflix.controller;

import com.avengers.netflix.model.Filme;
import com.avengers.netflix.model.Midia;
import com.avengers.netflix.model.Serie;
import com.avengers.netflix.model.dto.MidiaDTO;
import com.avengers.netflix.repository.MidiaRepository;
import com.avengers.netflix.service.MidiaService;
import com.avengers.netflix.utils.ConteudoSpec;
import org.springframework.data.jpa.domain.Specification;
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

    @GetMapping("/ordenadas")
    public ResponseEntity<List<MidiaDTO>> listarOrdenadas() {
        return ResponseEntity.ok(midiaService.listarTodasOrdenadas());}


    @GetMapping("/filtro-genero")
    public ResponseEntity<List<MidiaDTO>> buscarPorGenero(@RequestParam String genero) {
        return ResponseEntity.ok(midiaService.buscarPorGenero(genero));
    }

    @GetMapping("/top-trends")
    public ResponseEntity<List<MidiaDTO>> buscarTopTrends(@RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(midiaService.buscarTopRelevantes(limit));
    }

    @GetMapping("/lancamentos")
    public ResponseEntity<List<MidiaDTO>> buscarLancamentos(@RequestParam int ano) {
        return ResponseEntity.ok(midiaService.buscarLancamentosRecentes(ano));
    }

    @GetMapping("/com-trailers")
    public ResponseEntity<List<MidiaDTO>> buscarComTrailer() {
        return ResponseEntity.ok(midiaService.buscarComTrailer());
    }

    @GetMapping("/busca")
    public ResponseEntity<List<MidiaDTO>> buscarPorTermo(@RequestParam String termo) {
        return ResponseEntity.ok(midiaService.buscarPorTermo(termo));
    }
}
