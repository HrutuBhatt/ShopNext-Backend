package com.project.ecommerce_app.repository;

import com.project.ecommerce_app.entity.Feedback;
import com.project.ecommerce_app.entity.Product;
import com.project.ecommerce_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    Optional<Feedback> findByUserAndProduct(User user, Product product);
    List<Feedback> findByProduct(Product product);
}
