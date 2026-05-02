package com.example.ScamNumbers.models;

import java.util.Map;

public record SearchNumberResponseDto(
        String number,
        Long searched,
        Map<String, Integer> typeAndNumberReports
) {
}
