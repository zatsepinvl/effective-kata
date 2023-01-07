package com.effective.kata.booking.command;

import com.effective.kata.booking.Booking;

public class BookingCommandServiceImpl implements BookingCommandService {
    private final WriteBookingStorage bookingStorage;

    public BookingCommandServiceImpl(WriteBookingStorage bookingStorage) {
        this.bookingStorage = bookingStorage;
    }

    @Override
    public void bookRoom(Booking booking) {
        boolean isRoomAvailable = bookingStorage.getBookings().stream()
                .filter(existingBooking -> existingBooking.roomName().equals(booking.roomName()))
                .noneMatch(existingBooking -> existingBooking.overlaysWith(booking));
        if (!isRoomAvailable) {
            throw new IllegalArgumentException("Room is not available for that time.");
        }
        bookingStorage.addBooking(booking);
    }
}
