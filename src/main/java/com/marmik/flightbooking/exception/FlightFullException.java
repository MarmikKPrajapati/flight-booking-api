package com.marmik.flightbooking.exception;

// Thrown when a client tries to book a flight that has no available seats.
// GlobalExceptionHandler catches this and returns a 409 Conflict response.
// 409 Conflict is the correct HTTP status — the request was valid but
// conflicts with the current state of the resource (no seats left).
public class FlightFullException extends RuntimeException {
    public FlightFullException(String flightNumber) {
        super("Flight " + flightNumber + " is fully booked");
    }
}