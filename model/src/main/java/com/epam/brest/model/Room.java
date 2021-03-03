package com.epam.brest.model;

import java.util.Objects;

public class Room {

    private Integer roomId;
    private Integer roomNumber;
    private Integer countOfPlaces;
    private String roomClass;
    //add in future Resident resident;

    public Room() {
    }

    public Room(Integer roomNumber, Integer countOfPlaces, String roomClass) {
        this.roomNumber = roomNumber;
        this.countOfPlaces = countOfPlaces;
        this.roomClass = roomClass;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getCountOfPlaces() {
        return countOfPlaces;
    }

    public void setCountOfPlaces(Integer countOfPlaces) {
        this.countOfPlaces = countOfPlaces;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomNumber=" + roomNumber +
                ", countOfPlaces=" + countOfPlaces +
                ", roomClass='" + roomClass + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomId, room.roomId) && Objects.equals(roomNumber, room.roomNumber) && Objects.equals(countOfPlaces, room.countOfPlaces) && Objects.equals(roomClass, room.roomClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, roomNumber, countOfPlaces, roomClass);
    }
}
