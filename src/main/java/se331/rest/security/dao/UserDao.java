package se331.rest.security.dao;


import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import se331.rest.entity.Doctor;
import se331.rest.security.entity.User;

import java.util.Optional;

public interface UserDao {
    User getUser(Long id);
    User save(User user);
    Optional<User> findById(Long id);
    Page<User> getUsers(Integer pageSize, Integer page);
}