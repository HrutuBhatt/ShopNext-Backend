package com.project.ecommerce_app.repository;

import com.project.ecommerce_app.entity.SellerStats;
import com.project.ecommerce_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SellerStatsRepository extends JpaRepository<SellerStats, Integer> {
    Optional<SellerStats> findByUser(User user);
}
