package se331.rest.security.dao;


import se331.rest.entity.Patient;
import se331.rest.security.entity.Authority;

import java.util.Optional;

public interface AuthorityDao {
    Authority getAuthority(Long id);
}