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
        String source = "METALS_API";

        // Fallback to last DB value (REAL DATA)
        if (price == null) {
            GoldPrice lastPrice = goldPriceRepository
                    .findTopByOrderByFetchedAtDesc()
                    .orElseThrow(() ->
                            new IllegalStateException(
                                    "Gold API failed and no DB fallback available"
                            )
                    );

            price = lastPrice.getPricePerGram();
            source = "DB_FALLBACK";
        }

        GoldPrice goldPrice = GoldPrice.builder()
                .pricePerGram(price)
                .source(source)
                .fetchedAt(LocalDateTime.now())
                .build();

        return goldPriceRepository.save(goldPrice);
    }
}