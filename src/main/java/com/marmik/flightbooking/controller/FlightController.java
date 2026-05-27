package com.marmik.flightbooking.controller;

import com.marmik.flightbooking.dto.CreateFlightRequest;
import com.marmik.flightbooking.model.Flight;
import com.marmik.flightbooking.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// @RestController combines @Controller and @ResponseBody —
// it means every method returns data (JSON) directly,
// not a view/template name.
// @RequestMapping sets the base URL for all endpoints in this class.
@RestController
@RequestMapping("/api/v1/flights")
public class FlightController {

    private final FlightService flightService;

    // Constructor injection — Spring provides the FlightService automatically
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    // POST /api/v1/flights
    // Creates a new flight in the system.
    // @Valid triggers the validation annotations on CreateFlightRequest
    // (@NotBlank, @Min) — if validation fails, Spring throws
    // MethodArgumentNotValidException which our GlobalExceptionHandler catches.
    // Returns 201 Created (not 200 OK) — 201 is the correct status for
    // resource creation per REST conventions.
    @PostMapping
    public ResponseEntity<Flight> createFlight(@Valid @RequestBody CreateFlightRequest request) {
        Flight flight = flightService.createFlight(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(flight);
    }
}