package com.epam.brest.dao.jdbc;

import com.epam.brest.dao.RoomDao;
import com.epam.brest.model.Room;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;


import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
public class RoomDaoJdbcTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomDaoJdbcTest.class);

    @Autowired
    private RoomDao roomDao;

    @Test
    public void findAllTest() {
        List<Room> roomList = roomDao.findAll();
        Assert.assertNotNull(roomList);
        Assert.assertTrue(roomList.size() > 0);
    }

    @Test
    public void findByIdTest() {
        List<Room> roomList = roomDao.findAll();
        Assert.assertNotNull(roomList);
        Assert.assertTrue(roomList.size() > 0);

        Integer roomId = roomList.get(0).getRoomId();
        Room expertRoom = roomDao.findById(roomId).get();
        Assert.assertEquals(roomId, expertRoom.getRoomId());
        Assert.assertEquals(roomList.get(0).getRoomNumber(), expertRoom.getRoomNumber());
        Assert.assertEquals(roomList.get(0), expertRoom);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void findByIdExceptionTest() {
        Room expertRoom = roomDao.findById(999).get();
    }

    @Test
    public void createRoomTest(){
        List<Room> rooms = roomDao.findAll();
        Assert.assertNotNull(rooms);
        Assert.assertTrue(rooms.size()>0);

        roomDao.create(new Room(103, 2, "Cheap"));

        List<Room> realRoom = roomDao.findAll();
        Assert.assertEquals(rooms.size() + 1, realRoom.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createRoomWithSameNumberTest(){
        List<Room> rooms = roomDao.findAll();
        Assert.assertNotNull(rooms);
        Assert.assertTrue(rooms.size()>0);

        roomDao.create(new Room(102, 2, "Cheap"));
        roomDao.create(new Room(102, 2, "Cheap"));

//        List<Room> realRoom = roomDao.findAll();
//        Assert.assertEquals(rooms.size() + 1, realRoom.size());
    }

    @Test
    public void UpdateRoomTest(){
        List<Room> rooms = roomDao.findAll();
        Assert.assertNotNull(rooms);
        Assert.assertTrue(rooms.size()>0);

        Room room = rooms.get(0);
        room.setCountOfPlaces(1);
        roomDao.update(room);

        Optional<Room> realRoom = roomDao.findById(room.getRoomId());
        Assert.assertEquals((Integer) 1,realRoom.get().getCountOfPlaces());
    }

    @Test
    public void deleteRoomTest(){
        List<Room> rooms = roomDao.findAll();
        Assert.assertNotNull(rooms);
        Assert.assertTrue(rooms.size()>0);

        roomDao.delete(rooms.get(0).getRoomId());

        List<Room> realRoom = roomDao.findAll();
        Assert.assertEquals(rooms.size() - 1, realRoom.size());
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