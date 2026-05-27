package com.marmik.flightbooking.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

// DTO (Data Transfer Object) for the POST /api/v1/flights request body.
// DTOs are separate from models — they represent what the API accepts as input,
// not how data is stored internally. This separation keeps the API contract
// independent from the internal domain model.
public class CreateFlightRequest {

    @NotBlank(message = "Flight number is required")
    // @NotBlank rejects null, empty string, and whitespace-only strings
    private String flightNumber;

    @Min(value = 1, message = "Total seats must be at least 1")
    // @Min ensures we cannot create a flight with 0 or negative seats
    private int totalSeats;

    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public int getTotalSeats() { return totalSeats; }
    public void setTotalSeats(int totalSeats) { this.totalSeats = totalSeats; }
}