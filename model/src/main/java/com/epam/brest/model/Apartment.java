package com.epam.brest.model;

import java.util.Objects;

public class Apartment {

    private Integer apartmentId;
    private Integer apartmentNumber;
    private Integer countOfPlaces;
    private String apartmentClass;
    //add in future Resident resident;

    public Apartment() {
    }

    public Apartment(Integer apartmentNumber, Integer countOfPlaces, String apartmentClass) {
        this.apartmentNumber = apartmentNumber;
        this.countOfPlaces = countOfPlaces;
        this.apartmentClass = apartmentClass;
    }

    public Integer getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Integer apartmentId) {
        this.apartmentId = apartmentId;
    }

    public Integer getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(Integer apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public Integer getCountOfPlaces() {
        return countOfPlaces;
    }

    public void setCountOfPlaces(Integer countOfPlaces) {
        this.countOfPlaces = countOfPlaces;
    }

    public String getApartmentClass() {
        return apartmentClass;
    }

    public void setApartmentClass(String apartmentClass) {
        this.apartmentClass = apartmentClass;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "roomId=" + apartmentId +
                ", roomNumber=" + apartmentNumber +
                ", countOfPlaces=" + countOfPlaces +
                ", roomClass='" + apartmentClass + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return Objects.equals(apartmentId, apartment.apartmentId) && Objects.equals(apartmentNumber, apartment.apartmentNumber) && Objects.equals(countOfPlaces, apartment.countOfPlaces) && Objects.equals(apartmentClass, apartment.apartmentClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apartmentId, apartmentNumber, countOfPlaces, apartmentClass);
    }
}
