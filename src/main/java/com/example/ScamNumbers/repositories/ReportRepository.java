package com.example.ScamNumbers.repositories;

import com.example.ScamNumbers.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReportRepository extends JpaRepository<Report, UUID> {
}
