package se331.rest.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.rest.entity.Vaccine;
import se331.rest.repository.VaccineRepository;

import java.util.Optional;

@Repository
public class VaccineDaoImpl implements VaccineDao{
    @Autowired
    VaccineRepository vaccineRepository;

    @Override
    public Page<Vaccine> getVaccine(Pageable pageRequest) {
        return vaccineRepository.findAll(pageRequest);
    }

    @Override
    public Optional<Vaccine> findById(Long id) {
        return vaccineRepository.findById(id);

    }
}
