package com.example.ScamNumbers.db.repositories;

import com.example.ScamNumbers.db.entities.Number;
import com.example.ScamNumbers.db.entities.SearchLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface SearchLogRepository extends JpaRepository<SearchLog, UUID> {
    List<SearchLog> findAllByNumberIs(Number number);

    Long countByNumberAndSearchedAtAfter(Number number, Instant searchedAtAfter);

    Long countByNumber(Number number);
}
