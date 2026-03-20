package com.example.ScamNumbers.controllers;

import com.example.ScamNumbers.models.User;
import com.example.ScamNumbers.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users/{email}")
    public User findUsersById(@PathVariable String email) {
        return userRepository.findUserByEmailIs(email);
    }

    @GetMapping("/users")
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users")
    public User createUser(
            @RequestBody User user
    ) {
        return userRepository.save(user);
    }


    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(
            @PathVariable UUID id
    ) {
        userRepository.deleteById(id);
    }
}
