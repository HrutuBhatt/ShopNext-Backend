package com.project.ecommerce_app.service;

import com.project.ecommerce_app.entity.CartItem;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface CartItemService {
    CartItem addCartItem(CartItem cartItem);
//    void addToCart(Integer userId, Integer productId, int quantity);
    Optional<CartItem> getCartItemById(Integer cartItemId);
    void removeCartItem(Integer cartItemId);
    void increaseQuantity( Integer cartItemId);
    void decreaseQuantity(Integer cartItemId);
}
