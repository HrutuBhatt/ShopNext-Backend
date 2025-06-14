package com.project.ecommerce_app.service;

import com.project.ecommerce_app.entity.SearchHistory;
import com.project.ecommerce_app.entity.User;
import com.project.ecommerce_app.repository.SearchHistoryRepository;
import com.project.ecommerce_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchHistoryServiceImpl implements SearchHistoryService{
    @Autowired
    private SearchHistoryRepository searchHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Override

    public List<SearchHistory> getHistoryByUserId(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        return searchHistoryRepository.findByUser(user);
    }

    @Override
    public void removeBySearchId(Integer searchId) {
        searchHistoryRepository.deleteById(searchId);
    }

    @Override
    public SearchHistory addSearchHistory(Integer userId, String searchQuery) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        SearchHistory history = new SearchHistory();
        history.setSearchQuery(searchQuery);
        history.setUser(user);
        return searchHistoryRepository.save(history);
    }
}
