package com.marmik.flightbooking.model;

import java.time.LocalDateTime;

// Represents a single booking made by a passenger for a specific flight.
// Each booking gets a unique ID (UUID) generated at creation time.
public class Booking {
    private String bookingId;      // Unique ID — generated as UUID when booking is created
    private String flightNumber;   // Which flight this booking is for
    private String passengerName;  // Who made the booking
    private BookingStatus status;  // CONFIRMED or CANCELLED
    private LocalDateTime createdAt; // Timestamp of when the booking was made

    // New bookings always start as CONFIRMED with the current timestamp
    public Booking(String bookingId, String flightNumber, String passengerName) {
        this.bookingId = bookingId;
        this.flightNumber = flightNumber;
        this.passengerName = passengerName;
        this.status = BookingStatus.CONFIRMED; // Default status on creation
        this.createdAt = LocalDateTime.now();  // Capture creation time
    }

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String passengerName) { this.passengerName = passengerName; }
    public BookingStatus getStatus() { return status; }
    public void setStatus(BookingStatus status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}