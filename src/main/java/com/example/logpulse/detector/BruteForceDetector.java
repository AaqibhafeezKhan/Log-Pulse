package com.example.logpulse.detector;

import java.util.*;
import com.example.logpulse.alert.Alert;
import com.example.logpulse.model.LogEvent;

public class BruteForceDetector {
    public static List<Alert> detect(List<LogEvent> events) {
        Map<String,Integer> fail = new HashMap<>();
        Set<String> success = new HashSet<>();
        List<Alert> alerts = new ArrayList<>();

        for(LogEvent e: events){
            if(e.eventID==4625)
                fail.put(e.ipAddress, fail.getOrDefault(e.ipAddress,0)+1);
            if(e.eventID==4624)
                success.add(e.ipAddress);
        }

        for(String ip: fail.keySet()){
            if(fail.get(ip)>5){
                if(success.contains(ip))
                    alerts.add(new Alert("Brute force SUCCESS: "+ip,"CRITICAL"));
                else
                    alerts.add(new Alert("Brute force attempt: "+ip,"HIGH"));
            }
        }
        return alerts;
    }
}
