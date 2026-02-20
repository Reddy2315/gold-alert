package com.goldalert.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class GoldApiClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${gold.api.url}")
    private String apiUrl;

    @Value("${gold.api.key}")
    private String apiKey;

    @SuppressWarnings("unchecked")
    public Double fetchGoldPrice() {

        try {
            String url = apiUrl + "?api_key=" + apiKey + "&base=XAU&currencies=INR";

            Map<String, Object> response =
                    restTemplate.getForObject(url, Map.class);

            Map<String, Object> rates =
                    (Map<String, Object>) response.get("rates");

            return Double.parseDouble(rates.get("INR").toString());

        } catch (Exception ex) {
            // VERY IMPORTANT: do NOT crash system
            return null;
        }
    }
}
