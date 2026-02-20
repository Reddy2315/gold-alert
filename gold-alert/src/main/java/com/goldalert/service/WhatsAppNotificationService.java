//package com.goldalert.service;
//
//import com.goldalert.model.Alert;
//import com.goldalert.template.NotificationTemplate;
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class WhatsAppNotificationService implements NotificationService {
//
//    @Value("${twilio.accountSid}")
//    private String accountSid;
//
//    @Value("${twilio.authToken}")
//    private String authToken;
//
//    @Value("${twilio.whatsappFrom}")
//    private String from;
//
//    @PostConstruct
//    void init() {
//        Twilio.init(accountSid, authToken);
//    }
//
//    @Async
//    @Override
//    public void notify(Alert alert, double currentPrice) {
//
//        Message.creator(
//                new com.twilio.type.PhoneNumber("whatsapp:+91XXXXXXXXXX"),
//                new com.twilio.type.PhoneNumber(from),
//                NotificationTemplate.buildMessage(alert, currentPrice)
//        ).create();
//    }
//}