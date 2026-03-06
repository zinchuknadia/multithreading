package com.example.multithreading.dto;

import lombok.Data;

@Data
public class HarmonicParallelRequest {
    private int terms;
    private int scale;
    private int threads;
}
