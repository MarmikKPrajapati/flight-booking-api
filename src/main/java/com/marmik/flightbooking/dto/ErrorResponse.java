package com.marmik.flightbooking.dto;

import java.time.LocalDateTime;

// Standard error response returned by the API whenever something goes wrong.
// Having a consistent error format across all endpoints makes it easier
// for clients to handle failures — they always know what shape to expect.
public class ErrorResponse {
    private String message;          // Human-readable description of what went wrong
    private LocalDateTime timestamp; // When the error occurred — useful for debugging

    public ErrorResponse(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() { return message; }
    public LocalDateTime getTimestamp() { return timestamp; }
}