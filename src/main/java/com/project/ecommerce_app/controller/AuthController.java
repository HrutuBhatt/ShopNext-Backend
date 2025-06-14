package com.project.ecommerce_app.controller;

import com.project.ecommerce_app.entity.User;
import com.project.ecommerce_app.repository.UserRepository;
import com.project.ecommerce_app.security.JwtUtil;
import com.project.ecommerce_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtil jwtUtils;
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody User user) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User existingUser  = userRepository.findByUsername(user.getUsername());
        String jwt = jwtUtils.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(Map.of(
                "token", jwt,
                "user", existingUser
        ));

    }
    @PostMapping("/signup")
    public String registerUser(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return "Error: Username is already taken!";
        }
        // Create new user's account
        User newUser = new User(
                user.getUsername(),
                user.getEmail(),
                encoder.encode(user.getPassword()),
                user.getRole()

        );
        userService.createUser(newUser);
//        userRepository.save(newUser);
        return "User registered successfully!";
    }
}