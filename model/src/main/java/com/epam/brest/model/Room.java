package com.epam.brest.model;

public class Room {

    private Integer roomId;
    private Integer roomNumber;
    private Integer countOfPlaces;
    private String roomClass;
    //add in future Resident resident;

    public Room() {
    }

    public Room(Integer roomNumber) {
        this.roomNumber = roomNumber;
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
}
