package com.marmik.flightbooking.exception;

// A dedicated exception for attempting to cancel a booking that is already cancelled.
// Previously this used a generic IllegalStateException which is not descriptive enough.
// A specific exception class makes the codebase easier to read and maintain —
// any developer reading BookingService immediately understands what can go wrong
// without having to trace through generic exception types.
public class BookingAlreadyCancelledException extends RuntimeException {
    public BookingAlreadyCancelledException(String bookingId) {
        super("Booking " + bookingId + " is already cancelled");
    }
}