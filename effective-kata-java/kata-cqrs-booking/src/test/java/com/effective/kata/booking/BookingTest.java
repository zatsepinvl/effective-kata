package com.effective.kata.booking;

import org.junit.jupiter.api.Test;


import static com.effective.kata.booking.BookingTestUtils.givenBooking;
import static org.assertj.core.api.Assertions.assertThat;

class BookingTest {

    @Test
    void test_overlays_before() {
        // Given
        var booking1 = givenBooking(2, 4);
        var booking2 = givenBooking(1, 3);

        // Then
        assertThat(booking1.overlaysWith(booking2)).isTrue();
    }

    @Test
    void test_overlays_after() {
        // Given
        var booking1 = givenBooking(2, 4);
        var booking2 = givenBooking(3, 5);

        // Then
        assertThat(booking1.overlaysWith(booking2)).isTrue();
    }

    @Test
    void test_overlays_in_between() {
        // Given
        var booking1 = givenBooking(2, 6);
        var booking2 = givenBooking(3, 5);

        // Then
        assertThat(booking1.overlaysWith(booking2)).isTrue();
    }

    @Test
    void test_no_overlay_before() {
        // Given
        var booking1 = givenBooking(2, 3);
        var booking2 = givenBooking(1, 2);

        // Then
        assertThat(booking1.overlaysWith(booking2)).isFalse();
    }

    @Test
    void test_no_overlay_after() {
        // Given
        var booking1 = givenBooking(1, 2);
        var booking2 = givenBooking(2, 3);

        // Then
        assertThat(booking1.overlaysWith(booking2)).isFalse();
    }

    @Test
    void test_overlay() {
        // Given
        var booking1 = givenBooking(1, 3);
        var booking2 = givenBooking(1, 2);

        // Then
        assertThat(booking1.overlaysWith(booking2)).isTrue();
    }

}
