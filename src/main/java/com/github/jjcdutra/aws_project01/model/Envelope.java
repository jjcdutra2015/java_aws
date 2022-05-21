package com.github.jjcdutra.aws_project01.model;

import com.github.jjcdutra.aws_project01.enums.EventType;

public class Envelope {

    private EventType eventType;
    private String data;

    public Envelope() {
    }

    public Envelope(EventType eventType, String data) {
        this.eventType = eventType;
        this.data = data;
    }

    public EventType getEventType() {
        return eventType;
    }

    public String getData() {
        return data;
    }
}
