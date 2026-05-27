package com.marmik.flightbooking.model;

// Using an enum instead of a String prevents invalid values like "CANCELD" typos.
public enum BookingStatus {
    CONFIRMED,  // Booking is active — seat is reserved
    CANCELLED   // Booking was cancelled — seat has been released back
}