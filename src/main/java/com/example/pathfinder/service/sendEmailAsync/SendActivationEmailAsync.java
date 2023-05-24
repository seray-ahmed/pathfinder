package com.example.pathfinder.service.sendEmailAsync;

import com.example.pathfinder.model.User;
import com.example.pathfinder.service.SendEmailService;
import org.springframework.stereotype.Component;

@Component
public class SendActivationEmailAsync implements Runnable {

   private final SendEmailService sendEmailService;
   private User user;

    public SendActivationEmailAsync(SendEmailService sendEmailService) {
        this.sendEmailService = sendEmailService;
    }


    @Override
    public void run() {
        sendEmailService.sendActivationEmail(user);
    }

    public void setUser(User user) {
        this.user = user;
    }
}
