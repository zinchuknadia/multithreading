package com.example.multithreading.controller;

import com.example.multithreading.dto.HarmonicParallelRequest;
import com.example.multithreading.dto.HarmonicResponse;
import com.example.multithreading.service.MultiThreadHarmonicService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/multi-thread-harmonic")
public class MultiThreadHarmonicController {

    private final MultiThreadHarmonicService harmonicService;

    public MultiThreadHarmonicController(MultiThreadHarmonicService harmonicService) {
        this.harmonicService = harmonicService;
    }

    @PostMapping
    public HarmonicResponse calculateMultithreadHarmonic(@RequestBody HarmonicParallelRequest harmonic) throws ExecutionException, InterruptedException {
        BigDecimal result = harmonicService.calculate(harmonic.getTerms(), harmonic.getScale(), harmonic.getThreads());
        return new HarmonicResponse(result);
    }
}
