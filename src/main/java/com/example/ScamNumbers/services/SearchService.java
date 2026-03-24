package com.example.ScamNumbers.services;

import com.example.ScamNumbers.db.entities.Number;
import com.example.ScamNumbers.db.entities.Report;
import com.example.ScamNumbers.db.entities.SearchLog;
import com.example.ScamNumbers.db.repositories.NumberRepository;
import com.example.ScamNumbers.db.repositories.ReportRepository;
import com.example.ScamNumbers.db.repositories.SearchLogRepository;
import com.example.ScamNumbers.models.SearchNumberResponseDto;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class SearchService {

    private final SearchLogRepository searchLogRepository;
    private final NumberRepository numberRepository;
    private final ReportRepository reportRepository;

    public SearchService(SearchLogRepository searchLogRepository, NumberRepository numberRepository, ReportRepository reportRepository) {
        this.searchLogRepository = searchLogRepository;
        this.numberRepository = numberRepository;
        this.reportRepository = reportRepository;
    }

    public SearchNumberResponseDto getSearchStatsByNumber(String numberStr) {

        String numberNormalized = numberStr.trim().replaceAll("\\s", "");

        Optional<Number> optionalNumber = numberRepository.findByNumber(numberNormalized);

        if (optionalNumber.isEmpty()) {
            Number newNumber = numberRepository.save(new Number(numberNormalized));
            searchLogRepository.save(new SearchLog(newNumber));

            return new SearchNumberResponseDto(
                    newNumber.getNumber(),
                    0L,
                    new HashMap<>()
            );
        }

        Number searchedNumber = optionalNumber.get();

        Instant thirtyDaysAgo = Instant.now().minus(30, ChronoUnit.DAYS);
        Long searches = searchLogRepository.countByNumberAndSearchedAtAfter(searchedNumber, thirtyDaysAgo);


        List<Report> reports = reportRepository.findAllByNumberIs(searchedNumber);
        HashMap<String, Integer> reps = new HashMap<>();
        reports.forEach(report -> {
            String category = report.getCategory().getName();
            reps.merge(category, 1, Integer::sum);
        });

        searchLogRepository.save(new SearchLog(searchedNumber));

        return new SearchNumberResponseDto(searchedNumber.getNumber(), searches, reps);

    }
}
