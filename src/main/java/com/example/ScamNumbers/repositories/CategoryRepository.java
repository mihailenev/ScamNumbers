package com.example.ScamNumbers.repositories;

import com.example.ScamNumbers.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
