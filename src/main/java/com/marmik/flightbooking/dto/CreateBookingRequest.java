package com.marmik.flightbooking.dto;

import jakarta.validation.constraints.NotBlank;

// DTO for the POST /api/v1/bookings request body.
// The client is expected to already know the flight number —
// there is no flight search in this API.
public class CreateBookingRequest {

    @NotBlank(message = "Flight number is required")
    private String flightNumber;  // The flight to book a seat on

    @NotBlank(message = "Passenger name is required")
    private String passengerName; // Name of the passenger being booked

    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String passengerName) { this.passengerName = passengerName; }
}