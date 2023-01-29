package com.effective.kata.booking;

import java.time.LocalDate;

public final class BookingTestUtils {

    public static Booking givenBooking(int fromDay, int toDay) {
        return givenBooking("client", "room", fromDay, toDay);
    }

    public static Booking givenBooking(String client1, String room1, int fromDate, int toDate) {
        return new Booking(client1, room1, day(fromDate), day(toDate));
    }

    public static LocalDate day(int day) {
        return LocalDate.of(2022, 1, day);
    }
}
