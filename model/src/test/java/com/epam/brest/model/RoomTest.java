package com.epam.brest.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoomTest {

    @Test
    public void getRoomNumberConstructor() {
        Room room = new Room(110);
        Assert.assertEquals((Integer) 110, room.getRoomNumber());
    }

    @Test
    public void getRoomNumberSetter() {
        Room room = new Room();
        room.setRoomNumber(110);
        Assert.assertEquals((Integer) 110, room.getRoomNumber());
    }
}