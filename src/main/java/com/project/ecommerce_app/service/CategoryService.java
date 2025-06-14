package com.project.ecommerce_app.service;

import com.project.ecommerce_app.entity.CartItem;
import com.project.ecommerce_app.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category createCategory(Category category);
    List<Category> getAllCategories();
    Optional<Category> getCategoryById(Integer id);
    Category updateCategory(Integer id, Category category);
    void deleteCategory(Integer id);
}
