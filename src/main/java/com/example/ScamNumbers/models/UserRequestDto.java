package com.example.ScamNumbers.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @Size(max = 255)
        String email,
        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 255, message = "Password must be at least 8 characters")
        String password
) {
}
