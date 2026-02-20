package com.goldalert.scheduler;

import com.goldalert.service.GoldPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GoldPriceScheduler {

    private final GoldPriceService goldPriceService;

    @Scheduled(fixedRate = 900000) // 15 minutes
    public void fetchGoldPriceJob() {
        goldPriceService.fetchAndSavePrice();
    }
}
