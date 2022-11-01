package se331.rest.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.rest.entity.Doctor;
import se331.rest.entity.Patient;
import se331.rest.repository.PatientRepository;

import java.util.Optional;

@Repository
public class PatientDaoImpl implements PatientDao{
    @Autowired
    PatientRepository patientRepository;
    @Override
    public Integer getPatientSize() {
        return Math.toIntExact(patientRepository.count());
    }

    @Override
    public Page<Patient> getPatients(Integer pageSize, Integer page) {
        return patientRepository.findAll(PageRequest.of(page-1,pageSize));
    }

    @Override
    public Patient getPatients(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Page<Patient> getPatients(String name, Pageable page) {
        return patientRepository.findByFirstnameIgnoreCaseContainingOrLastnameIgnoreCaseContaining(name,name,page);
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);

    }
}
