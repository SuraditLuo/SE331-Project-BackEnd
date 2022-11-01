package se331.rest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import se331.rest.entity.Patient;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findAll();
    Page<Patient> findByFirstnameIgnoreCaseContainingOrLastnameIgnoreCaseContaining(String firstName, String lastName, Pageable pageRequest);
}
