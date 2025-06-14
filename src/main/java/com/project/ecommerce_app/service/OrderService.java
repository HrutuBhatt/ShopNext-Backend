package com.project.ecommerce_app.service;

import com.project.ecommerce_app.dto.AddressDto;
import com.project.ecommerce_app.entity.Order;
import com.project.ecommerce_app.entity.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order placeOrder(Integer UserId, AddressDto address);
    Optional<Order> getOrderById(Integer orderId);
    List<Order> getAllOrders();
    void cancelOrder(Integer orderId);
    List<Order> getOrdersByUserId(Integer userId);
    List<OrderItem> getNewOrderItemsForSeller(Integer sellerId);
    List<OrderItem> getPastOrderItemsForSeller(Integer sellerId);
    String markOrderItemAsCompleted(Integer orderItemId);
}
