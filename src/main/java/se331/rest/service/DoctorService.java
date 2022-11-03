package se331.rest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.rest.entity.Doctor;
import se331.rest.entity.Doctor;

public interface DoctorService {
    Integer getDoctorSize();

    Page<Doctor> getDoctors(Integer pageSize, Integer page);

    Doctor getDoctor(Long id);

    Doctor save(Doctor doctor);
    /*Page<Doctor> getDoctors(String name, Pageable pageable);*/

    void removeDoctor(Long id);
}
