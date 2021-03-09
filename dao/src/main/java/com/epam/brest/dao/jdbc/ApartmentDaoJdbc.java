package com.epam.brest.dao.jdbc;

import com.epam.brest.dao.ApartmentDao;
import com.epam.brest.model.Apartment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ApartmentDaoJdbc implements ApartmentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApartmentDaoJdbc.class);

    private static final String SQL_GET_ALL_APARTMENTS = "select * from APARTMENT as A ORDER BY A.APARTMENT_NUMBER";
    private static final String SQL_GET_BY_ID_APARTMENTS = "select * from APARTMENT as A where A.APARTMENT_ID = :APARTMENT_ID";
    private static final String SQL_CREATE_APARTMENTS = "INSERT INTO APARTMENT (APARTMENT_NUMBER, COUNT_OF_PLACES, APARTMENT_CLASS ) " +
            "VALUES (:APARTMENT_NUMBER, :COUNT_OF_PLACES, :APARTMENT_CLASS)";
    private static final String SQL_CHECK_APARTMENT_NUMBER = "select COUNT(APARTMENT_ID) from APARTMENT WHERE " +
            "APARTMENT_NUMBER = :APARTMENT_NUMBER";
    private static final String SQL_UPDATE_APARTMENTS = "UPDATE APARTMENT " +
            "SET COUNT_OF_PLACES = :COUNT_OF_PLACES WHERE APARTMENT_ID = :APARTMENT_ID";
    private static final String SQL_DELETE_APARTMENTS = "DELETE FROM APARTMENT WHERE APARTMENT_ID = :APARTMENT_ID";



    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    RowMapper rowMapper = BeanPropertyRowMapper.newInstance(Apartment.class);

    public ApartmentDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public List<Apartment> findAll() {
        LOGGER.debug("Find all rooms");
        return namedParameterJdbcTemplate.query(SQL_GET_ALL_APARTMENTS, rowMapper);
    }

    @Override
    public Optional<Apartment> findById(Integer apartmentId) {
        LOGGER.debug("Find apartment by id: {}", apartmentId);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("APARTMENT_ID", apartmentId);
        return Optional.ofNullable((Apartment) namedParameterJdbcTemplate.queryForObject(SQL_GET_BY_ID_APARTMENTS,
                sqlParameterSource, rowMapper));
    }

    @Override
    public Integer create(Apartment apartment) {
        long startTime = System.nanoTime();
        LOGGER.debug("Create apartment: {}", apartment);
        if(!isApartmentNumberUnique(apartment)) {
            LOGGER.warn("Apartment with the same name already exists in DB: {}", apartment);
            throw new IllegalArgumentException("Apartment with the same number that already exist in DB");
        }
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("APARTMENT_NUMBER", apartment.getApartmentNumber())
                        .addValue("COUNT_OF_PLACES", apartment.getCountOfPlaces())
                        .addValue("APARTMENT_CLASS", apartment.getApartmentClass());
        namedParameterJdbcTemplate.update(SQL_CREATE_APARTMENTS, sqlParameterSource, keyHolder);
        Integer apartmentId = Objects.requireNonNull(keyHolder.getKey()).intValue();
        apartment.setApartmentId(apartmentId);
        long endTime = System.nanoTime();
        LOGGER.debug("Execution time: {}", endTime - startTime);
        return apartmentId;
    }

    private boolean isApartmentNumberUnique(Apartment apartment) {
        return namedParameterJdbcTemplate.queryForObject(SQL_CHECK_APARTMENT_NUMBER
                , new MapSqlParameterSource("APARTMENT_NUMBER", apartment.getApartmentNumber()), Integer.class) == 0;
    }

    @Override
    public Integer update(Apartment apartment) {
        LOGGER.debug("Update apartment: {}", apartment);
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("COUNT_OF_PLACES", apartment.getCountOfPlaces())
                .addValue("APARTMENT_ID", apartment.getApartmentId());
        return namedParameterJdbcTemplate.update(SQL_UPDATE_APARTMENTS, sqlParameterSource);
    }

    @Override
    public Integer delete(Integer apartmentId) {
        LOGGER.debug("Delete apartment: {}", apartmentId);
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("APARTMENT_ID", apartmentId);
        return namedParameterJdbcTemplate.update(SQL_DELETE_APARTMENTS, sqlParameterSource);
    }
}
