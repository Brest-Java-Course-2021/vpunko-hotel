package com.epam.brest.dao.jdbc;

import com.epam.brest.dao.RoomDao;
import com.epam.brest.model.Room;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RoomDaoJdbc implements RoomDao {

    private static final String SQL_GET_ALL_ROOMS = "select * from ROOM as R ORDER BY R.Room_Number";
    private static final String SQL_GET_BY_ID_ROOMS = "select * from ROOM as R where R.ROOM_ID = :ROOM_ID";
    private static final String SQL_CREATE_ROOMS = "INSERT INTO ROOM (Room_Number, Count_Of_Places, Room_Class ) VALUES (?, ?, ? )";

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    RowMapper rowMapper =  BeanPropertyRowMapper.newInstance(Room.class);

    public RoomDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public List<Room> findAll() {
        return namedParameterJdbcTemplate.query(SQL_GET_ALL_ROOMS, rowMapper);
    }

    @Override
    public Optional<Room> findById(Integer roomId) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("ROOM_ID", roomId);
        return Optional.ofNullable((Room) namedParameterJdbcTemplate.queryForObject(SQL_GET_BY_ID_ROOMS,
                sqlParameterSource, rowMapper));
    }

    @Override
    public Integer create(Room room) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("Room_Number, Count_Of_Places, Room_Class", room);
        return null;
    }

    @Override
    public Integer update(Room room) {
        return null;
    }

    @Override
    public Integer delete(Integer roomId) {
        return null;
    }
}
