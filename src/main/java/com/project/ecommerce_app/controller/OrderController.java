package com.project.ecommerce_app.controller;

import com.project.ecommerce_app.dto.AddressDto;
import com.project.ecommerce_app.entity.Order;
import com.project.ecommerce_app.entity.OrderItem;
import com.project.ecommerce_app.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // Place an order
    @PostMapping("/place/{userId}")
    public ResponseEntity<Order> placeOrder(@PathVariable Integer userId, @RequestBody AddressDto address) {
        return ResponseEntity.ok(orderService.placeOrder(userId, address));
    }

    // Get order by ID
    @GetMapping("/{orderId}")
    public ResponseEntity<Optional<Order>> getOrderById(@PathVariable Integer orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    // Get all orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    // Get all orders of a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }
    // Cancel order
    @DeleteMapping("/{orderId}/cancel")
    public ResponseEntity<String> cancelOrder(@PathVariable Integer orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok("Order Cancelled");
    }

    @GetMapping("/orderitems/pending/{sellerId}")
    public ResponseEntity<List<OrderItem>> getNewOrderItemsForSeller(@PathVariable Integer sellerId){
        List<OrderItem> items = orderService.getNewOrderItemsForSeller(sellerId);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/orderitems/completed/{sellerId}")
    public ResponseEntity<List<OrderItem>> getPastOrderItemsForSeller(@PathVariable Integer sellerId){
        List<OrderItem> items = orderService.getPastOrderItemsForSeller(sellerId);
        return ResponseEntity.ok(items);
    }

    //mark order item as completed
    @GetMapping("/markascomplete/{orderItemId}")
    public ResponseEntity<String> markOrderItemAsCompleted(@PathVariable Integer orderItemId) {
        String result = orderService.markOrderItemAsCompleted(orderItemId);
        return ResponseEntity.ok(result);
    }
}
