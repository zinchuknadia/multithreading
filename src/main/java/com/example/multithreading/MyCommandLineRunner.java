package com.example.multithreading;

import com.example.multithreading.service.MultiThreadHarmonicService;
import com.example.multithreading.service.SingleThreadHarmonicService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Enter terms and scale factor");
        int terms = scanner.nextInt();
        System.out.println("Enter scale factor");
        int scale = scanner.nextInt();
        System.out.println("Enter threads");
        int threads = scanner.nextInt();

        BigDecimal result;
        if (threads == 1) {
            result = singleThreadCalculation(terms, scale);
        } else if (threads > 1) {
            result = multiThreadCalculation(terms, scale, threads);
        } else {
            System.out.println("Invalid threads");
            return;
        }

        System.out.println("Result: " + result);
    }

    private BigDecimal singleThreadCalculation(int terms, int scale) {
        SingleThreadHarmonicService singleThreadHarmonicService = new SingleThreadHarmonicService();
        return singleThreadHarmonicService.calculate(terms, scale);
    }

    private BigDecimal multiThreadCalculation(int terms, int scale, int threads) throws ExecutionException, InterruptedException {
        MultiThreadHarmonicService multiThreadHarmonicService = new MultiThreadHarmonicService();
        return multiThreadHarmonicService.calculate(terms, scale, threads);
    }
}
