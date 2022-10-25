package se331.rest.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se331.rest.entity.Doctor;
import se331.rest.entity.Patient;
import se331.rest.security.dao.UserDao;
import se331.rest.security.entity.Authority;
import se331.rest.security.entity.AuthorityName;
import se331.rest.security.entity.User;
import se331.rest.security.repository.AuthorityRepository;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;
    @Autowired
    AuthorityRepository authorityRepository;
    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    @Transactional
    public User saveRole(User user) {
        Authority authDoctor = Authority.builder().name(AuthorityName.ROLE_DOCTOR).build();
        user.getAuthorities().add(authDoctor);
        authorityRepository.save(authDoctor);
        return userDao.save(user);
    }
}
