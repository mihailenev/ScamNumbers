package com.example.ScamNumbers.services;

import com.example.ScamNumbers.db.entities.User;
import com.example.ScamNumbers.db.repositories.UserRepository;
import com.example.ScamNumbers.models.AuthRequestDto;
import com.example.ScamNumbers.models.AuthResponseDto;
import com.example.ScamNumbers.models.UserResponseDto;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder, JWTService jwtService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponseDto register(AuthRequestDto requestDto) throws BadRequestException {

        if (userService.doesUserExist(requestDto.email())) throw new BadRequestException("User exists");

        User registeredUser = userService.saveUser(
                new User(
                        requestDto.email(),
                        passwordEncoder.encode(requestDto.password())
                )
        );
        String token = jwtService.generateToken(registeredUser);
        return new AuthResponseDto(token, new UserResponseDto(registeredUser.getId(), registeredUser.getEmail()));
    }

    public AuthResponseDto login(AuthRequestDto requestDto) throws BadRequestException {
        User user = userRepository.findUserByEmailIs(requestDto.email());

        if (user == null) throw new BadRequestException("Wrong email or password");

        if (!passwordEncoder.matches(requestDto.password(), user.getPasswordHash()))
            throw new BadRequestException("Wrong email or password");


        String token = jwtService.generateToken(user);
        return new AuthResponseDto(token, new UserResponseDto(user.getId(), user.getEmail()));
    }
}
