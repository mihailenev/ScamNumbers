package com.example.ScamNumbers.controllers;

import com.example.ScamNumbers.db.entities.Number;
import com.example.ScamNumbers.db.entities.SearchLog;
import com.example.ScamNumbers.db.repositories.SearchLogRepository;
import com.example.ScamNumbers.models.SearchNumberResponseDto;
import com.example.ScamNumbers.services.SearchService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class SearchController {

    private final SearchLogRepository repository;
    private final SearchService searchService;

    public SearchController(SearchLogRepository repository, SearchService searchService) {
        this.repository = repository;
        this.searchService = searchService;
    }

    @GetMapping("/search/{number}")
    public SearchNumberResponseDto findLogsByNumber(
            @PathVariable String number
    ) {
        return searchService.getSearchStatsByNumber(number);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/search")
    public SearchLog createSearchLogForANumber(
            @RequestBody Number number
    ) {
        return repository.save(new SearchLog(number));
    }

}
