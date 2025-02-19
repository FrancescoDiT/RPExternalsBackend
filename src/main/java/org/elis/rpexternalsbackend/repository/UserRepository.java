package org.elis.rpexternalsbackend.repository;

import org.elis.rpexternalsbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    String findPasswordByEmail(String email);
}
