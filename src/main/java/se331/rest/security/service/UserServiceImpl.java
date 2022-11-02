package se331.rest.security.service;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.rest.DAO.DoctorDao;
import se331.rest.entity.Doctor;
import se331.rest.entity.Patient;
import se331.rest.repository.DoctorRepository;
import se331.rest.repository.PatientRepository;
import se331.rest.security.dao.AuthorityDao;
import se331.rest.security.dao.UserDao;
import se331.rest.security.entity.Authority;
import se331.rest.security.entity.AuthorityName;
import se331.rest.security.entity.User;
import se331.rest.security.repository.AuthorityRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;
    @Autowired
    DoctorDao doctorDao;
    @Autowired
    AuthorityDao authorityDao;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PatientRepository patientRepository;
    @Override
    @Transactional
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public Page<User> getUsers(Integer pageSize, Integer page) {
        return userDao.getUsers(pageSize, page);
    }

    @Override
    @Transactional
    public User saveDoctorRole(User user) {
        Authority authority = authorityDao.getAuthority(03L);
        User u = userDao.findById(user.getId()).orElse(null);
        Doctor d = doctorRepository.save(Doctor.builder()
                .firstname(u.getFirstname())
                .lastname(u.getLastname())
                .imageUrls(u.getImageUrls())
                .build());
        u.getAuthorities().add(authority);
        authority.getUsers().add(u);

        u.setDoctor(d);
        d.setUser(user);
        return userDao.save(u);
    }

    @Override
    @Transactional
    public User savePatientRole(User user) {
        Authority authority = authorityDao.getAuthority(04L);
        User u = userDao.findById(user.getId()).orElse(null);
        Patient p = patientRepository.save(Patient.builder()
                .firstname(u.getFirstname())
                .lastname(u.getLastname())
                .age(u.getAge())
                .address(u.getAddress())
                .imageUrls(u.getImageUrls())
                .build());
        u.getAuthorities().add(authority);
        authority.getUsers().add(u);

        u.setPatient(p);
        p.setUser(user);
        return userDao.save(u);
    }

    @Override
    @Transactional
    public User removeDoctor(User user) {
        Authority authority = authorityDao.getAuthority(01L);
        User u = userDao.findById(user.getId()).orElse(null);
        Doctor d = doctorDao.getDoctors(u.getDoctor().getId());
        u.getDoctor().setInCharge(null);
        u.setDoctor(null);
        d.setUser(null);
        doctorRepository.delete(d);
        u.getAuthorities().clear();
        u.getAuthorities().add(authority);
        doctorDao.save(d);
        return userDao.save(u);
    }
}
