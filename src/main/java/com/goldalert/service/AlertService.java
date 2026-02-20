package com.goldalert.service;

import com.goldalert.model.Alert;
import com.goldalert.repository.AlertRepository;
import com.goldalert.model.User;
import com.goldalert.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertService {

    private final AlertRepository alertRepository;
    private final UserRepository userRepository;

    public Alert createAlert(String email, double targetPrice) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Alert alert = Alert.builder()
                .user(user)
                .targetPrice(targetPrice)
                .triggered(false)
                .build();

        return alertRepository.save(alert);
    }

    @Transactional
    public List<Alert> findTriggeredAlerts(double currentPrice) {

        List<Alert> alerts =
                alertRepository.findByTriggeredFalseAndTargetPriceGreaterThanEqual(currentPrice);

        alerts.forEach(alert -> {
            alert.setTriggered(true);
            alert.setTriggeredAt(LocalDateTime.now());
        });

        return alerts;
    }
}
