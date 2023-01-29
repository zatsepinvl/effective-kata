package com.effective.kata.booking.command;

import com.effective.kata.booking.Booking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WriteBookingStorage {
    private final List<Booking> bookings;
    private final List<BookingSubscriber> bookingSubscribers;

    public WriteBookingStorage() {
        this.bookings = new ArrayList<>();
        bookingSubscribers = new ArrayList<>();
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
        bookingSubscribers.forEach(subscriber -> subscriber.onNewBooking(booking));
    }

    public List<Booking> getBookings() {
        return Collections.unmodifiableList(bookings);
    }

    public void subscribe(BookingSubscriber subscriber) {
        bookingSubscribers.add(subscriber);
    }

    public void unsubscribe(BookingSubscriber subscriber) {
        if (bookingSubscribers.contains(subscriber)) {
            bookingSubscribers.remove(subscriber);
        } else {
            throw new IllegalArgumentException("Subscriber not found to delete.");
        }
    }
}
