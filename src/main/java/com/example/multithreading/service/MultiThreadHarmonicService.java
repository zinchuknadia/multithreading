package com.example.multithreading.service;

import com.example.multithreading.dto.HarmonicResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class MultiThreadHarmonicService {

    public HarmonicResponse calculate(int terms, int scale, int threads) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Future<BigDecimal>> futures = new ArrayList<>();

        int chunkSize = terms / threads;

        for (int t = 0; t < threads; t++) {
            int start = t * chunkSize + 1;
            int end = (t == threads - 1) ? terms : (t + 1) * chunkSize;

            futures.add(executor.submit(() -> {
                BigDecimal partial = BigDecimal.ZERO;
                for (int i = start; i <= end; i++) {
                    BigDecimal term = BigDecimal.ONE.divide(
                            BigDecimal.valueOf(i),
                            scale,
                            RoundingMode.HALF_UP
                    );
                    partial = partial.add(term);
                }
                return partial;
            }));
        }

        BigDecimal result = BigDecimal.ZERO;
        for (Future<BigDecimal> future : futures) {
            result = result.add(future.get());
        }

        executor.shutdown();

        return new HarmonicResponse(result.setScale(scale, RoundingMode.HALF_UP));
    }
}
