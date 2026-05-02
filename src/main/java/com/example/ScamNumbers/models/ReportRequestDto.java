package com.example.ScamNumbers.models;

import jakarta.validation.constraints.NotNull;

public record ReportRequestDto(
        @NotNull
        String number,
        @NotNull(message = "Category should not be empty")
        Integer categoryId
) {
}
