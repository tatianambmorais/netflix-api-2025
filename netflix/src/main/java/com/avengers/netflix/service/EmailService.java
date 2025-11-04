package com.avengers.netflix.service;

public class EmailService {
    public void enviarConfirmacao(String para, String token){
        System.out.println("=== EMAIL SIMULADO ===");
        System.out.println("Para: " + para);
        System.out.println("Assunto: Confirme seu cadastro no Backendflix (simulação)");
        System.out.println("Corpo: Clique no link para confirmar: http://localhost:8080/confirm?token=" + token);
        System.out.println("======================");
    }
}
