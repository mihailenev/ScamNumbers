package com.example.ScamNumbers.services;

import com.example.ScamNumbers.db.entities.User;
import com.example.ScamNumbers.db.repositories.UserRepository;
import com.example.ScamNumbers.models.UserRequestDto;
import org.apache.coyote.BadRequestException;
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

    public User createUser(UserRequestDto request) throws BadRequestException {

        //find if email exists
        if (userRepository.existsUsersByEmail(request.email()))
            throw new BadRequestException("Users exists");

        String passwordHash = passwordEncoder.encode(request.password());

        //create user
        return userRepository.save(new User(request.email(), passwordHash));
    }
}
