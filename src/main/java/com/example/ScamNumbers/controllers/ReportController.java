package com.example.ScamNumbers.controllers;

import com.example.ScamNumbers.models.Category;
import com.example.ScamNumbers.models.Number;
import com.example.ScamNumbers.models.Report;
import com.example.ScamNumbers.models.User;
import com.example.ScamNumbers.repositories.ReportRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReportController {

    private final ReportRepository repository;

    public ReportController(ReportRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/reports/{number}")
    public List<Report> findReports(
            @PathVariable Number number
    ) {
        return repository.findAllByNumberIs(number);
    }

    @PostMapping("/reports")
    @ResponseStatus(HttpStatus.CREATED)
    public Report createReport(
            @RequestBody User user,
            @RequestBody Number number,
            @RequestBody Category category
    ) {
        return repository.save(new Report(user, number, category));
    }
}
