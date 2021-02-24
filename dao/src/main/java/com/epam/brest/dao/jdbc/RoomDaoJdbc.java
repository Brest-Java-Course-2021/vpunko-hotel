package com.epam.brest.dao.jdbc;

import com.epam.brest.dao.RoomDao;
import com.epam.brest.model.Room;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoomDaoJdbc implements RoomDao {

    private static final String SQL_GET_ALL_ROOMS = "select * from ROOM as R ORDER BY R.Room_Number";
    private static final String SQL_CREATE_NEW_ROOM = "INSERT INTO ROOM (Room_Number, Count_Of_Places, Room_Class ) VALUES (?, ?, ?)";

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public RoomDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public boolean createRoom() {
        namedParameterJdbcTemplate.query(SQL_CREATE_NEW_ROOM, new CreateRoomRowMapper());
        return true;
    }

    private class CreateRoomRowMapper implements RowMapper<Room> {
        @Override
        public Room mapRow(ResultSet resultSet, int i) throws SQLException {
            Room room = new Room();
            room.setRoomNumber(110);
            room.setCountOfPlaces(1);
            room.setRoomClass("Cheap");

            return room;
        }

    }

    @Override
    public List<Room> findAll() {
        return namedParameterJdbcTemplate.query(SQL_GET_ALL_ROOMS, new RoomRowMapper());
    }

    private class RoomRowMapper implements RowMapper<Room> {
        @Override
        public Room mapRow(ResultSet resultSet, int i) throws SQLException {
            Room room = new Room();
            room.setRoomId(resultSet.getInt("Room_Id"));
            room.setRoomNumber(resultSet.getInt("Room_Number"));
            room.setCountOfPlaces(resultSet.getInt("Count_Of_Places"));
            room.setRoomClass(resultSet.getString("Room_Class"));

            return room;
        }

    }
}
