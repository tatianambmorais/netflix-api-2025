package com.avengers.netflix.service;

import com.avengers.netflix.model.Usuario;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmailService {
    public static final String ASSUNTO = "Confirme seu cadastro no Backendflix (simulação)";
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void enviar(Usuario usuario) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(usuario.getEmail());
            helper.setSubject(ASSUNTO);
            helper.setText(geraCorpoEmail(usuario));


            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao enviar e-mail", e);
        }
    }

    private String geraCorpoEmail(Usuario usuario) {
        String token = usuario.getToken();

        String corpo = "Olá, " + usuario.getNomeCompleto() + ". Estamos muito felizes com sua chegada. Seu token de confirmação de cadastro é: " + token;
        return corpo;
    }


    public void enviarConfirmacao(String para, String token){
        System.out.println("=== EMAIL SIMULADO ===");
        System.out.println("Para: " + para);
        System.out.println("Assunto: Confirme seu cadastro no Backendflix (simulação)");
        System.out.println("Corpo: Clique no link para confirmar: http://localhost:8080/confirm?token=" + token);
        System.out.println("======================");
    }


}
