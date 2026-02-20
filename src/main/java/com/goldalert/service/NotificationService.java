package com.goldalert.service;

import com.goldalert.model.Alert;

public interface NotificationService {
    void notify(Alert alert, double currentPrice);
}