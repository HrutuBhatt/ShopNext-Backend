package com.project.ecommerce_app.service;

import com.project.ecommerce_app.entity.Cart;
import com.project.ecommerce_app.entity.CartItem;

import java.util.List;
import java.util.Optional;

public interface CartService {
    //returns cart , also creates if there does not exists one.
    void addToCart(Integer userId, Integer productId, int quantity);
    Cart getCartByUserId(Integer userId);
    List<CartItem> getCartItems(Integer userId);

}
