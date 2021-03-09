package com.epam.brest.dao.jdbc;

import com.epam.brest.dao.ApartmentDao;
import com.epam.brest.model.Apartment;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
public class ApartmentDaoJdbcTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApartmentDaoJdbcTest.class);

    @Autowired
    private ApartmentDao apartmentDao;

    @Test
    public void findAllTest() {
        List<Apartment> apartmentList = apartmentDao.findAll();
        Assert.assertNotNull(apartmentList);
        Assert.assertTrue(apartmentList.size() > 0);
    }

    @Test
    public void findByIdTest() {
        List<Apartment> apartmentList = apartmentDao.findAll();
        Assert.assertNotNull(apartmentList);
        Assert.assertTrue(apartmentList.size() > 0);

        Integer roomId = apartmentList.get(0).getApartmentId();
        Apartment expertApartment = apartmentDao.findById(roomId).get();
        Assert.assertEquals(roomId, expertApartment.getApartmentId());
        Assert.assertEquals(apartmentList.get(0).getApartmentNumber(), expertApartment.getApartmentNumber());
        Assert.assertEquals(apartmentList.get(0), expertApartment);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void findByIdExceptionTest() {
        Apartment expertApartment = apartmentDao.findById(999).get();
    }

    @Test
    public void createRoomTest(){
        List<Apartment> apartments = apartmentDao.findAll();
        Assert.assertNotNull(apartments);
        Assert.assertTrue(apartments.size()>0);

        apartmentDao.create(new Apartment(103, 2, "Cheap"));

        List<Apartment> realApartment = apartmentDao.findAll();
        Assert.assertEquals(apartments.size() + 1, realApartment.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createRoomWithSameNumberTest(){
        List<Apartment> apartments = apartmentDao.findAll();
        Assert.assertNotNull(apartments);
        Assert.assertTrue(apartments.size()>0);

        apartmentDao.create(new Apartment(102, 2, "Cheap"));
        apartmentDao.create(new Apartment(102, 2, "Cheap"));

//        List<Apartment> realRoom = apartmentDao.findAll();
//        Assert.assertEquals(apartments.size() + 1, realRoom.size());
    }

    @Test
    public void UpdateRoomTest(){
        List<Apartment> apartments = apartmentDao.findAll();
        Assert.assertNotNull(apartments);
        Assert.assertTrue(apartments.size()>0);

        Apartment apartment = apartments.get(0);
        apartment.setCountOfPlaces(1);
        apartmentDao.update(apartment);

        Optional<Apartment> realRoom = apartmentDao.findById(apartment.getApartmentId());
        Assert.assertEquals((Integer) 1,realRoom.get().getCountOfPlaces());
    }

    @Test
    public void deleteRoomTest(){
        List<Apartment> apartments = apartmentDao.findAll();
        Assert.assertNotNull(apartments);
        Assert.assertTrue(apartments.size()>0);

        apartmentDao.delete(apartments.get(0).getApartmentId());

        List<Apartment> realApartment = apartmentDao.findAll();
        Assert.assertEquals(apartments.size() - 1, realApartment.size());
    }


    @Test
    public void testLogging() {
        LOGGER.trace("hello trace");
        LOGGER.debug("hello debug");
        LOGGER.info("hello info");
        LOGGER.warn("hello warn");
        LOGGER.error("hello error");

    }

}