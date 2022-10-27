package se331.rest.security.repository;



import org.springframework.data.repository.CrudRepository;
import se331.rest.entity.Doctor;
import se331.rest.security.entity.Authority;
import se331.rest.security.entity.AuthorityName;

import java.util.List;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {
    List<Authority> findAll();
}
