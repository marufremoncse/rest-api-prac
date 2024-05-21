package org.codingsense.restapiprac.repository;

import org.codingsense.restapiprac.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
