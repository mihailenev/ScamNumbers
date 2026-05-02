package com.example.ScamNumbers.controllers;

import com.example.ScamNumbers.models.AuthRequestDto;
import com.example.ScamNumbers.models.AuthResponseDto;
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
    public AuthResponseDto registerUser(
            @Valid @RequestBody AuthRequestDto request
    ) throws BadRequestException {
        return authService.register(request); // taka li?
    }

    @PostMapping("/login")
    public AuthResponseDto loginUser(
            @Valid @RequestBody AuthRequestDto request
    ) throws BadRequestException {
        return authService.login(request);
    }

}
