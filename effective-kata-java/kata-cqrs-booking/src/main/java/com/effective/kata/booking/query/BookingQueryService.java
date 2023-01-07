package com.effective.kata.booking.query;

import com.effective.kata.booking.Room;

import java.time.LocalDate;
import java.util.Set;

public interface BookingQueryService {
    Set<Room> getFreeRooms(LocalDate arrival, LocalDate departure);
}
