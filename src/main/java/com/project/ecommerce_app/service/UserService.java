package com.project.ecommerce_app.service;

import com.project.ecommerce_app.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    Optional<User> getUserById(Integer userId);
    List<User> getAllUsers();
    User updateUser(Integer userId, User user);
    void deleteUser(Integer userId);
    Optional<User> getUserByEmail(String email);
}
