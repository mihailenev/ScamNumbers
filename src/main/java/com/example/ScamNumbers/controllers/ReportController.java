package com.example.ScamNumbers.controllers;

import com.example.ScamNumbers.db.entities.Number;
import com.example.ScamNumbers.db.entities.Report;
import com.example.ScamNumbers.db.repositories.ReportRepository;
import com.example.ScamNumbers.models.ReportRequestDto;
import com.example.ScamNumbers.models.ReportResponseDto;
import com.example.ScamNumbers.services.ReportService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class ReportController {

    private final ReportService reportService;
    private final ReportRepository repository;

    public ReportController(ReportService reportService, ReportRepository repository) {
        this.reportService = reportService;
        this.repository = repository;
    }

    @GetMapping("/reports/{number}")
    public List<Report> findReports(
            @PathVariable Number number
    ) {
        return repository.findAllByNumberIs(number);
    }

    @GetMapping("/reports")
    public List<Report> findAllReports(
    ) {
        return repository.findAll();
    }

    @PostMapping("/reports")
    @ResponseStatus(HttpStatus.CREATED)
    public ReportResponseDto createReport(
            @Valid @RequestBody ReportRequestDto reportDto
    ) {

        return reportService.createReport(reportDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exp
    ) {
        var errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
