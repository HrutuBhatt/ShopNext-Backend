package com.project.ecommerce_app.controller;

import com.project.ecommerce_app.entity.SearchHistory;
import com.project.ecommerce_app.service.SearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search-history")
public class SearchHistoryController {

    @Autowired
    private SearchHistoryService searchHistoryService;

    @GetMapping("/user/{userId}")
    public List<SearchHistory> getHistoryByUserId(@PathVariable Integer userId) {
        return searchHistoryService.getHistoryByUserId(userId);
    }

    @DeleteMapping("/{searchId}")
    public void removeBySearchId(@PathVariable Integer searchId) {
        searchHistoryService.removeBySearchId(searchId);
    }

    @PostMapping("/add")
    public SearchHistory addSearchHistory(
            @RequestParam Integer userId,
            @RequestParam String searchQuery) {
        return searchHistoryService.addSearchHistory(userId, searchQuery);
    }
}
