package se331.rest.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.rest.entity.Doctor;

import java.util.Optional;

public interface DoctorDao {
    Integer getDoctorSize();
    Page<Doctor> getDoctors(Integer pageSize, Integer page);
    Doctor getDoctors(Long id);

    Doctor save(Doctor doctor);
    /*Page<Doctor> getDoctors(String name, Pageable page);*/
    Optional<Doctor> findById(Long id);
}
