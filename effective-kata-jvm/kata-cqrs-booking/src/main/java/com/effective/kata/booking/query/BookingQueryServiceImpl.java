package com.effective.kata.booking.query;

import com.effective.kata.booking.Booking;
import com.effective.kata.booking.Room;
import com.effective.kata.booking.RoomStorage;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class BookingQueryServiceImpl implements BookingQueryService {
    private final ReadBookingStorage bookingStorage;
    private final RoomStorage roomStorage;

    public BookingQueryServiceImpl(ReadBookingStorage bookingStorage, RoomStorage roomStorage) {
        this.bookingStorage = bookingStorage;
        this.roomStorage = roomStorage;
    }

    @Override
    public Set<Room> getFreeRooms(LocalDate arrival, LocalDate departure) {
        if (arrival.isAfter(departure)) {
            throw new IllegalArgumentException("Invalid date rage provided.");
        }
        Set<String> bookedRoomNames = bookingStorage.getBookings().stream()
                .filter(booking -> booking.overlaysWith(arrival, departure))
                .map(Booking::roomName)
                .collect(Collectors.toSet());
        return roomStorage.getRooms().stream()
                .filter(room -> !bookedRoomNames.contains(room.name()))
                .collect(Collectors.toSet());
    }
}
