package com.example.multithreading.model;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Harmonic {
    @NotNull
    private int terms;
    private int scale;
    private int threads;

    private BigDecimal result;

    public Harmonic() {
    }

    public Harmonic(int terms, int scale, int threads) {
        this.terms = terms;
        this.scale = scale;
        this.threads = threads;
    }

    public Harmonic(int terms, int scale) {
        this.terms = terms;
        this.scale = scale;
    }

    public int getTerms() {
        return terms;
    }

    public void setTerms(int terms) {
        this.terms = terms;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }
}
