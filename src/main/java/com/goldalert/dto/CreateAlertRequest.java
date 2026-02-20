package com.goldalert.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateAlertRequest {

    @Positive
    private double targetPrice;
}
