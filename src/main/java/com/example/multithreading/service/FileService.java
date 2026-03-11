package com.example.multithreading.service;

import com.example.multithreading.dto.HarmonicParallelRequest;
import com.example.multithreading.dto.HarmonicResponse;
import com.example.multithreading.dto.HarmonicSingleRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Properties;

@Service
public class FileService {

    public void writeToFile(String fileName, HarmonicResponse harmonicResponse) {
        try {
            Path path = Paths.get("results/" + fileName + ".txt");

            // create folder if it doesn't exist
            Files.createDirectories(path.getParent());

            String line = String.format(
                    "terms=%d, scale=%d, threads=%d, result=%s, time=%dms%n",
//                    "result=%s, time=%dms%n",
                    harmonicResponse.getTerms(),
                    harmonicResponse.getScale(),
                    harmonicResponse.getThreads(),
                    harmonicResponse.getResult(),
                    harmonicResponse.getExecutionTimeMs()
            );

            Files.write(
                    path,
                    line.getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );

        } catch (IOException e) {
            throw new RuntimeException("Failed to write result to file", e);
        }
    }

    public HarmonicParallelRequest readMultithreadFile(String filePath) {
        Properties properties = new Properties();

        try (InputStream input =
                     getClass().getClassLoader()
                             .getResourceAsStream(filePath + ".properties")) {

            if (input == null) {
                throw new RuntimeException("Config file not found");
            }

            properties.load(input);

            HarmonicParallelRequest request = new HarmonicParallelRequest();

            request.setTerms(Integer.parseInt(properties.getProperty("terms")));
            request.setScale(Integer.parseInt(properties.getProperty("scale")));
            request.setThreads(Integer.parseInt(properties.getProperty("threads")));

            return request;

        } catch (IOException e) {
            throw new RuntimeException("Error reading config file", e);
        }
    }

    public HarmonicSingleRequest readSingleThreadFile(String filePath) {
        Properties properties = new Properties();

        try (InputStream input =
                     getClass().getClassLoader()
                             .getResourceAsStream(filePath + ".properties")) {

            if (input == null) {
                throw new RuntimeException("Config file not found");
            }

            properties.load(input);

            HarmonicSingleRequest request = new HarmonicSingleRequest();

            request.setTerms(Integer.parseInt(properties.getProperty("terms")));
            request.setScale(Integer.parseInt(properties.getProperty("scale")));

            return request;

        } catch (IOException e) {
            throw new RuntimeException("Error reading config file", e);
        }
    }
}
