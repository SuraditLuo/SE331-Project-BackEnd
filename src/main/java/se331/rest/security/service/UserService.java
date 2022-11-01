package se331.rest.security.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.rest.security.entity.User;

import java.util.List;

public interface UserService {
    User save(User user);
    Page<User> getUsers(Integer pageSize, Integer page);

    User saveDoctorRole(User user);

    User savePatientRole(User user);
    User removeDoctor(User user);
}
