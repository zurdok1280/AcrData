package com.ml.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendVerificationEmail(String to, String token){
        String subject = "Verifica tu cuenta de Digital Latino";
        String verificationURL = "http://localhost:8082/api/auth/verify-email?token=" + token;
        String message = "Gracias por registrarte, Haz clic en el siguiente enlace para activar tu cuenta \n" + verificationURL;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(to);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);
    }
}
