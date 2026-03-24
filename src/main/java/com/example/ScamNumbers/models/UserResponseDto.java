package com.example.ScamNumbers.models;

import java.util.UUID;

public record UserResponseDto(
        UUID id,
        String email

) {
}
