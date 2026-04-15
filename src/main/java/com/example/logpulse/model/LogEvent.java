package com.example.logpulse.model;

public class LogEvent {
    public int eventID;
    public String timestamp;
    public String username;
    public String ipAddress;

    public LogEvent(int e, String t, String u, String i) {
        eventID = e;
        timestamp = t;
        username = u;
        ipAddress = i;
    }
}
