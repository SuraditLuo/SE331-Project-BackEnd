package se331.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.rest.DAO.VaccineDao;
import se331.rest.entity.Vaccine;

import java.util.List;
@Service
public class VaccineServiceImpl implements VaccineService{
    @Autowired
    VaccineDao vaccineDao;

    @Override
    public List<Vaccine> getAllVaccine() {
        return vaccineDao.getVaccine(Pageable.unpaged()).getContent();
    }
}
