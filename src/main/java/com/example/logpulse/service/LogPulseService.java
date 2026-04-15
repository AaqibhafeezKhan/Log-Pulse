package com.example.logpulse.service;

import java.io.InputStream;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.logpulse.alert.Alert;
import com.example.logpulse.detector.DetectionEngine;
import com.example.logpulse.model.LogEvent;
import com.example.logpulse.parser.XmlLogParser;

@Service
public class LogPulseService {

    public List<Alert> analyze(InputStream inputStream) {
        List<LogEvent> events = XmlLogParser.parse(inputStream);
        return DetectionEngine.runAll(events);
    }
}
