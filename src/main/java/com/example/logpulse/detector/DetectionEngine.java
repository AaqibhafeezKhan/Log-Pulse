package com.example.logpulse.detector;

import java.util.*;
import com.example.logpulse.alert.Alert;
import com.example.logpulse.model.LogEvent;

public class DetectionEngine {
    public static List<Alert> runAll(List<LogEvent> events) {
        List<Alert> alerts = new ArrayList<>();
        alerts.addAll(BruteForceDetector.detect(events));
        alerts.addAll(SuspiciousLoginDetector.detect(events));
        alerts.addAll(AccountCreationDetector.detect(events));
        alerts.addAll(MultipleIPLoginDetector.detect(events));
        return alerts;
    }
}
