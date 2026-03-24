package com.example.ScamNumbers.controllers;

import com.example.ScamNumbers.models.UserRequestDto;
import com.example.ScamNumbers.models.UserResponseDto;
import com.example.ScamNumbers.services.AuthService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public UserResponseDto registerUser(
            @Valid @RequestBody UserRequestDto request
    ) throws BadRequestException {
        return authService.register(request);
    }

}
