package com.example.ScamNumbers.seeds;

import com.example.ScamNumbers.db.entities.Category;
import com.example.ScamNumbers.db.repositories.CategoryRepository;
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
                                new Category("scam"),
                                new Category("telemarketing"),
                                new Category("robocall"),
                                new Category("phishing"),
                                new Category("debtCollection"),
                                new Category("harassment"),
                                new Category("suspicious")
                        )
                );
                System.out.println("[+] Categories seeded!");
            } else {
                System.out.println("[!] Categories already exist, skipping seeding.");
            }
        };
    }
}
