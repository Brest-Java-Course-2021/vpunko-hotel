package com.epam.brest.dao.jdbc;

import com.epam.brest.dao.RoomDao;
import com.epam.brest.model.Room;
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
    public void testLogging() {
        LOGGER.trace("hello trace");
        LOGGER.debug("hello debug");
        LOGGER.info("hello info");
        LOGGER.warn("hello warn");
        LOGGER.error("hello error");

    }

}