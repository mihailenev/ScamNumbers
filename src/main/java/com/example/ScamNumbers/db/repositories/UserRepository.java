package com.example.ScamNumbers.db.repositories;

import com.example.ScamNumbers.db.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findUserByEmailIs(String email);

    Boolean existsUsersByEmail(String email);

    List<User> id(UUID id);
}
