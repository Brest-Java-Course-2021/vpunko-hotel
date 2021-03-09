package com.epam.brest.model;

import org.junit.Assert;
import org.junit.Test;

public class ApartmentTest {

    @Test
    public void getRoomNumberConstructor() {
        Apartment apartment = new Apartment(110, 1, "Medium");
        Assert.assertEquals((Integer) 110, apartment.getApartmentNumber());
    }

    @Test
    public void getRoomNumberSetter() {
        Apartment apartment = new Apartment();
        apartment.setApartmentNumber(110);
        Assert.assertEquals((Integer) 110, apartment.getApartmentNumber());
    }
}