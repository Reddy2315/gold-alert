package com.goldalert.service;

import com.goldalert.client.GoldApiClient;
import com.goldalert.model.GoldPrice;
import com.goldalert.repository.GoldPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GoldPriceService {

    private final GoldApiClient goldApiClient;
    private final GoldPriceRepository goldPriceRepository;

    public GoldPrice fetchAndSavePrice() {

        Double price = goldApiClient.fetchGoldPrice();

        // FALLBACK (until real API key is added)
        if (price == null) {
            price = getFallbackPrice();
        }

        GoldPrice goldPrice = GoldPrice.builder()
                .pricePerGram(price)
                .source(price == null ? "FALLBACK" : "METALS_API")
                .fetchedAt(LocalDateTime.now())
                .build();

        return goldPriceRepository.save(goldPrice);
    }

    private double getFallbackPrice() {
        // Simulated realistic gold price (INR/gram)
        return 5800 + Math.random() * 200;
    }
}