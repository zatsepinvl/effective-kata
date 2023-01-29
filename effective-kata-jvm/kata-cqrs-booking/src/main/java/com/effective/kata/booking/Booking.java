package com.effective.kata.booking;

import java.time.Instant;
import java.time.LocalDate;

import static com.effective.kata.booking.InstantUtils.isAfterOrEqual;
import static com.effective.kata.booking.InstantUtils.isBeforeOrEqual;

public record Booking(
        String clientId,
        String roomName,
        LocalDate arrivalDate,
        LocalDate departureDate
) {
    public boolean overlaysWith(Booking booking) {
        return overlaysWith(booking.arrivalDate(), booking.departureDate());
    }

    public boolean overlaysWith(LocalDate arrival, LocalDate departure) {
        return arrival.isBefore(arrivalDate) && departure.isAfter(arrivalDate)
                || arrival.isBefore(departureDate) && departure.isAfter(departureDate)
                || isAfterOrEqual(arrival, arrivalDate) && isBeforeOrEqual(departure, departureDate);
    }
}
