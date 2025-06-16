package com.project.ecommerce_app.repository;

import com.project.ecommerce_app.entity.SearchHistory;
import com.project.ecommerce_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Integer> {
    void deleteById(Integer id);

    List<SearchHistory> findByUser(User user);
    Optional<SearchHistory> findByUserAndSearchQuery(User user, String searchQuery);

}
