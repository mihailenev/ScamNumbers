package com.example.ScamNumbers.models;

import java.time.Instant;

public record ReportResponseDto(
        String email,
        String number,
        String categoryName,
        Instant createdAt
) {
}
