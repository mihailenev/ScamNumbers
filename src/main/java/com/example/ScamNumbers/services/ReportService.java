package com.example.ScamNumbers.services;

import com.example.ScamNumbers.db.entities.Category;
import com.example.ScamNumbers.db.entities.Number;
import com.example.ScamNumbers.db.entities.Report;
import com.example.ScamNumbers.db.entities.User;
import com.example.ScamNumbers.db.repositories.CategoryRepository;
import com.example.ScamNumbers.db.repositories.NumberRepository;
import com.example.ScamNumbers.db.repositories.ReportRepository;
import com.example.ScamNumbers.db.repositories.UserRepository;
import com.example.ScamNumbers.models.ReportRequestDto;
import com.example.ScamNumbers.models.ReportResponseDto;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    private final UserRepository userRepository;
    private final NumberRepository numberRepository;
    private final CategoryRepository categoryRepository;
    private final ReportRepository reportRepository;

    public ReportService(UserRepository userRepository, NumberRepository numberRepository, CategoryRepository categoryRepository, ReportRepository reportRepository) {
        this.userRepository = userRepository;
        this.numberRepository = numberRepository;
        this.categoryRepository = categoryRepository;
        this.reportRepository = reportRepository;
    }

    public ReportResponseDto createReport(ReportRequestDto request, User user) {

        Number number = numberRepository
                .findByNumber(request.number())
                .orElseGet(() -> numberRepository.save(new Number(request.number())));

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        //user can report number once add delete and add report
        if (reportRepository.existsByUserAndNumber(user, number)) {
            throw new RuntimeException("You have already reported this number");
        }


        Report report = reportRepository.save(new Report(user, number, category));

        return new ReportResponseDto(report.getUser().getEmail(), report.getNumber().getNumber(), report.getCategory().getName(), report.getCreatedAt());
    }

    //private
}
