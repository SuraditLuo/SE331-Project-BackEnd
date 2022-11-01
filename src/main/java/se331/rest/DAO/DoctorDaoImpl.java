package se331.rest.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.rest.entity.Doctor;
import se331.rest.entity.Vaccine;
import se331.rest.repository.DoctorRepository;

import java.util.Optional;

@Repository
public class DoctorDaoImpl implements DoctorDao{
    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public Integer getDoctorSize() {
        return Math.toIntExact(doctorRepository.count());
    }

    @Override
    public Page<Doctor> getDoctors(Integer pageSize, Integer page) {
        return doctorRepository.findAll(PageRequest.of(page-1,pageSize));
    }

    @Override
    public Doctor getDoctors(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    /*@Override
    public Page<Doctor> getDoctors(String name, Pageable page) {
        return doctorRepository.findByInCharge_Patient_FirstnameIgnoreCaseContainingOrInCharge_Patient_LastnameIgnoreCaseContaining(name, name, page);
    }*/

    @Override
    public Optional<Doctor> findById(Long id) {
        return doctorRepository.findById(id);

    }
}
