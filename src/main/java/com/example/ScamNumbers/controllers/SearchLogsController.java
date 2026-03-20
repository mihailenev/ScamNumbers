package com.example.ScamNumbers.controllers;

import com.example.ScamNumbers.models.Number;
import com.example.ScamNumbers.models.SearchLog;
import com.example.ScamNumbers.repositories.SearchLogRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SearchLogsController {

    private final SearchLogRepository repository;

    public SearchLogsController(SearchLogRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/search/{number}")
    public List<SearchLog> findLogsByNumber(
            @PathVariable Number number
    ) {
        return repository.findAllByNumberIs(number);
    }

    @PostMapping("/search")
    public SearchLog createSearchLogForANumber(
            @RequestBody Number number
    ) {
        return repository.save(new SearchLog(number));
    }

}
