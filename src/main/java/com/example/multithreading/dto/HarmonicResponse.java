package com.example.multithreading.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class HarmonicResponse {
    private BigDecimal result;
}
