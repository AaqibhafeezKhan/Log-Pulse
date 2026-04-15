package com.example.logpulse.detector;

import java.util.*;
import com.example.logpulse.alert.Alert;
import com.example.logpulse.model.LogEvent;

public class AccountCreationDetector {
    public static List<Alert> detect(List<LogEvent> events) {
        List<Alert> alerts = new ArrayList<>();
        for(LogEvent e: events){
            if(e.eventID==4720)
                alerts.add(new Alert("New user: "+e.username,"HIGH"));
        }
        return alerts;
    }
}
