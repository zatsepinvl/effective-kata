package com.effective.kata.booking.command;

import com.effective.kata.booking.Booking;

public interface BookingSubscriber {
    void onNewBooking(Booking booking);
}
