package com.example.ScamNumbers.repositories;

import com.example.ScamNumbers.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findUserByEmailIs(String email);

    List<User> id(UUID id);
}
