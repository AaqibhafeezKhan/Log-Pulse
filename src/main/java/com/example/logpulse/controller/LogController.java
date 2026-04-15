package com.example.logpulse.controller;

import java.io.IOException;
import java.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import com.example.logpulse.alert.Alert;
import com.example.logpulse.service.LogPulseService;

@CrossOrigin(origins = "*")
@RestController
public class LogController {

    private final LogPulseService s;

    private static final List<String> ALLOWED_EXTENSIONS = List.of(".xml", ".log", ".txt", ".json", ".csv");

    public LogController(LogPulseService s) {
        this.s = s;
    }

    private void validateFile(MultipartFile file) throws IllegalArgumentException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("No file provided or file is empty.");
        }
        String originalName = file.getOriginalFilename();
        if (originalName == null) {
            throw new IllegalArgumentException("Invalid file name.");
        }
        String lower = originalName.toLowerCase();
        boolean allowed = ALLOWED_EXTENSIONS.stream().anyMatch(lower::endsWith);
        if (!allowed) {
            throw new IllegalArgumentException(
                "Unsupported file type. Allowed: " + String.join(", ", ALLOWED_EXTENSIONS));
        }
    }

    @PostMapping("/analyze")
    public ResponseEntity<?> analyze(@RequestParam("file") MultipartFile file) {
        try {
            validateFile(file);
            return ResponseEntity.ok(s.analyze(file.getInputStream()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(Map.of("error", "Failed to read uploaded file."));
        }
    }

    @PostMapping("/summary")
    public ResponseEntity<?> summary(@RequestParam("file") MultipartFile file) {
        try {
            validateFile(file);
            List<Alert> alerts = s.analyze(file.getInputStream());
            Map<String, Object> data = new HashMap<>();
            data.put("totalAlerts", alerts.size());
            data.put("alerts", alerts);
            return ResponseEntity.ok(data);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(Map.of("error", "Failed to read uploaded file."));
        }
    }
}
