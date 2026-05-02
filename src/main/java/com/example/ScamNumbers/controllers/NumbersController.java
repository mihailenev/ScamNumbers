package com.example.ScamNumbers.controllers;

import com.example.ScamNumbers.db.entities.Number;
import com.example.ScamNumbers.db.repositories.NumberRepository;
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
    public List<Number> findAllNumbers() {
        return numberRepository.findAll();
    }

    @GetMapping("/numbers/{number}")
    public Number findNumber(@PathVariable String number) {
        return numberRepository.findByNumber(number).orElse(null);// TODO: fix laterrrrrrr
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/numbers")
    public Number createNumber(@RequestBody Number number) {
        return numberRepository.save(number);
    }

}
