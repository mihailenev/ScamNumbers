package com.example.ScamNumbers.db.repositories;

import com.example.ScamNumbers.db.entities.Number;
import com.example.ScamNumbers.db.entities.Report;
import com.example.ScamNumbers.db.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReportRepository extends JpaRepository<Report, UUID> {
    List<Report> findAllByNumberIs(Number number);

    boolean existsByUserAndNumber(User user, Number number);
}
