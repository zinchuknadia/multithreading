package com.example.multithreading.controller;

import com.example.multithreading.dto.HarmonicResponse;
import com.example.multithreading.dto.HarmonicSingleRequest;
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
    public HarmonicResponse calculateSingleThreadHarmonic(@RequestBody HarmonicSingleRequest harmonic) {
        return harmonicService.calculate(harmonic.getTerms(), harmonic.getScale());
    }

}
