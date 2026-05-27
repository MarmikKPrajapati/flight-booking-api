package com.marmik.flightbooking.exception;

// Thrown when a client tries to book a flight that doesn't exist in our system.
// Extends RuntimeException so we don't need to declare it in method signatures.
// The GlobalExceptionHandler will catch this and return a 404 response.
public class FlightNotFoundException extends RuntimeException {
    public FlightNotFoundException(String flightNumber) {
        super("Flight not found: " + flightNumber);
    }
}