package com.example.multithreading.dto;

import lombok.Data;

@Data
public class ReadFromFileRequest {
    private String readFromFileName;
    private String writeToFileName;
}
