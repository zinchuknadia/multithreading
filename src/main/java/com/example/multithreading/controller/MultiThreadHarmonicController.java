package com.example.multithreading.controller;

import com.example.multithreading.model.Harmonic;
import com.example.multithreading.service.MultiThreadHarmonicService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/multi-thread-harmonic")
public class MultiThreadHarmonicController {

    private final MultiThreadHarmonicService harmonicService;

    public MultiThreadHarmonicController(MultiThreadHarmonicService harmonicService) {
        this.harmonicService = harmonicService;
    }

    @PostMapping
    public Harmonic calculateMultithreadHarmonic(@RequestBody Harmonic harmonic) throws ExecutionException, InterruptedException {
        harmonic.setResult(harmonicService.calculate(harmonic.getTerms(), harmonic.getScale(), harmonic.getThreads()));
        return harmonic;
    }
}
