package com.example.demo.repository;

import java.sql.Timestamp;
import java.time.Instant;

public class Confirmation {
    private Topics topic;
    private Timestamp timestamp;
    private String operation;
    public Confirmation(Topics topic,String operation)
    {
        this.topic=topic;
        this.timestamp=Timestamp.from(Instant.now());
        this.operation=operation;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Topics getTopic() {
        return topic;
    }

    public void setTopic(Topics topic) {
        this.topic = topic;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
