package com.example.pathfinder.repository;

import com.example.pathfinder.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
