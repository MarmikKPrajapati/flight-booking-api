# Flight Booking API

A lightweight REST API for booking flight tickets, built with Java and Spring Boot.
Implements core booking operations with overbooking prevention using thread-safe
in-memory storage.

## Prerequisites

- Java 21 or higher
- No additional setup required — Maven Wrapper is included

## How to Run

Clone the repository and run:

```bash
# Windows
mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

The application starts on `http://localhost:8080`.

---

## API Endpoints

### 1. Create a Flight
Initializes a flight in the system with a set number of seats.

```
POST /api/v1/flights
Content-Type: application/json
```

Request:
```json
{
  "flightNumber": "AC101",
  "totalSeats": 150
}
```

Response: 201 Created
```json
{
  "flightNumber": "AC101",
  "totalSeats": 150,
  "availableSeats": 150
}
```

Error cases:
- 400 Bad Request — missing or invalid fields
- 409 Conflict — flight number already exists

---

### 2. Book a Seat
Books a seat on an existing flight for a passenger.

```
POST /api/v1/bookings
Content-Type: application/json
```

Request:
```json
{
  "flightNumber": "AC101",
  "passengerName": "John Smith"
}
```

Response: 201 Created
```json
{
  "bookingId": "6397ee92-1fb5-499a-b34c-3e02de95d152",
  "flightNumber": "AC101",
  "passengerName": "John Smith",
  "status": "CONFIRMED",
  "createdAt": "2026-05-27T15:04:06.782674"
}
```

Error cases:
- 400 Bad Request — missing or invalid fields
- 404 Not Found — flight does not exist
- 409 Conflict — flight is fully booked

---

### 3. Cancel a Booking
Cancels an existing booking and releases the seat back to the flight.

```
DELETE /api/v1/bookings/{bookingId}
```

Response: 204 No Content

Error cases:
- 404 Not Found — booking ID does not exist
- 400 Bad Request — booking is already cancelled

---

## Example curl Requests

```bash
# Create a flight
curl -X POST http://localhost:8080/api/v1/flights \
  -H "Content-Type: application/json" \
  -d "{\"flightNumber\": \"AC101\", \"totalSeats\": 2}"

# Book a seat
curl -X POST http://localhost:8080/api/v1/bookings \
  -H "Content-Type: application/json" \
  -d "{\"flightNumber\": \"AC101\", \"passengerName\": \"John Smith\"}"

# Cancel a booking
curl -X DELETE http://localhost:8080/api/v1/bookings/{bookingId}
```

---

## What I Would Improve With More Time

1. **Granular locking** — Replace `synchronized` on the flight object with
   `ReentrantLock` per flight for better throughput under high concurrency

2. **Idempotency keys** — Add idempotency key support on booking creation
   to safely handle retried requests without duplicate bookings

3. **Booking retrieval endpoint** — Add `GET /api/v1/bookings/{bookingId}`
   so clients can check booking status after creation

4. **Persistent storage** — Replace in-memory maps with PostgreSQL and
   Spring Data JPA for production use

5. **Unit and integration tests** — Add JUnit and Mockito tests for the
   service layer and MockMvc tests for the controllers

6. **Seat number assignment** — Currently we track seat count but do not
   assign specific seat numbers to passengers