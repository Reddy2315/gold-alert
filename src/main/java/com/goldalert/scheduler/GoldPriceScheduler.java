package com.goldalert.scheduler;

import com.goldalert.model.GoldPrice;
import com.goldalert.service.AlertService;
import com.goldalert.service.GoldPriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class GoldPriceScheduler {

    private final GoldPriceService goldPriceService;
    private final AlertService alertService;

    @Scheduled(fixedRate = 900000)
    public void fetchGoldPriceJob() {
        log.info("Scheduled job started: Fetching gold price and checking alerts.");
        GoldPrice price = goldPriceService.fetchAndSavePrice();

        // CORE ALERT LOGIC
        alertService.findTriggeredAlerts(price.getPricePerGram());
    }
}

