//package com.avengers.netflix.view;
//
//import com.avengers.netflix.service.UsuarioService;
//import com.avengers.netflix.model.Midia;
//import com.avengers.netflix.model.Filme;
//import com.avengers.netflix.model.Serie;
//
//import java.util.List;
//import java.util.Map;
//
//public class TelaFavorito {
//
//    private final UsuarioService usuarioService;
//
//    public TelaFavorito(UsuarioService usuarioService) {
//        this.usuarioService = usuarioService;
//    }
//
//
//    public void exibirLista(String emailUsuario) {
//        System.out.println("\n==========================================");
//        System.out.println("        ⭐  LISTA DE FAVORITOS ⭐");
//        System.out.println("==========================================");
//        try {
//            Map<String, List<Midia>> favorito = usuarioService.listarFavoritosSeparados(emailUsuario);
//
//            List<?> filmes = favorito.getOrDefault("filmes", List.of());
//            System.out.println("\n FILMES FAVORITOS (" + filmes.size() + "):");
//            if (filmes.isEmpty()) {
//                System.out.println("   Nenhum filme favorito encontrado.");
//            } else {
//                for (Object midia : filmes) {
//                    Filme filme = (Filme) midia;
//                    System.out.println("   - ID: " + filme.getId() + " | " + filme.getTitulo() + " (" + filme.getAno() + ")");
//                }
//            }
//
//
//            List<?> series = favorito.getOrDefault("series", List.of());
//            System.out.println("\nSÉRIES FAVORITAS (" + series.size() + "):");
//            if (series.isEmpty()) {
//                System.out.println("   Nenhuma série favorita encontrada.");
//            } else {
//                for (Object midia : series) {
//                    Serie serie = (Serie) midia;
//                    System.out.println("   - ID: " + serie.getId() + " | " + serie.getTitulo() + " (" + serie.getAno() + ")");
//                }
//            }
//
//        } catch (IllegalArgumentException e) {
//            System.err.println("ERRO: " + e.getMessage());
//        }
//        System.out.println("==========================================");
//    }
//}