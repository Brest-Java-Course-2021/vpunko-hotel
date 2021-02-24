package com.epam.brest.dao;

import com.epam.brest.model.Room;

import java.util.List;

public interface RoomDao {

    List<Room> findAll();
    boolean createRoom();


}
