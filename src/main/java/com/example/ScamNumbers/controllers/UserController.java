package com.example.ScamNumbers.controllers;

import com.example.ScamNumbers.db.entities.User;
import com.example.ScamNumbers.db.repositories.UserRepository;
import com.example.ScamNumbers.models.UserRequestDto;
import com.example.ScamNumbers.services.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
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
            @Valid @RequestBody UserRequestDto request
    ) throws BadRequestException {
        return userService.createUser(request);
    }


    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(
            @PathVariable UUID id
    ) {
        userRepository.deleteById(id);
    }
}
