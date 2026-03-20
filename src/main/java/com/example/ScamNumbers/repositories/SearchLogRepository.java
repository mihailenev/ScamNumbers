package com.example.ScamNumbers.repositories;

import com.example.ScamNumbers.models.Number;
import com.example.ScamNumbers.models.SearchLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SearchLogRepository extends JpaRepository<SearchLog, UUID> {
    List<SearchLog> findAllByNumberIs(Number number);
}
