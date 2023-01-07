package com.effective.kata.booking.query;

import com.effective.kata.booking.Booking;
import com.effective.kata.booking.command.BookingSubscriber;

import java.util.ArrayList;
import java.util.List;

public class ReadBookingStorage implements BookingSubscriber {
    private final List<Booking> bookings = new ArrayList<>();

    public List<Booking> getBookings() {
        return bookings;
    }

    @Override
    public void onNewBooking(Booking booking) {
        bookings.add(booking);
    }
}
