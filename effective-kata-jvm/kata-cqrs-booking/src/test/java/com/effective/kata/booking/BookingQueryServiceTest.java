package com.effective.kata.booking;

import com.effective.kata.booking.command.BookingCommandService;
import com.effective.kata.booking.command.BookingCommandServiceImpl;
import com.effective.kata.booking.command.WriteBookingStorage;
import com.effective.kata.booking.query.BookingQueryService;
import com.effective.kata.booking.query.BookingQueryServiceImpl;
import com.effective.kata.booking.query.ReadBookingStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.effective.kata.booking.BookingTestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

class BookingQueryServiceTest {

    private BookingCommandService commandService;
    private BookingQueryService queryService;

    private final Room room1 = new Room("room1");
    private final Room room2 = new Room("room2");
    private final Room room3 = new Room("room3");

    @BeforeEach
    void setUp() {
        WriteBookingStorage writeBookingStorage = new WriteBookingStorage();
        ReadBookingStorage readBookingStorage = new ReadBookingStorage();
        writeBookingStorage.subscribe(readBookingStorage);
        RoomStorage roomStorage = new RoomStorage(List.of(room1, room2, room3));

        commandService = new BookingCommandServiceImpl(writeBookingStorage);
        queryService = new BookingQueryServiceImpl(readBookingStorage, roomStorage);
    }

    @Test
    void test_available_rooms_before() {
        // Given
        bookRoom("client1", "room1", 3, 5);

        // When
        var freeRooms = queryService.getFreeRooms(day(1), day(2));

        // Then
        assertThat(freeRooms).containsExactlyInAnyOrder(room1, room2, room3);
    }

    @Test
    void test_available_rooms_after_all() {
        // Given
        bookRoom("client1", "room1", 2, 4);
        bookRoom("client2", "room2", 4, 6);


        // When
        var freeRooms = queryService.getFreeRooms(day(10), day(12));

        // Then
        assertThat(freeRooms).containsExactlyInAnyOrder(room1, room2, room3);
    }

    @Test
    void test_available_rooms_no_rooms() {
        // Given
        bookRoom("client1", "room1", 2, 4);
        bookRoom("client2", "room2", 2, 4);
        bookRoom("client3", "room3", 2, 4);

        // When
        var freeRooms = queryService.getFreeRooms(day(1), day(3));

        // Then
        assertThat(freeRooms).isEmpty();
    }

    @Test
    void test_available_rooms_in_between() {
        // Given
        bookRoom("client1", "room1", 2, 4);
        bookRoom("client2", "room2", 6, 8);

        // When
        var freeRooms = queryService.getFreeRooms(day(4), day(6));

        // Then
        assertThat(freeRooms).containsExactlyInAnyOrder(room1, room2, room3);
    }

    private void bookRoom(String client, String room, int from, int to) {
        commandService.bookRoom(givenBooking(client, room, from, to));
    }

}
