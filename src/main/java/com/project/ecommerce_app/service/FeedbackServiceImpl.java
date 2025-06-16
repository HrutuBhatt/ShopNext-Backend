package com.project.ecommerce_app.service;

import com.project.ecommerce_app.entity.Feedback;
import com.project.ecommerce_app.entity.Product;
import com.project.ecommerce_app.entity.User;
import com.project.ecommerce_app.repository.FeedbackRepository;
import com.project.ecommerce_app.repository.ProductRepository;
import com.project.ecommerce_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService{
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Feedback submitFeedback(Integer userId, Integer productId, String message) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Feedback feedback = new Feedback();
        feedback.setUser(user);
        feedback.setProduct(product);
        feedback.setMessage(message);
        feedback.setSubmittedAt(LocalDateTime.now());

        return feedbackRepository.save(feedback);
    }

    @Override
    public void deleteFeedback(Integer feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }

    @Override
    public List<Feedback> getFeedbacksForProduct(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return feedbackRepository.findByProduct(product);
    }
}
