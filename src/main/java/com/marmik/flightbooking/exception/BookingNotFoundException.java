package com.marmik.flightbooking.exception;

// Thrown when a client tries to cancel a booking ID that doesn't exist.
// GlobalExceptionHandler catches this and returns a 404 response.
public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(String bookingId) {
        super("Booking not found: " + bookingId);
    }
}