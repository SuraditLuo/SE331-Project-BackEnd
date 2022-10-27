package se331.rest.security.service;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.rest.entity.Doctor;
import se331.rest.entity.Patient;
import se331.rest.repository.DoctorRepository;
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
    AuthorityDao authorityDao;
    @Autowired
    DoctorRepository doctorRepository;
    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public Page<User> getUsers(Integer pageSize, Integer page) {
        return userDao.getUsers(pageSize, page);
    }

    @Override
    @Transactional
    public User saveRole(User user) {
        Authority authority = authorityDao.getAuthority(03L);
        User u = userDao.findById(user.getId()).orElse(null);
        Doctor d = doctorRepository.save(Doctor.builder()
                .name(u.getUsername()).build());
        u.getAuthorities().add(authority);
        authority.getUsers().add(u);

        u.setDoctor(d);
        d.setUser(user);
        return userDao.save(u);
    }
}
