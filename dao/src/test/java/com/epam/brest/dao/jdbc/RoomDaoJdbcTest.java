package com.epam.brest.dao.jdbc;

import com.epam.brest.dao.RoomDao;
import com.epam.brest.model.Room;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
public class RoomDaoJdbcTest {

    @Autowired
    private RoomDao roomDao;

    @Test
    public void findAllTest() {

        List<Room> roomList = roomDao.findAll();
        Assert.assertNotNull(roomList);
        Assert.assertTrue(roomList.size() > 0);

    }

    @Test
    public void createRoomTest() {

        boolean isCreateRoom = roomDao.createRoom();
        Assert.assertTrue("Did the room add?", isCreateRoom);

    }
}