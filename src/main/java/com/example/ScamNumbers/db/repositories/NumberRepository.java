package com.example.ScamNumbers.db.repositories;

import com.example.ScamNumbers.db.entities.Number;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface NumberRepository extends JpaRepository<Number, UUID> {
    Optional<Number> findByNumber(String number);

    Number findByNumberIs(String number);
}
