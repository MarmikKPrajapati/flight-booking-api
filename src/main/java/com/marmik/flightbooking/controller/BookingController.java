package com.marmik.flightbooking.controller;

import com.marmik.flightbooking.dto.BookingResponse;
import com.marmik.flightbooking.dto.CreateBookingRequest;
import com.marmik.flightbooking.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // POST /api/v1/bookings
    // Books a seat on a flight for a passenger.
    // Returns 201 Created with the booking details including the bookingId
    // that the client will need for cancellation.
    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(
            @Valid @RequestBody CreateBookingRequest request) {
        BookingResponse response = bookingService.createBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // DELETE /api/v1/bookings/{bookingId}
    // Cancels an existing booking and releases the seat back to the flight.
    // @PathVariable extracts the bookingId from the URL.
    // Returns 204 No Content — correct REST convention for a successful
    // delete/cancel where there is nothing to return in the body.
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> cancelBooking(@PathVariable String bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.noContent().build();
    }
}