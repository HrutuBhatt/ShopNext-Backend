package com.project.ecommerce_app.repository;

import com.project.ecommerce_app.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String name); // optional helper
}
