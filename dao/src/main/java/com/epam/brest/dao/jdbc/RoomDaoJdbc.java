package com.epam.brest.dao.jdbc;

import com.epam.brest.dao.RoomDao;
import com.epam.brest.model.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RoomDaoJdbc implements RoomDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomDaoJdbc.class);

    private static final String SQL_GET_ALL_ROOMS = "select * from ROOM as R ORDER BY R.ROOM_NUMBER";
    private static final String SQL_GET_BY_ID_ROOMS = "select * from ROOM as R where R.ROOM_ID = :ROOM_ID";
    private static final String SQL_CREATE_ROOMS = "INSERT INTO ROOM (ROOM_NUMBER, COUNT_OF_PLACES, ROOM_CLASS ) " +
            "VALUES (:ROOM_NUMBER, :COUNT_OF_PLACES, :ROOM_CLASS)";
    private static final String SQL_CHECK_ROOM_NUMBER = "select COUNT(ROOM_ID) from ROOM WHERE " +
            "ROOM_NUMBER = :ROOM_NUMBER";
    private static final String SQL_UPDATE_ROOMS = "UPDATE ROOM " +
            "SET COUNT_OF_PLACES = :COUNT_OF_PLACES WHERE ROOM_ID = :ROOM_ID";
    private static final String SQL_DELETE_ROOMS = "DELETE FROM ROOM WHERE ROOM_ID = :ROOM_ID";



    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    RowMapper rowMapper = BeanPropertyRowMapper.newInstance(Room.class);

    public RoomDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public List<Room> findAll() {
        LOGGER.debug("Find all rooms");
        return namedParameterJdbcTemplate.query(SQL_GET_ALL_ROOMS, rowMapper);
    }

    @Override
    public Optional<Room> findById(Integer roomId) {
        LOGGER.debug("Find room by id: {}", roomId);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("ROOM_ID", roomId);
        return Optional.ofNullable((Room) namedParameterJdbcTemplate.queryForObject(SQL_GET_BY_ID_ROOMS,
                sqlParameterSource, rowMapper));
    }

    @Override
    public Integer create(Room room) {
        long startTime = System.nanoTime();
        LOGGER.debug("Create room: {}", room);
        if(!isRoomNumberUnique(room)) {
            LOGGER.warn("Room with the same name already exists in DB: {}", room);
            throw new IllegalArgumentException("Room with the same number that already exist in DB");
        }
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("ROOM_NUMBER", room.getRoomNumber())
                        .addValue("COUNT_OF_PLACES", room.getCountOfPlaces())
                        .addValue("ROOM_CLASS", room.getRoomClass());
        namedParameterJdbcTemplate.update(SQL_CREATE_ROOMS, sqlParameterSource, keyHolder);
        Integer roomId = Objects.requireNonNull(keyHolder.getKey()).intValue();
        room.setRoomId(roomId);
        long endTime = System.nanoTime();
        LOGGER.debug("Execution time: {}", endTime - startTime);
        return roomId;
    }

    private boolean isRoomNumberUnique(Room room) {
        return namedParameterJdbcTemplate.queryForObject(SQL_CHECK_ROOM_NUMBER
                , new MapSqlParameterSource("ROOM_NUMBER", room.getRoomNumber()), Integer.class) == 0;
    }

    @Override
    public Integer update(Room room) {
        LOGGER.debug("Update room: {}", room);
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("COUNT_OF_PLACES", room.getCountOfPlaces())
                .addValue("ROOM_ID", room.getRoomId());
        return namedParameterJdbcTemplate.update(SQL_UPDATE_ROOMS, sqlParameterSource);
    }

    @Override
    public Integer delete(Integer roomId) {
        LOGGER.debug("Delete room: {}", roomId);
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("ROOM_ID", roomId);
        return namedParameterJdbcTemplate.update(SQL_DELETE_ROOMS, sqlParameterSource);
    }
}
