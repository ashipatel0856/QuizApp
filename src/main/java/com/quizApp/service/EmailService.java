package com.quizApp.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOtpEmail(String toEmail, String otpCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your OTP Code for Quiz App by ashsih kumar");
        message.setText("Your OTP is: " + otpCode + "\nThis code will expire in 5 minutes.");
        message.setFrom("ashishkumarr0856@gmail.com"); // You can set your email here as the sender
        mailSender.send(message);
    }
}
