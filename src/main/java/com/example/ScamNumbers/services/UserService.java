package com.example.ScamNumbers.services;

import com.example.ScamNumbers.db.entities.User;
import com.example.ScamNumbers.db.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Boolean doesUserExist(String email) {
        return userRepository.existsUsersByEmail(email);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
