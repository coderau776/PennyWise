package com.atharva.pwise.repository;

import com.atharva.pwise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLastNameAndPhone(String lastName, String phone);
}
