package com.goldalert.template;

import com.goldalert.model.Alert;

public class NotificationTemplate {

    public static String buildMessage(Alert alert, double currentPrice) {
        return """
                🚨 Gold Price Alert!
                
                Target Price: ₹%.2f / gram
                Current Price: ₹%.2f / gram
                
                ✅ Good time to buy gold!
                
                – Gold Alert System
                """.formatted(alert.getTargetPrice(), currentPrice);
    }
}