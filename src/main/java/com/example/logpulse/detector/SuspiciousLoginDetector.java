package com.example.logpulse.detector;

import java.util.*;
import com.example.logpulse.alert.Alert;
import com.example.logpulse.model.LogEvent;

public class SuspiciousLoginDetector {
    public static List<Alert> detect(List<LogEvent> events) {
        List<Alert> alerts = new ArrayList<>();
        for(LogEvent e: events){
            if(e.eventID==4624){
                if(e.timestamp == null || e.timestamp.length() < 13) continue;
                int hour = Integer.parseInt(e.timestamp.substring(11,13));
                if(hour>=2 && hour<=4)
                    alerts.add(new Alert("Odd hour login: "+e.username,"MEDIUM"));
            }
        }
        return alerts;
    }
}
