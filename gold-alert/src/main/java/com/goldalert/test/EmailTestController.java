package com.goldalert.test;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailTestController {

    @Value("${spring.mail.username}")
    private String emailFrom;

    private final JavaMailSender mailSender;

    @GetMapping("/test/email")
    public String sendTestEmail() {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailFrom);
        message.setSubject("Gold Alert – Email Test ✅");
        message.setText("If you received this email, SMTP is working perfectly.");

        mailSender.send(message);

        return "Email sent – check inbox / spam";
    }
}
