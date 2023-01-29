package com.effective.kata.booking;

import com.effective.kata.booking.command.BookingCommandService;
import com.effective.kata.booking.command.BookingCommandServiceImpl;
import com.effective.kata.booking.command.WriteBookingStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.effective.kata.booking.BookingTestUtils.givenBooking;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BookingCommandServiceTest {

    private BookingCommandService commandService;

    @BeforeEach
    void setUp() {
        WriteBookingStorage storage = new WriteBookingStorage();
        commandService = new BookingCommandServiceImpl(storage);
    }

    @Test
    void test_same_room_is_available() {
        // Given
        bookRoom("client1", "room1", 3, 5);

        // Then
        bookRoom("client2", "room1", 5, 6);
    }

    @Test
    void test_another_room_is_available() {
        // Given
        bookRoom("client1", "room1", 3, 5);

        // Then
        bookRoom("client2", "room2", 3, 5);
    }

    @Test
    void test_same_room_is_not_available() {
        // Given
        bookRoom("client1", "room1", 1, 3);

        // Then
        assertThatThrownBy(() -> bookRoom("client2", "room1", 1, 2))
                .hasMessageContaining("Room is not available");
    }

    private void bookRoom(String client, String room, int from, int to) {
        commandService.bookRoom(givenBooking(client, room, from, to));
    }
}
