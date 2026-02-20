package com.goldalert.controller;

import com.goldalert.model.GoldPrice;
import com.goldalert.service.GoldPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/price")
@RequiredArgsConstructor
public class GoldPriceController {

    private final GoldPriceService goldPriceService;

    @GetMapping("/fetch")
    public GoldPrice fetchNow() {
        return goldPriceService.fetchAndSavePrice();
    }
}
