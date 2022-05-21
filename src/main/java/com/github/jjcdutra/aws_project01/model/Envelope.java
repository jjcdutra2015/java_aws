package com.github.jjcdutra.aws_project01.model;

public class Envelope {

    private String eventType;
    private String data;

    public Envelope() {
    }

    public Envelope(String eventType, String data) {
        this.eventType = eventType;
        this.data = data;
    }

    public String getEventType() {
        return eventType;
    }

    public String getData() {
        return data;
    }
}
