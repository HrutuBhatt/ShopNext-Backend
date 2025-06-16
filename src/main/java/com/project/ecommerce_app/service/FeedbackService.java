package com.project.ecommerce_app.service;

import com.project.ecommerce_app.entity.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback submitFeedback(Integer userId, Integer productId, String message);
    void deleteFeedback(Integer feedbackId);
    List<Feedback> getFeedbacksForProduct(Integer productId);
}
