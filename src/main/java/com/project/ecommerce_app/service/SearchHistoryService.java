package com.project.ecommerce_app.service;

import com.project.ecommerce_app.entity.SearchHistory;

import java.util.List;

public interface SearchHistoryService {
    List<SearchHistory> getHistoryByUserId(Integer userId);
    void removeBySearchId(Integer searchId);
    SearchHistory addSearchHistory(Integer userId, String searchQuery);
}
