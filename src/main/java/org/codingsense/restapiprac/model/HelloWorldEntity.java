package org.codingsense.restapiprac.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class HelloWorldEntity {
    private String message;
    private LocalDateTime timestamp;

    public HelloWorldEntity(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
