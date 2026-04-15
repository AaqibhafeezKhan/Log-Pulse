package com.example.logpulse.detector;

import java.util.*;
import com.example.logpulse.alert.Alert;
import com.example.logpulse.model.LogEvent;

public class MultipleIPLoginDetector {
    public static List<Alert> detect(List<LogEvent> events) {
        Map<String,Set<String>> map = new HashMap<>();
        List<Alert> alerts = new ArrayList<>();

        for(LogEvent e: events){
            if(e.eventID==4624){
                map.putIfAbsent(e.username,new HashSet<>());
                map.get(e.username).add(e.ipAddress);
            }
        }

        for(Map.Entry<String,Set<String>> entry: map.entrySet()){
            if(entry.getValue().size()>3)
                alerts.add(new Alert("Multiple IP: "+entry.getKey(),"HIGH"));
        }
        return alerts;
    }
}
