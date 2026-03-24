package com.example.ScamNumbers.models;

import java.util.HashMap;

public record SearchNumberResponseDto(
        String number,
        Long searched,
        HashMap<String, Integer> typeAndNumberReports
) {
}
