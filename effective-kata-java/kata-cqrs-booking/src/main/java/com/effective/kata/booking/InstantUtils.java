package com.effective.kata.booking;

import java.time.LocalDate;

public final class InstantUtils {
    public static boolean isBeforeOrEqual(LocalDate one, LocalDate two) {
        return one.equals(two) || one.isBefore(two);
    }

    public static boolean isAfterOrEqual(LocalDate one, LocalDate two) {
        return one.equals(two) || one.isAfter(two);
    }
}
