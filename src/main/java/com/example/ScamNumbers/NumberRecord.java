package com.example.ScamNumbers;

public record NumberRecord(
        String number,
        String note,
        int numberOfSearches,
        int numberOfReports
) {
}
