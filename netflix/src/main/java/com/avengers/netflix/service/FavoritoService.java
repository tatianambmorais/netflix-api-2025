package com.avengers.netflix.service;

import com.avengers.netflix.model.Midia;
import com.avengers.netflix.model.TipoUsuario;
import com.avengers.netflix.model.Usuario;
import com.avengers.netflix.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class FavoritoService {

    private final UsuarioRepository usuarioRepository;
    private final MidiaService midiaService;

    public FavoritoService(UsuarioRepository usuarioRepository, MidiaService midiaService) {
        this.usuarioRepository = usuarioRepository;
        this.midiaService = midiaService;
    }

    public Map<String, List<Midia>> listarFavoritosSeparados(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        Map<String, List<Midia>> favoritos = new HashMap<>();

        favoritos.put("filmes", usuario.getFavoritos().stream()
                .filter(m -> m.getClass().getSimpleName().equals("Filme"))
                .collect(Collectors.toList()));

        favoritos.put("series", usuario.getFavoritos().stream()
                .filter(m -> m.getClass().getSimpleName().equals("Serie"))
                .collect(Collectors.toList()));

        return favoritos;
    }

    @Transactional
    public Usuario alternarFavorito(String email, Long midiaId) {

        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        if (usuario.getTipoUsuario() != TipoUsuario.CLIENTE) {
            throw new IllegalArgumentException("Apenas CLIENTES podem favoritar mídias.");
        }

        Midia midia = midiaService.buscarPorId(midiaId);

        if (usuario.getFavoritos().contains(midia)) {
            usuario.getFavoritos().remove(midia);
        } else {
            usuario.getFavoritos().add(midia);
        }

        return usuarioRepository.save(usuario);
    }
}
