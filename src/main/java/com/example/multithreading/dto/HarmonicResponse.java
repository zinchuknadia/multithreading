package com.example.multithreading.dto;

import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;

@Data
public class HarmonicResponse {
    private BigDecimal result;
    @Setter
    private long executionTimeMs;

    public HarmonicResponse(BigDecimal result) {
        this.result = result;
    }

}
