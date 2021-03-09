package impl;

import com.epam.brest.dao.ApartmentDao;
import com.epam.brest.model.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.ApartmentService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ApartmentServiceImpl implements ApartmentService {

//    private static final Logger LOGGER = LoggerFactory.getLogger(ApartmentServiceImpl.class);

    private final ApartmentDao apartmentDao;

    @Autowired
    public ApartmentServiceImpl(ApartmentDao apartmentDao) {
        this.apartmentDao = apartmentDao;
    }

    @Override
    public List<Apartment> findAll() {
        return apartmentDao.findAll();
    }

    @Override
    public Optional<Apartment> findById(Integer apartmentId) {
        return apartmentDao.findById(apartmentId);
    }

    @Override
    public Integer create(Apartment apartment) {
        return apartmentDao.create(apartment);
    }

    @Override
    public Integer update(Apartment apartment) {
        return apartmentDao.update(apartment);
    }

    @Override
    public Integer delete(Integer apartmentId) {
        return apartmentDao.delete(apartmentId);
    }
}
