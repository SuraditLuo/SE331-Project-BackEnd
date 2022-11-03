package se331.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.rest.DAO.DoctorDao;
import se331.rest.DAO.DoctorDao;
import se331.rest.entity.Doctor;
import se331.rest.entity.Doctor;
import se331.rest.entity.Doctor;
import se331.rest.entity.Vaccine;
import se331.rest.repository.DoctorRepository;
import se331.rest.security.dao.AuthorityDao;
import se331.rest.security.dao.UserDao;
import se331.rest.security.entity.User;
import se331.rest.security.repository.AuthorityRepository;
import se331.rest.security.repository.UserRepository;

import javax.transaction.Transactional;

@Service
public class DoctorServiceImpl implements DoctorService{
    @Autowired
    DoctorDao doctorDao;
    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    AuthorityDao authorityDao;

    @Autowired
    UserDao userDao;
    @Autowired
    UserRepository userRepository;
    @Override
    public Integer getDoctorSize() {
        return doctorDao.getDoctorSize();
    }

    @Override
    public Page<Doctor> getDoctors(Integer pageSize, Integer page) {
        return doctorDao.getDoctors(pageSize, page);
    }

    @Override
    public Doctor getDoctor(Long id) {
        return doctorDao.getDoctors(id);
    }

    @Override
    @Transactional
    public Doctor save(Doctor doctor) {
        return doctorDao.save(doctor);
    }
    @Override
    @Transactional
    public void removeDoctor(Long id) {
        User u = doctorDao.getDoctors(id).getUser();
        u.getAuthorities().removeIf( e -> authorityDao.getAuthority(03L).equals(e));
        doctorRepository.deleteById(id);
        userRepository.save(u);
    }
}
