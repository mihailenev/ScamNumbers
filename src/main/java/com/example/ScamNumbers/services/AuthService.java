package com.example.ScamNumbers.services;

import com.example.ScamNumbers.db.entities.User;
import com.example.ScamNumbers.models.UserRequestDto;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder, JWTService jwtService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String register(UserRequestDto requestDto) throws BadRequestException {

        if (userService.doesUserExist(requestDto.email())) throw new BadRequestException("User exists");

        User registeredUser = userService.saveUser(
                new User(
                        requestDto.email(),
                        passwordEncoder.encode(requestDto.password())
                )
        );
        return jwtService.generateToken(registeredUser);
    }

    public String login(UserRequestDto requestDto) throws BadRequestException {
        //check if user exists
        User user = userService.getUser(requestDto.email());

        if (user == null) throw new BadRequestException("Wrong email or password");

        if (!passwordEncoder.matches(requestDto.password(), user.getPasswordHash()))
            throw new BadRequestException("Wrong email or password");


        return jwtService.generateToken(user);
    }
}
