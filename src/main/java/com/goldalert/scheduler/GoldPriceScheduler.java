package com.goldalert.scheduler;

import com.goldalert.model.GoldPrice;
import com.goldalert.service.AlertService;
import com.goldalert.service.GoldPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GoldPriceScheduler {

    private final GoldPriceService goldPriceService;
    private final AlertService alertService;

    @Scheduled(fixedRate = 900000)
    public void fetchGoldPriceJob() {

        GoldPrice price = goldPriceService.fetchAndSavePrice();

        // CORE ALERT LOGIC
        alertService.findTriggeredAlerts(price.getPricePerGram());
    }
}

