package com.example.logpulse.service;

import java.io.InputStream;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.logpulse.alert.Alert;
import com.example.logpulse.alert.AlertRepository;
import com.example.logpulse.detector.DetectionEngine;
import com.example.logpulse.model.LogEvent;
import com.example.logpulse.parser.XmlLogParser;

@Service
public class LogPulseService {

    private final AlertRepository alertRepository;

    public LogPulseService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public List<Alert> analyze(InputStream inputStream) {
        List<LogEvent> events = XmlLogParser.parse(inputStream);
        List<Alert> alerts = DetectionEngine.runAll(events);
        return alertRepository.saveAll(alerts);
    }

    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    public void clearAlerts() {
        alertRepository.deleteAll();
    }
}
