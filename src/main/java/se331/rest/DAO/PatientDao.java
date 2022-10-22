package se331.rest.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.rest.entity.Doctor;
import se331.rest.entity.Patient;

import java.util.Optional;

public interface PatientDao {
    Integer getPatientSize();
    Page<Patient> getPatients(Integer pageSize, Integer page);
    Patient getPatients(Long id);

    Patient save(Patient patient);
    Page<Patient> getPatients(String name, Pageable page);

    Optional<Patient> findById(Long id);
}
