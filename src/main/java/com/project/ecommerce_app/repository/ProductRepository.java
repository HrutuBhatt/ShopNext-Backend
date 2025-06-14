package com.project.ecommerce_app.repository;

import com.project.ecommerce_app.entity.Product;
import com.project.ecommerce_app.entity.User;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByUser(User user);
}
