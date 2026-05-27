package com.marmik.flightbooking.service;

import com.marmik.flightbooking.dto.BookingResponse;
import com.marmik.flightbooking.dto.CreateBookingRequest;
import com.marmik.flightbooking.exception.BookingNotFoundException;
import com.marmik.flightbooking.exception.FlightFullException;
import com.marmik.flightbooking.exception.FlightNotFoundException;
import com.marmik.flightbooking.model.Booking;
import com.marmik.flightbooking.model.BookingStatus;
import com.marmik.flightbooking.model.Flight;
import com.marmik.flightbooking.repository.BookingRepository;
import com.marmik.flightbooking.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookingService {

    private final FlightRepository flightRepository;
    private final BookingRepository bookingRepository;

    public BookingService(FlightRepository flightRepository,
                          BookingRepository bookingRepository) {
        this.flightRepository = flightRepository;
        this.bookingRepository = bookingRepository;
    }

    public BookingResponse createBooking(CreateBookingRequest request) {

        // Step 1: Find the flight — throw 404 if it doesn't exist
        Flight flight = flightRepository.findByFlightNumber(request.getFlightNumber())
                .orElseThrow(() -> new FlightNotFoundException(request.getFlightNumber()));

        // Step 2: THE CRITICAL OVERBOOKING PREVENTION BLOCK
        // synchronized(flight) means only ONE thread at a time can execute this block
        // for this specific flight object. This prevents a race condition where two
        // requests both see availableSeats == 1, both pass the check, and both book
        // the last seat — resulting in overbooking.
        // We synchronize on the flight object itself (not the whole method) so that
        // bookings for different flights can still happen simultaneously.
        synchronized (flight) {
            if (flight.getAvailableSeats() <= 0) {
                throw new FlightFullException(request.getFlightNumber());
            }
            // Decrement the seat count inside the synchronized block
            // so no other thread can read the old value before we update it
            flight.setAvailableSeats(flight.getAvailableSeats() - 1);
        }

        // Step 3: Create and save the booking with a unique UUID
        Booking booking = new Booking(
                UUID.randomUUID().toString(), // Generates a unique ID like "550e8400-e29b-41d4..."
                request.getFlightNumber(),
                request.getPassengerName()
        );
        bookingRepository.save(booking);

        // Step 4: Return a response DTO — not the raw Booking model
        return toResponse(booking);
    }

    public void cancelBooking(String bookingId) {

        // Step 1: Find the booking — throw 404 if it doesn't exist
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException(bookingId));

        // Step 2: Prevent cancelling an already-cancelled booking
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new IllegalStateException("Booking is already cancelled");
        }

        // Step 3: Mark the booking as cancelled
        booking.setStatus(BookingStatus.CANCELLED);

        // Step 4: Release the seat back to the flight
        Flight flight = flightRepository.findByFlightNumber(booking.getFlightNumber())
                .orElseThrow(() -> new FlightNotFoundException(booking.getFlightNumber()));

        // synchronized here too — releasing a seat must also be thread-safe
        // to prevent inconsistent seat counts under concurrent load
        synchronized (flight) {
            flight.setAvailableSeats(flight.getAvailableSeats() + 1);
        }
    }

    // Private helper — converts internal Booking model to the API response DTO.
    // Keeps the conversion logic in one place rather than scattered across the codebase.
    private BookingResponse toResponse(Booking booking) {
        return new BookingResponse(
                booking.getBookingId(),
                booking.getFlightNumber(),
                booking.getPassengerName(),
                booking.getStatus(),
                booking.getCreatedAt()
        );
    }
}