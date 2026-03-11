package com.example.multithreading.controller;

import com.example.multithreading.dto.HarmonicParallelRequest;
import com.example.multithreading.dto.HarmonicResponse;
import com.example.multithreading.dto.ReadFromFileRequest;
import com.example.multithreading.service.FileService;
import com.example.multithreading.service.MultiThreadHarmonicService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/multi-thread-harmonic")
public class MultiThreadHarmonicController {

    private final MultiThreadHarmonicService harmonicService;
    private final FileService fileService;

    public MultiThreadHarmonicController(MultiThreadHarmonicService harmonicService, FileService fileService) {
        this.harmonicService = harmonicService;
        this.fileService = fileService;
    }

    @PostMapping
    public HarmonicResponse calculateMultithreadHarmonic(@RequestParam(defaultValue = "false") Boolean saveToFile,
                                                         @RequestBody HarmonicParallelRequest harmonic) throws ExecutionException, InterruptedException {
        HarmonicResponse harmonicResponse = getHarmonicResponse(harmonic);
        if (saveToFile) {
            fileService.writeToFile(harmonic.getWriteToFileName(), harmonicResponse);
        }
        return harmonicResponse;
    }

    @PostMapping("/from-file")
    public HarmonicResponse calculateMultithreadHarmonicFromFile(@RequestParam(defaultValue = "false") Boolean saveToFile,
                                                                 @RequestBody ReadFromFileRequest request) throws ExecutionException, InterruptedException {
        HarmonicParallelRequest harmonic = fileService.readMultithreadFile(request.getReadFromFileName());
        harmonic.setWriteToFileName(request.getWriteToFileName());
        HarmonicResponse harmonicResponse = getHarmonicResponse(harmonic);
        if (saveToFile) {
            fileService.writeToFile(harmonic.getWriteToFileName(), harmonicResponse);
        }
        return harmonicResponse;
    }

    private HarmonicResponse getHarmonicResponse(HarmonicParallelRequest harmonic) throws ExecutionException, InterruptedException {
        HarmonicResponse harmonicResponse = harmonicService.calculate(harmonic.getTerms(), harmonic.getScale(), harmonic.getThreads());
        harmonicResponse.setTerms(harmonic.getTerms());
        harmonicResponse.setScale(harmonic.getScale());
        harmonicResponse.setThreads(harmonic.getThreads());
        return harmonicResponse;
    }
}
