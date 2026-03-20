package com.example.ScamNumbers.seeds;

import com.example.ScamNumbers.models.Category;
import com.example.ScamNumbers.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner seedCategories(CategoryRepository categoryRepository) {
        return args -> {

            // Check if already seeded
            if (categoryRepository.count() == 0) {
                categoryRepository.saveAll(
                        java.util.List.of(
                                new Category("Scam"),
                                new Category("Telemarketing"),
                                new Category("Robocall"),
                                new Category("Phishing"),
                                new Category("Debt Collection"),
                                new Category("Harassment"),
                                new Category("Suspicious")
                        )
                );
                System.out.println("[+] Categories seeded!");
            } else {
                System.out.println("[!] Categories already exist, skipping seeding.");
            }
        };
    }
}
