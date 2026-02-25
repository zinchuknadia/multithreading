package com.example.multithreading.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class SingleThreadHarmonicService {

    public BigDecimal calculate(int terms, int scale) {
        BigDecimal sum = BigDecimal.ZERO;

        for (int i = 1; i <= terms; i++) {
            BigDecimal term = BigDecimal.ONE.divide(
                    BigDecimal.valueOf(i),
                    scale,
                    RoundingMode.HALF_UP
            );
            sum = sum.add(term);
        }

        return sum.setScale(scale, RoundingMode.HALF_UP);
    }
}
