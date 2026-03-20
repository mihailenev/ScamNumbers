package com.example.ScamNumbers.repositories;

import com.example.ScamNumbers.models.Number;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NumberRepository extends JpaRepository<Number, UUID> {
    Number getNumberByNumberIs(String number);
}
