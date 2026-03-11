package com.example.multithreading.controller;

import com.example.multithreading.dto.HarmonicResponse;
import com.example.multithreading.dto.HarmonicSingleRequest;
import com.example.multithreading.dto.ReadFromFileRequest;
import com.example.multithreading.service.FileService;
import com.example.multithreading.service.SingleThreadHarmonicService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/single-thread-harmonic")
public class SingleThreadHarmonicController {

    private final SingleThreadHarmonicService harmonicService;
    private final FileService fileService;

    public SingleThreadHarmonicController(SingleThreadHarmonicService harmonicService, FileService fileService) {
        this.harmonicService = harmonicService;
        this.fileService = fileService;

    }

    @PostMapping
    public HarmonicResponse calculateSingleThreadHarmonic(@RequestParam(defaultValue = "false") Boolean writeToFile,
                                                          @RequestBody HarmonicSingleRequest harmonic) {
        HarmonicResponse harmonicResponse = getHarmonicResponse(harmonic);
        if (writeToFile) {
            fileService.writeToFile(harmonic.getWriteToFileName(), harmonicResponse);
        }
        return harmonicResponse;
    }

    @PostMapping("/from-file")
    public HarmonicResponse calculateMultithreadHarmonicFromFile(@RequestParam(defaultValue = "false") Boolean saveToFile,
                                                                 @RequestBody ReadFromFileRequest request) {
        HarmonicSingleRequest harmonic = fileService.readSingleThreadFile(request.getReadFromFileName());
        harmonic.setWriteToFileName(request.getWriteToFileName());
        HarmonicResponse harmonicResponse = getHarmonicResponse(harmonic);
        if (saveToFile) {
            fileService.writeToFile(harmonic.getWriteToFileName(), harmonicResponse);
        }
        return harmonicResponse;
    }

    private HarmonicResponse getHarmonicResponse(HarmonicSingleRequest harmonic) {
        HarmonicResponse harmonicResponse = harmonicService.calculate(harmonic.getTerms(), harmonic.getScale());
        harmonicResponse.setTerms(harmonic.getTerms());
        harmonicResponse.setScale(harmonic.getScale());
        harmonicResponse.setThreads(1);
        return harmonicResponse;
    }
}
