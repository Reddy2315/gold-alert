package com.goldalert.controller;

import com.goldalert.dto.CreateAlertRequest;
import com.goldalert.model.Alert;
import com.goldalert.service.AlertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alerts")
@RequiredArgsConstructor
public class AlertController {

    private final AlertService alertService;

    @PostMapping
    public Alert createAlert(
            @Valid @RequestBody CreateAlertRequest request,
            Authentication authentication
    ) {
        String email = authentication.getName();
        return alertService.createAlert(email, request.getTargetPrice());
    }
}
