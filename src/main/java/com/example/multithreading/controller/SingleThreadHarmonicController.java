package com.example.multithreading.controller;

import com.example.multithreading.model.Harmonic;
import com.example.multithreading.service.SingleThreadHarmonicService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/single-thread-harmonic")
public class SingleThreadHarmonicController {

    private final SingleThreadHarmonicService harmonicService;

    public SingleThreadHarmonicController(SingleThreadHarmonicService harmonicService) {
        this.harmonicService = harmonicService;
    }

    @PostMapping
    public Harmonic calculateSingleThreadHarmonic(@RequestBody Harmonic harmonic) {
        harmonic.setThreads(1);
        harmonic.setResult(harmonicService.calculate(harmonic.getTerms(), harmonic.getScale()));
        return harmonic;
    }

}
