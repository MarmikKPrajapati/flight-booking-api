package com.marmik.flightbooking.repository;

import com.marmik.flightbooking.model.Booking;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

// In-memory storage for bookings.
// Key is bookingId (UUID), value is the Booking object.
// Think of this as our "bookings table" in memory.
@Repository
public class BookingRepository {

    private final Map<String, Booking> store = new ConcurrentHashMap<>();

    // Save a new booking or update an existing one (used for cancellation status update)
    public Booking save(Booking booking) {
        store.put(booking.getBookingId(), booking);
        return booking;
    }

    // Look up a booking by its unique ID.
    // Returns Optional to force proper "not found" handling in the service layer.
    public Optional<Booking> findById(String bookingId) {
        return Optional.ofNullable(store.get(bookingId));
    }
}