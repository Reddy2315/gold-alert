package com.goldalert.controller;

import com.goldalert.model.GoldPrice;
import com.goldalert.service.AlertService;
import com.goldalert.service.GoldPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/price")
@RequiredArgsConstructor
public class GoldPriceController {

    private final GoldPriceService goldPriceService;

    private final AlertService alertService;

    @GetMapping("/fetch")
    public GoldPrice fetchNow() {
        GoldPrice price = goldPriceService.fetchAndSavePrice();

        // trigger alert engine
        alertService.findTriggeredAlerts(price.getPricePerGram());

        return price;
    }

    @GetMapping("/history")
    public List<GoldPrice> getHistory() {
        return goldPriceService.getPriceHistory();
    }
}
