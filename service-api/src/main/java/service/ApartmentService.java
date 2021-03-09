package service;

import com.epam.brest.model.Apartment;

import java.util.List;
import java.util.Optional;

public interface ApartmentService {

    List<Apartment> findAll();

    Optional<Apartment> findById(Integer roomId);

    Integer create(Apartment apartment);

    Integer update(Apartment apartment);

    Integer delete(Integer roomId);
}
