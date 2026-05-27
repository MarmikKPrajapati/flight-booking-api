package com.marmik.flightbooking.repository;

import com.marmik.flightbooking.model.Flight;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

// @Repository marks this as a Spring-managed data access component.
// Instead of a real database, we use a ConcurrentHashMap as in-memory storage.
// ConcurrentHashMap is thread-safe — it handles multiple simultaneous requests
// without corrupting the data, unlike a regular HashMap.
@Repository
public class FlightRepository {

    // The map key is flightNumber, value is the Flight object.
    // Think of this as our "flights table" in memory.
    private final Map<String, Flight> store = new ConcurrentHashMap<>();

    // Save a flight — adds it to the map or overwrites if same flightNumber exists
    public Flight save(Flight flight) {
        store.put(flight.getFlightNumber(), flight);
        return flight;
    }

    // Look up a flight by its number.
    // Returns Optional<Flight> — forces the caller to handle the "not found" case
    // instead of risking a NullPointerException.
    public Optional<Flight> findByFlightNumber(String flightNumber) {
        return Optional.ofNullable(store.get(flightNumber));
    }
}