package com.example.ScamNumbers.controllers;

import com.example.ScamNumbers.models.Number;
import com.example.ScamNumbers.repositories.NumberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NumbersController {

    private final NumberRepository numberRepository;

    public NumbersController(NumberRepository numberRepository) {
        this.numberRepository = numberRepository;
    }

    @GetMapping("/numbers")
    public List<com.example.ScamNumbers.models.Number> findAllNumbers() {
        return numberRepository.findAll();
    }

    @GetMapping("/numbers/{number}")
    public com.example.ScamNumbers.models.Number findNumber(@PathVariable String number) {
        return numberRepository.getNumberByNumberIs(number);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/numbers")
    public com.example.ScamNumbers.models.Number createNumber(@RequestBody Number number) {
        return numberRepository.save(number);
    }

}
