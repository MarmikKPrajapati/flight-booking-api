package com.marmik.flightbooking.model;

// Represents a flight in our system.
// We track both totalSeats (never changes) and availableSeats (decrements on booking).
// This lets us detect overbooking: if availableSeats == 0, the flight is full.
public class Flight {
    private String flightNumber;  // Unique identifier e.g. "AC101"
    private int totalSeats;       // Total capacity — set once at creation, never changes
    private int availableSeats;   // Remaining bookable seats — changes with each booking/cancellation

    // When a flight is created, all seats are available
    public Flight(String flightNumber, int totalSeats) {
        this.flightNumber = flightNumber;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats; // Start fully open
    }

    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public int getTotalSeats() { return totalSeats; }
    public void setTotalSeats(int totalSeats) { this.totalSeats = totalSeats; }
    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }
}