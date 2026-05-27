package com.marmik.flightbooking.dto;

import com.marmik.flightbooking.model.BookingStatus;
import java.time.LocalDateTime;

// DTO for the API response after a successful booking.
// We return a response DTO instead of the raw Booking model to control
// exactly what fields the client sees — good practice for API design.
public class BookingResponse {
    private String bookingId;       // Client uses this ID for future cancellation
    private String flightNumber;
    private String passengerName;
    private BookingStatus status;   // Will be CONFIRMED on creation
    private LocalDateTime createdAt;

    public BookingResponse(String bookingId, String flightNumber, String passengerName,
                           BookingStatus status, LocalDateTime createdAt) {
        this.bookingId = bookingId;
        this.flightNumber = flightNumber;
        this.passengerName = passengerName;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getBookingId() { return bookingId; }
    public String getFlightNumber() { return flightNumber; }
    public String getPassengerName() { return passengerName; }
    public BookingStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}