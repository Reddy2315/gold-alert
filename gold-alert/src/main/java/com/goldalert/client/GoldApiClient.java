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
            String url = apiUrl
                    + "?api_key=" + apiKey
                    + "&currency=INR"
                    + "&unit=g";

            Map<String, Object> response =
                    restTemplate.getForObject(url, Map.class);

            if (response == null || !response.containsKey("metals")) {
                return null;
            }

            Map<String, Object> metals = (Map<String, Object>) response.get("metals");

            if (metals == null || !metals.containsKey("mcx_gold")) {
                return null;
            }

            return Double.parseDouble(metals.get("mcx_gold").toString());

        } catch (Exception ex) {
            // IMPORTANT: Never crash scheduler
            return null;
        }
    }
}