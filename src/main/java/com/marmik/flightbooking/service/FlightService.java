package com.marmik.flightbooking.service;

import com.marmik.flightbooking.dto.CreateFlightRequest;
import com.marmik.flightbooking.model.Flight;
import com.marmik.flightbooking.repository.FlightRepository;
import org.springframework.stereotype.Service;

// @Service marks this as a Spring-managed business logic component.
// FlightService handles creating flights — it is kept simple intentionally
// because the complex logic lives in BookingService.
@Service
public class FlightService {

    private final FlightRepository flightRepository;

    // Constructor injection — Spring automatically provides the repository.
    // This is preferred over @Autowired field injection because it makes
    // dependencies explicit and easier to test.
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Flight createFlight(CreateFlightRequest request) {
        // Prevent duplicate flights — if the flight number already exists, reject it
        if (flightRepository.findByFlightNumber(request.getFlightNumber()).isPresent()) {
            throw new IllegalArgumentException(
                    "Flight already exists: " + request.getFlightNumber()
            );
        }

        // Create the flight object and save it to our in-memory store
        Flight flight = new Flight(request.getFlightNumber(), request.getTotalSeats());
        return flightRepository.save(flight);
    }
}