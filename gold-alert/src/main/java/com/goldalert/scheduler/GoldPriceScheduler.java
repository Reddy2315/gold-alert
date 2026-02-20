package com.goldalert.scheduler;

import com.goldalert.model.GoldPrice;
import com.goldalert.service.AlertService;
import com.goldalert.service.GoldPriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class GoldPriceScheduler {

    private final GoldPriceService goldPriceService;
    private final AlertService alertService;

    @ConditionalOnProperty(
            name = "gold.scheduler.enabled",
            havingValue = "true"
    )
    // Every 8 hours: 0 0 */8 * * ? = At minute 0 of hour 0, 8, 16 of every day
//    @Scheduled(cron = "0 0 */8 * * ?") // disabled the scheduler to avoid exceeding quotas
    @Scheduled(cron = "0 0 10 * * *") // once per day
    public void fetchGoldPriceJob() {
        log.info("Scheduled job started: Fetching gold price and checking alerts.");
        GoldPrice price = goldPriceService.fetchAndSavePrice();

        // Check if any alerts are triggered by the new price
        alertService.findTriggeredAlerts(price.getPricePerGram());
    }
}

