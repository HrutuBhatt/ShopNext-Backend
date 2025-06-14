package com.project.ecommerce_app.service;

import com.project.ecommerce_app.entity.Cart;
import com.project.ecommerce_app.entity.CartItem;
import com.project.ecommerce_app.entity.Product;
import com.project.ecommerce_app.entity.User;
import com.project.ecommerce_app.repository.CartItemRepository;
import com.project.ecommerce_app.repository.CartRepository;
import com.project.ecommerce_app.repository.ProductRepository;
import com.project.ecommerce_app.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;
    @Override
    @Transactional
    public Cart getCartByUserId(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Cart cart =  cartRepository.findByUser(user);
        if(cart == null){
            cart = new Cart();
            cart.setUser(user);
            cart = cartRepository.save(cart);
        }

        return cart;
    }

    @Override
    public void addToCart(Integer userId, Integer productId, int quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get or create cart
        Cart cart = cartRepository.findByUser(user);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart = cartRepository.save(cart);
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Check if cartItem already exists
        Optional<CartItem> existingCartItem = cartItemRepository.findByCartAndProduct(cart, product);

        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItemRepository.save(cartItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItemRepository.save(cartItem);
        }
    }

    @Override
    public List<CartItem> getCartItems(Integer userId){
        Cart cart = getCartByUserId(userId);
        return cart.getCartItems();
    }


}
