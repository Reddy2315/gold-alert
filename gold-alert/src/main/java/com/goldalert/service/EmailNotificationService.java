package com.goldalert.service;

import com.goldalert.model.Alert;
import com.goldalert.template.NotificationTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Primary
@Slf4j
public class EmailNotificationService implements NotificationService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.from}")
    private String mailFrom;

    @Async
    @Override
    public void notify(Alert alert, double currentPrice) {
        log.info("📧 EMAIL TRIGGERED for: {}" , alert.getUser().getEmail());

        SimpleMailMessage message = new SimpleMailMessage();

        // REQUIRED FOR BREVO
        message.setFrom(mailFrom);
        message.setTo(alert.getUser().getEmail());
        message.setSubject("Gold Buy Alert 🚨");
        message.setText(
                NotificationTemplate.buildMessage(alert, currentPrice)
        );

        mailSender.send(message);
    }
}