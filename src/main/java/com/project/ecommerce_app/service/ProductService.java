package com.project.ecommerce_app.service;

import com.project.ecommerce_app.entity.Product;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> getAllProducts();
    Optional<Product> getProductById(Integer id);
    Optional<Product> updateProduct(Integer id, Product product);
    void deleteProduct(Integer id);
    List<Product> getProductByUserId(Integer userId);
}
