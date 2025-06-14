package com.project.ecommerce_app.repository;

import com.project.ecommerce_app.entity.Product;
import com.project.ecommerce_app.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
    List<ProductImage> findByProduct(Product product);
}

