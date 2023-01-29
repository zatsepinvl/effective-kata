package com.effective.kata.booking;

import java.util.Collections;
import java.util.List;

public class RoomStorage {
    private final List<Room> rooms;

    public RoomStorage(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Room> getRooms() {
        return Collections.unmodifiableList(rooms);
    }
}
