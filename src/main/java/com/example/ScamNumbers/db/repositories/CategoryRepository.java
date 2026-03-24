package com.example.ScamNumbers.db.repositories;

import com.example.ScamNumbers.db.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
