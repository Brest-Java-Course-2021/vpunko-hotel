package com.epam.brest.dao;

import com.epam.brest.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomDao {

    List<Room> findAll();

    Optional<Room> findById(Integer roomId);

    Integer create(Room room);

    Integer update(Room room);

    Integer delete(Integer roomId);



}
