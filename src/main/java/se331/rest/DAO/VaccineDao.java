package se331.rest.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.rest.entity.Vaccine;

import java.util.Optional;

public interface VaccineDao {
    Page<Vaccine> getVaccine(Pageable pageRequest);
    Optional<Vaccine> findById(Long id);
}
