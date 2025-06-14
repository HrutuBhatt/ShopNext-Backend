package com.project.ecommerce_app.service;
import com.project.ecommerce_app.entity.User;
import com.project.ecommerce_app.entity.Role;

import com.project.ecommerce_app.repository.UserRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Integer userId, User user) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setUsername(user.getUsername());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setRole(user.getRole());
            return userRepository.save(updatedUser);
        }
        return null;
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }


//    public void registerUser(String email, String password, Role role){
//        if (userRepository.findByEmail(email).isPresent()) {
//            throw new RuntimeException("User already exists");
//        }
//        User user = User.builder().email(email)
//                .password(passwordEncoder.encode(password))
//                .role(role)
//                .build();
//        userRepository.save(user);
//    }
    //login user
//    public Optional<User> loginUser(String username, String password){
//        Optional<User> userOptional = userRepository.findByUsername(username);
//        if(userOptional.isPresent()){
//            User user = userOptional.get();
//            if(passwordEncoder.matches(password, user.getPassword())){
//                return Optional.of(user);
//            }
//        }
//        return Optional.empty();
//    }

}
