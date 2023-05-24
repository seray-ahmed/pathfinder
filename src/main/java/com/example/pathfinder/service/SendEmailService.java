package com.example.pathfinder.service;

import com.example.pathfinder.model.Activation;
import com.example.pathfinder.model.User;
import com.example.pathfinder.repository.ActivationRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class SendEmailService {
    private final String from = "emre.metin2002@gmail.com";
    private final String password = "dnzeznuyjwszdkzx";
    private final String host = "smtp.gmail.com";
    private final Properties properties = System.getProperties();
    private String to;
    private Session session;
    private String subject;
    private String text;

    private final ActivationRepository activationRepository;

    public SendEmailService(ActivationRepository activationRepository) {
        this.activationRepository = activationRepository;

        session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(from, password);

            }
        });
    }

    public void sendWelcomeEmail(User user) {
        setUpServer();
        emailContentConfiguration(user);
        sendEmailToUser();
    }
    public void sendActivationEmail(User user) {
        setUpServer();
        Activation activation = new Activation();
        activation.setUser(user);
        activationRepository.saveAndFlush(activation);
        emailActivationConfiguration(activation);
        sendEmailToUser();
    }

    private void emailContentConfiguration(User user){
        this.to = user.getEmail();
        this.subject = "Успешна регистрация в Pathfinder!";
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Добре Дошли %s,\n\n", user.getFullName()));
        sb.append(String.format("с нетърпение чакаме да започнете да ползване услугите ни и да Ви улесним при избирането на маршрути.\n\n"));
        sb.append(String.format("Просто цъкнете линкът: %s и ползвайте услугите" +
                " ни(за да добавяне нови маршрути в системата трябва да имате одобрен профил). При проблеми се свържете с нас: %s.\n\n", "http://localhost:8080/login", "emre.metin2002@gmail.com"));
        sb.append(String.format("Хубав ден!\n"));
        sb.append(String.format("Екипът на Pathfinder"));
        this.text = sb.toString().trim();
    }

    private void emailActivationConfiguration(Activation activation){
        this.to = activation.getUser().getEmail();
        this.subject = "Активиране на профил в Pathfinder!";
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Добре Дошли %s,\n\n", activation.getUser().getFullName()));
        sb.append(String.format("Това е активационният Ви линк: %s\n\n","http://localhost:8080/activation/" + activation.getId()));
        sb.append(String.format("Хубав ден!\n"));
        sb.append(String.format("Екипът на Pathfinder"));
        this.text = sb.toString().trim();
    }
    private void setUpServer() {
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        session.setDebug(true);
    }

    private void sendEmailToUser() {
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(this.subject);
            message.setText(this.text);

            System.out.println("sending...");

            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
