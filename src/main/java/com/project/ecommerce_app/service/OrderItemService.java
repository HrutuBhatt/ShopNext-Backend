package com.project.ecommerce_app.service;

import com.project.ecommerce_app.entity.Order;
import com.project.ecommerce_app.entity.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {
    OrderItem addOrderItem(Integer orderId, OrderItem orderItem);
    Optional<OrderItem> getOrderItemById(Integer orderItemId);
    List<OrderItem> getOrderItemsByOrderId(Integer orderId);
    void removeOrderItem(Integer orderItemId);
}
