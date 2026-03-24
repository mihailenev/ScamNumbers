package com.example.ScamNumbers.services;

import com.example.ScamNumbers.db.entities.User;
import com.example.ScamNumbers.models.UserRequestDto;
import com.example.ScamNumbers.models.UserResponseDto;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto register(UserRequestDto requestDto) throws BadRequestException {

        if (userService.doesUserExist(requestDto.email())) throw new BadRequestException();

        User registeredUser = userService.saveUser(
                new User(
                        requestDto.email(),
                        passwordEncoder.encode(requestDto.password())
                )
        );
        return new UserResponseDto(registeredUser.getId(), registeredUser.getEmail());
    }
}
