package com.example.multithreading.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HarmonicResponse {
    private int terms;
    private int scale;
    private int threads;
    private BigDecimal result;
    private long executionTimeMs;

    public HarmonicResponse(BigDecimal result) {
        this.result = result;
    }

}
