package com.goldalert.template;

import com.goldalert.model.Alert;

public class NotificationTemplate {

    public static String buildMessage(Alert alert, double currentPrice) {
        return """
        🚨 Gold Buy Alert – Price Target Reached

        The gold price you were tracking has reached your target.

        • Target Price : ₹%.2f / gram
        • Current Price: ₹%.2f / gram

        This could be a favorable opportunity to consider buying gold.

        You are receiving this alert because you set a price notification
        in the Gold Price Alert System.

        — Gold Alert System
        """.formatted(alert.getTargetPrice(), currentPrice);
    }
}